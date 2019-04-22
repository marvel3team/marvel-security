package com.marvel.web.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.converts.LoginUserVoConvert;
import com.marvel.web.enums.LoginStatus;
import com.marvel.web.exception.BusinessException;
import com.marvel.common.utils.MD5Utils;
import com.marvel.framework.utils.MauthUtils;
import com.marvel.web.mapper.UserMapper;
import com.marvel.web.po.User;
import com.marvel.web.service.UserService;
import com.marvel.web.service.VerifyCodeService;
import com.marvel.web.vo.LoginUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Classname UserServiceImpl
 * @Description 用户service实现类
 * @Date 2019/2/19 上午10:40
 * @Author zj
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * guava缓存
     * expireAfterAccess(long, TimeUnit) 当缓存项在指定的时间段内没有被读或写就会被回收
     * expireAfterWrite(long, TimeUnit) 当缓存项在指定的时间段内没有更新就会被回收
     * maximumSize(long) 当前缓存可存的最大记录数
     * */
    private static Cache<String, User> mobileCache = CacheBuilder.newBuilder().maximumSize(1000)
            .expireAfterAccess(30, TimeUnit.MINUTES).build();
    /**
     * id纬度
     */
    private static Cache<Long, User> uidCache = CacheBuilder.newBuilder().maximumSize(1000)
            .expireAfterAccess(30, TimeUnit.MINUTES).build();

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Override
    public LoginUserVo login(String mobile, String password) {
        User user = get(mobile);
        if (user == null) {
            throw BusinessException.USERNAME_NO_EXISTS;
        }
        if (!MD5Utils.md5Digest(password.getBytes()).equals(user.getPassword())) {
            throw BusinessException.PASSWORD_ERROR;
        }
        if (user.getStatus() == LoginStatus.LOGOUT.value()) {
            //更新用户状态
            user.setStatus(LoginStatus.LOGIN.value());
            update(user);
        }
        String token = MauthUtils.create(user.getId());
        return LoginUserVoConvert.toLoginUserVo(user, token);
    }

    @Override
    public boolean register(Integer type, String username, String password) {
        User user = get(username);
        if (user != null) {
            throw BusinessException.USERNAME_EXISTS;
        }
        user = assembleUser(type, username, password);
        return save(user);
    }

    @Override
    public boolean resetPassword(RequestContext requestContext, String code, String password) {
        long uid = requestContext.getUid();
        String verifyCode = verifyCodeService.getCode(uid);
        if (StringUtils.isBlank(verifyCode) || !verifyCode.equals(code)) {
            throw BusinessException.VERIFY_CODE_ERROR;
        }
        User user = get(uid);
        if (user == null) {
            throw BusinessException.USER_NO_EXISTS;
        }
        user.setPassword(MD5Utils.md5Digest(password.getBytes()));
        verifyCodeService.clearCode(uid);
        return update(user);
    }

    @Override
    public User getUser(Long uid) {
        User user = get(uid);
        if (user == null) {
            throw BusinessException.USER_NO_EXISTS;
        }
        return user;
    }

    /**
     * 组装用户信息
     * @param type
     * @param username
     * @param password
     * @return
     */
    private User assembleUser(Integer type, String username, String password) {
        User user = new User();
        user.setId(snowflakeIdGenerator.generateId());
        user.setUsername(username);
        user.setPassword(MD5Utils.md5Digest(password.getBytes()));
        user.setMobile(username);
        user.setType(type);
        user.setStatus(LoginStatus.LOGOUT.value());
        long currentTimeMillis = System.currentTimeMillis();
        user.setCreateTime(currentTimeMillis);
        user.setUpdateTime(currentTimeMillis);
        user.setRemark(StringUtils.EMPTY);
        return user;
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    private boolean save(User user) {
        int save = userMapper.save(user);
        if (save > 0) {
            uidCache.put(user.getId(), user);
            mobileCache.put(user.getMobile(), user);
            return true;
        }
        return false;
    }

    /**
     * 获取用户
     * @param mobile
     * @return
     */
    private User get(String mobile) {
        User user = mobileCache.getIfPresent(mobile);
        if (user == null) {
            user = userMapper.findByMobile(mobile);
            if (user != null) {
                mobileCache.put(mobile, user);
            }
        }
        return user;
    }

    /**
     * 获取用户
     * @param uid
     * @return
     */
    private User get(Long uid) {
        User user = uidCache.getIfPresent(uid);
        if (user == null) {
            user = userMapper.findByUid(uid);
            if (user != null) {
                uidCache.put(uid, user);
            }
        }
        return user;
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    private boolean update(User user) {
        user.setUpdateTime(System.currentTimeMillis());
        int update = userMapper.update(user);
        if (update > 0) {
            uidCache.put(user.getId(), user);
            mobileCache.put(user.getMobile(), user);
            return true;
        }
        return false;
    }
}

package com.marvel.web.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.marvel.web.enums.LoginStatus;
import com.marvel.web.exception.BusinessException;
import com.marvel.common.utils.MD5Utils;
import com.marvel.framework.utils.MauthUtils;
import com.marvel.web.mapper.UserMapper;
import com.marvel.web.po.User;
import com.marvel.web.service.UserService;
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
    private static Cache<String, User> cache = CacheBuilder.newBuilder().maximumSize(1000)
            .expireAfterAccess(30, TimeUnit.MINUTES).build();

    @Resource
    private UserMapper userMapper;


    @Override
    public String login(Integer type, String username, String password) {
        User user = get(username);
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
        return MauthUtils.create(user.getUid());
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
    public boolean resetPassword(long uid, String oldPassword, String newPassword) {
        User user = userMapper.findByUid(uid);
        if (user == null) {
            throw BusinessException.USER_NO_EXISTS;
        }
        if (!user.getPassword().equals(MD5Utils.md5Digest(oldPassword.getBytes()))) {
            throw BusinessException.PASSWORD_ERROR;
        }
        user.setPassword(MD5Utils.md5Digest(newPassword.getBytes()));
        return update(user);
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
        long currentTimeMillis = System.currentTimeMillis();
        user.setUid(currentTimeMillis);
        user.setUsername(username);
        user.setPassword(MD5Utils.md5Digest(password.getBytes()));
        user.setType(type);
        user.setStatus(LoginStatus.LOGOUT.value());
        user.setCreateTime(currentTimeMillis);
        user.setUpdateTime(currentTimeMillis);
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
            cache.put(user.getUsername(), user);
            return true;
        }
        return false;
    }

    /**
     * 获取用户
     * @param username
     * @return
     */
    private User get(String username) {
        User user = cache.getIfPresent(username);
        if (user == null) {
            user = userMapper.findByName(username);
            if (user != null) {
                cache.put(username, user);
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
            cache.put(user.getUsername(), user);
            return true;
        }
        return false;
    }
}

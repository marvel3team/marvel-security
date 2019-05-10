package com.marvel.web.service.impl;

import com.marvel.common.utils.RandomUtils;
import com.marvel.web.constants.RedisKeys;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.service.SmsService;
import com.marvel.web.service.VerifyCodeService;
import com.marvel.web.vo.VerifyCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Classname VerifyCodeServiceImpl
 * @Description 验证码实现类
 * @Date 2019-04-22 19:13
 * @Author zhongjie
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    /**
     * 验证码过期时间，5分钟
     */
    private final long expireTime = 300;

    /**
     * 两次验证码发送的最小间隔时间，90S
     */
    private final int intervalTime = 90000;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SmsService smsService;

    @Override
    public VerifyCode getVerify(Long uid, String mobile) {
        String key = RedisKeys.getKey(RedisKeys.VERIFY_CODE, String.valueOf(uid));
        Map<String, Long> entries = redisTemplate.opsForHash().entries(key);
        //验证码不存在，如第一次请求或者验证码过期后再次请求
        if (CollectionUtils.isEmpty(entries) || CollectionUtils.isEmpty(entries.keySet())
                || CollectionUtils.isEmpty(entries.values())) {
            return new VerifyCode(sendCode(key, mobile), expireTime);
        }
        //如果验证码存在，就是说验证码未过期，如果距离上一次验证码发送超过90s，则进行一次验证码发送
        Long createTime = entries.values().iterator().next();
        if (System.currentTimeMillis() - createTime > intervalTime) {
            return new VerifyCode(sendCode(key, mobile), expireTime);
        }
        throw BusinessException.LAST_VERIFY_CODE_EFFECTIVE;
    }

    @Override
    public String getCode(Long uid) {
        String key = RedisKeys.getKey(RedisKeys.VERIFY_CODE, String.valueOf(uid));
        Map<String, Long> entries = redisTemplate.opsForHash().entries(key);
        if (!CollectionUtils.isEmpty(entries) && !CollectionUtils.isEmpty(entries.keySet())) {
            return entries.keySet().iterator().next();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public void clearCode(Long uid) {
        String key = RedisKeys.getKey(RedisKeys.VERIFY_CODE, String.valueOf(uid));
        redisTemplate.delete(key);
    }

    /**
     * 获取验证码
     * @param key
     * @param mobile
     * @return
     */
    private boolean sendCode(String key, String mobile) {
        //发送验证码
        int code = RandomUtils.random4Bit();
        boolean success = smsService.sendCode(mobile, code);
        if (!success) {
            throw BusinessException.VERIFY_CODE_SEND_FAIL;
        }
        //保存验证码，并设置验证码的有效期
        redisTemplate.opsForHash().put(key, code, System.currentTimeMillis());
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        return true;
    }
}

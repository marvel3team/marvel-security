package com.marvel.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Classname RedisTemplateInit
 * @Description TODO
 * @Date 2019-04-23 17:41
 * @Author zhongjie
 */
//@Component
public class RedisTemplateInit implements CommandLineRunner {
    //@Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForValue().get("os_system");
    }
}

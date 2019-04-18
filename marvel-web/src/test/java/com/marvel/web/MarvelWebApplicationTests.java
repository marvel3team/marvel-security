package com.marvel.web;

import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.po.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.marvel.common", "com.marvel.framework", "com.marvel.web"})
public class MarvelWebApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Test
    public void redisTemplateForObject() {
        Assert.assertTrue(Objects.nonNull(redisTemplate));
        redisTemplate.opsForValue().set("user", getUser());
        Object user = redisTemplate.opsForValue().get("user");
        Assert.assertNotNull(user);
    }

    @Test
    public void redisTemplateForString() {
        Assert.assertTrue(Objects.nonNull(redisTemplate));
        redisTemplate.opsForValue().set("test_key", "test_value");
        Object value = redisTemplate.opsForValue().get("test_key");
        Assert.assertTrue(Objects.equals("test_value", value));
    }

    @Test
    public void snowFlakeTest(){
        for (int i = 0; i < 1; i++) {
            long id = snowflakeIdGenerator.generateId();
            Assert.assertTrue(id > 0);
        }
    }

    /**
     * 组装用户
     * @return
     */
    private User getUser(){
        User user = new User();
        user.setUid(100000L);
        return user;
    }
}

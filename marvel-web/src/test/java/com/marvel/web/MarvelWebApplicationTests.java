package com.marvel.web;

import com.alibaba.fastjson.JSON;
import com.marvel.common.http.template.ApiHttpClient;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.po.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MarvelWebApplicationTests extends BaseTestUtils{

    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;
    @Autowired
    private ApiHttpClient apiHttpClient;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
    public void redisTemplateForGetHash() {
        long currentTimeMillis = System.currentTimeMillis();
        Assert.assertTrue(Objects.nonNull(redisTemplate));
        Map entries = redisTemplate.opsForHash().entries("code:13761231122");
        System.out.println(JSON.toJSONString(entries));
        System.out.println(System.currentTimeMillis()-currentTimeMillis);
    }

    @Test
    public void redisTemplateForIncrement() {
        Assert.assertTrue(Objects.nonNull(redisTemplate));
        Long test1 = stringRedisTemplate.opsForValue().increment("test1");
        System.out.println(test1);
        Assert.assertTrue(test1 > 0);
        Long test11 = Long.parseLong(stringRedisTemplate.opsForValue().get("test1"));
        System.out.println(test11);
        Assert.assertTrue(test11.equals(test1));
    }

    @Test
    public void redisTemplateForSetHash() {
        Assert.assertTrue(Objects.nonNull(redisTemplate));
        redisTemplate.opsForHash().put("code:13761231122", "6666", 1555932166000L);
        redisTemplate.expire("code:13761231122", 1, TimeUnit.MINUTES);
    }

    @Test
    public void snowFlakeTest(){
        for (int i = 0; i < 1; i++) {
            long id = snowflakeIdGenerator.generateId();
            Assert.assertTrue(id > 0);
        }
    }

    @Test
    public void httpClientTest(){
        String url = "http://wthrcdn.etouch.cn/weather_mini";
        HashMap<String, Object> params = new HashMap<>();
        params.put("city", "深圳");
        String response = apiHttpClient.doGet(url, params);
        Assert.assertNotNull(response);
    }

    @Test
    public void httpClientPost(){
        String url = "http://localhost:8081/v1/user/login.json";
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", "jack");
        params.put("password", 123456);
        String response = apiHttpClient.doPost(url, params);
        Assert.assertNotNull(response);
    }

    /**
     * 组装用户
     * @return
     */
    private User getUser(){
        User user = new User();
        user.setId(100000L);
        return user;
    }
}

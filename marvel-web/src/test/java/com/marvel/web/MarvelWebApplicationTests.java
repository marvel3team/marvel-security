package com.marvel.web;

import com.marvel.common.http.template.ApiHttpClient;
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
import java.util.HashMap;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.marvel.common", "com.marvel.framework", "com.marvel.web"})
public class MarvelWebApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;
    @Autowired
    private ApiHttpClient apiHttpClient;

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
        user.setUid(100000L);
        return user;
    }
}

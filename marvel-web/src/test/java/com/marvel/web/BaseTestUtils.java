package com.marvel.web;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname BaseTestUtils
 * @Description 基础测试工具类
 * @Date 2019-04-24 15:21
 * @Author zhongjie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.marvel.common", "com.marvel.framework", "com.marvel.web"})
public class BaseTestUtils {
}

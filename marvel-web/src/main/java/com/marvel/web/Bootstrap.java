package com.marvel.web;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Classname Bootstrap
 * @Description 启动入口
 * @Date 2019/2/19 下午5:20
 * @Author zj
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@ComponentScan(basePackages = {"com.marvel.common", "com.marvel.framework", "com.marvel.web"})
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}

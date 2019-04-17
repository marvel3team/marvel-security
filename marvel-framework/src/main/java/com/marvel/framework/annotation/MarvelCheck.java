package com.marvel.framework.annotation;

import java.lang.annotation.*;

/**
 * @Classname MarvelCheck
 * @Description 自定义注解，用于controller类方法级别，比如是否需要进行Auth验证
 * @Date 2019/2/21 下午1:49
 * @Author zj
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MarvelCheck {
    /**
     * 返回是否需要进行check的boolean值，默认不校验
     */
    boolean auth() default false;
}

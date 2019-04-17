package com.marvel.framework.exception;

import com.marvel.common.exception.CommonException;

/**
 * @Classname ExceptionFactory
 * @Description 业务异常常量类
 * @Date 2019/2/19 上午10:40
 * @Author zj
 */
public class ExceptionFactory {

    /**
     * 服务器内部错误
     */
    public static CommonException SERVER_INTERNAL_ERROR = new CommonException(200000, "服务器内部错误");

    /**
     * token非法
     */
    public static CommonException INVALID_TOKEN = new CommonException(200001, "token非法");

    /**
     * token过期失效
     */
    public static CommonException TOKEN_EXPIRE = new CommonException(200002, "token失效");

    /**
     * 非法用户ID
     */
    public static CommonException INVALID_UID = new CommonException(200003, "非法用户ID");
}
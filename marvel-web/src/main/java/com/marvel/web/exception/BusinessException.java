package com.marvel.web.exception;

import com.marvel.common.exception.CommonException;

/**
 * @Classname ExceptionFactory
 * @Description 业务异常常量类
 * @Date 2019/2/19 上午10:40
 * @Author zj
 */
public class BusinessException {

    /**
     * 参数非法
     */
    public static CommonException INVALID_PARAMS = new CommonException(300001, "参数非法");

    /**
     * 用户名不存在
     */
    public static CommonException USERNAME_NO_EXISTS = new CommonException(300002, "用户名不存在");

    /**
     * 密码错误
     */
    public static CommonException PASSWORD_ERROR = new CommonException(300003, "密码错误");

    /**
     * 注册失败
     */
    public static CommonException REGISTER_FAIL = new CommonException(300004, "注册失败");

    /**
     * 用户名已存在
     */
    public static CommonException USERNAME_EXISTS = new CommonException(300005, "用户名已存在");

    /**
     * 原密码与新密码相同
     */
    public static CommonException PASSWORD_TWICE_SAME = new CommonException(300006, "原密码与新密码相同");

    /**
     * 用户不存在
     */
    public static CommonException USER_NO_EXISTS = new CommonException(300007, "用户不存在");

    /**
     * 密码重置失败
     */
    public static CommonException PASSWORD_SET_FAIL = new CommonException(300008, "密码重置失败");

}

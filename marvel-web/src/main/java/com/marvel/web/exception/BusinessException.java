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

    /**
     * 验证码发送失败
     */
    public static CommonException VERIFY_CODE_SEND_FAIL = new CommonException(300009, "验证码发送失败");

    /**
     * 上次验证码还在有效期内
     */
    public static CommonException LAST_VERIFY_CODE_EFFECTIVE = new CommonException(300010, "上次验证码还在有效期内");

    /**
     * 验证码错误
     */
    public static CommonException VERIFY_CODE_ERROR = new CommonException(300011, "验证码错误");

    /**
     * 查询失败
     */
    public static CommonException QUERY_DB_ERROR = new CommonException(300012, "查询失败");

    /**
     * 企业不存在
     */
    public static CommonException COMPANY_NOT_EXISTS = new CommonException(300013, "企业不存在");

    /**
     * 专家不存在
     */
    public static CommonException EXPERT_NOT_EXISTS = new CommonException(300014, "专家不存在");

    /**
     * 创建计划失败
     */
    public static CommonException CREATE_PLAN_ERROR = new CommonException(300015, "创建计划失败");

    /**
     * 更新失败
     */
    public static CommonException UPDATE_PLAN_ERROR = new CommonException(300016, "更新失败");

    /**
     * 计划不存在
     */
    public static CommonException PLAN_NOT_EXISTS = new CommonException(300017, "计划不存在");

    /**
     * 新增失败
     */
    public static CommonException SAVE_ERROR = new CommonException(300018, "新增失败");

    /**
     * 更新失败
     */
    public static CommonException UPDATE_ERROR = new CommonException(300019, "更新失败");

    /**
     * 数据不存在
     */
    public static CommonException DATA_NOT_EXISTS = new CommonException(300020, "数据不存在");



}

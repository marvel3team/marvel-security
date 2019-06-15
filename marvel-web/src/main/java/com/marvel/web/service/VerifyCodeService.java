package com.marvel.web.service;

import com.marvel.web.vo.VerifyCode;

/**
 * @Classname VerifyCodeService
 * @Description 验证码接口类
 * @Date 2019-04-22 19:12
 * @Author zhongjie
 */
public interface VerifyCodeService {

    /**
     * Description: 获取验证码
     *
     * @param uid
     * @param mobile
     * @return com.marvel.web.vo.VerifyCode
     * @Date 19:10 2019-04-22
     * @Author zhongjie
     **/
    VerifyCode getVerify(Long uid, String mobile);

    /**
     * Description: 获取发送到手机号的验证码
     *
     * @Param
     * @param uid
     * @return java.lang.String
     * @Date 20:00 2019-04-22
     * @Author zhongjie
    **/
    String getCode(Long uid);

    /**
     * 清空验证码
     * @param uid
     */
    void clearCode(Long uid);
}

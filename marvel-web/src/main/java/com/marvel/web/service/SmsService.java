package com.marvel.web.service;

/**
 * @Classname SmsService
 * @Description 短信服务接口类
 * @Date 2019-05-09 20:49
 * @Author zhongjie
 */
public interface SmsService {

    /**
     * Description: 验证码发送
     *
     * @param mobile 手机号
     * @param code 验证码
     * @return boolean
     * @Date 20:51 2019-05-09
     * @Author zhongjie
    **/
    boolean sendCode(String mobile, int code);
}

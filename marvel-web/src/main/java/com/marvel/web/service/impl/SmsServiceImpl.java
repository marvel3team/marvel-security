package com.marvel.web.service.impl;

import com.marvel.common.http.template.ApiHttpClient;
import com.marvel.web.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname SmsServiceImpl
 * @Description 短信服务实现类
 * @Date 2019-05-09 20:51
 * @Author zhongjie
 */
@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private ApiHttpClient apiHttpClient;

    @Override
    public boolean sendCode(String mobile, int code) {
        try {

        } catch (Exception e) {
            LOGGER.error(String.format("sms send error. mobile: %s, code: %d", mobile, code), e);
        }
        return false;
    }
}

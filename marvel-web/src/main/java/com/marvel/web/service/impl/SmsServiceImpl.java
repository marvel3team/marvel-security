package com.marvel.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.marvel.web.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final String domain = "dysmsapi.aliyuncs.com";
    private static final String accessKeyId = "LTAIt6otHuKUEouN";
    private static final String accessSecret = "pAqdEc5U9qAJG6ISf2ajjUDuyftguz";
    private static final String version = "2017-05-25";
    private static final String action = "SendSms";
    private static final String regionId = "default";
    private static final String signName = "第一线云";
    private static final String templateCode = "SMS_165105248";

    @Override
    public boolean sendCode(String mobile, int code) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction(action);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            LOGGER.info(String.format("sms send. mobile: %s, code: %d, response: %s", mobile, code, JSON.toJSONString(response)));
            String data = response.getData();
            return "OK".equals(JSON.parseObject(data).getString("Message"));
        } catch (Exception e) {
            LOGGER.error(String.format("sms send error. mobile: %s, code: %d", mobile, code), e);
        }
        return false;
    }

}

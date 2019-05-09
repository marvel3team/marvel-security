package com.marvel.web.service.impl;

import com.marvel.common.http.template.ApiHttpClient;
import com.marvel.web.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SmsServiceImpl
 * @Description 短信服务实现类
 * @Date 2019-05-09 20:51
 * @Author zhongjie
 */
@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    private static final String apiHost = "http://dysmsapi.aliyuncs.com/";
    private static final String accessKeyId = "LTAIt6otHuKUEouN";
    private static final String accessSecret = "pAqdEc5U9qAJG6ISf2ajjUDuyftguz";
    private static final String version = "2017-05-25";
    private static final String signName = "第一线云";
    private static final String templateCode = "SMS_165105248";
    // 这里一定要设置GMT时区
    private static final java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    static {
        // 这里一定要设置GMT时区
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
    }

    @Autowired
    private ApiHttpClient apiHttpClient;

    @Override
    public boolean sendCode(String mobile, int code) {
        try {
            Map<String, String> map = requestParams(mobile, code);
            String signature = sign(map);
            //处理
            java.util.Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                map.put(key, specialUrlEncode(map.get(key)));
            }

            map.put("Signature", signature);

            String response = apiHttpClient.doGet(apiHost, map);
            System.out.println(response);
            return true;
        } catch (Exception e) {
            LOGGER.error(String.format("sms send error. mobile: %s, code: %d", mobile, code), e);
        }
        return false;
    }


    private Map<String, String> requestParams(String mobile, int code) {
        Map<String, String> paras = new HashMap<String, String>();
        // 1. 系统参数
        paras.put("SignatureMethod", "HMAC-SHA1");
        paras.put("SignatureNonce", java.util.UUID.randomUUID().toString());
        paras.put("AccessKeyId", accessKeyId);
        paras.put("SignatureVersion", "1.0");
        paras.put("Timestamp", df.format(new java.util.Date()));
        // 2. 业务API参数
        paras.put("Action", "SendSms");
        paras.put("Format", "json");
        paras.put("Version", version);
        paras.put("PhoneNumbers", mobile);
        paras.put("SignName", signName);
        paras.put("TemplateParam", "{\"code\":\"" + code + "\"}");
        paras.put("TemplateCode", templateCode);
        return paras;
    }


    /**
     * 生成签名串
     * @param paras
     * @return
     * @throws Exception
     */
    private String sign(Map<String, String> paras) throws Exception {
        // 4. 参数KEY排序
        java.util.TreeMap<String, String> sortParas = new java.util.TreeMap<String, String>();
        sortParas.putAll(paras);
        // 5. 构造待签名的字符串
        java.util.Iterator<String> it = sortParas.keySet().iterator();
        StringBuilder sortQueryStringTmp = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key)));
        }
        String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append("GET").append("&");
        stringToSign.append(specialUrlEncode("/")).append("&");
        stringToSign.append(specialUrlEncode(sortedQueryString));
        String sign = sign(accessSecret + "&", stringToSign.toString());
        // 6. 签名最后也要做特殊URL编码
        String signature = specialUrlEncode(sign);
        return signature;
    }

    private String specialUrlEncode(String value) throws Exception {
        return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    private String sign(String accessSecret, String stringToSign) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes("UTF-8"), "HmacSHA1"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        return new sun.misc.BASE64Encoder().encode(signData);
    }

}

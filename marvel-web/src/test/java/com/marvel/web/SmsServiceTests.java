package com.marvel.web;

import com.marvel.web.service.SmsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname SmsServiceTests
 * @Description TODO
 * @Date 2019/5/9 下午11:14
 * @Author zhongjie
 */
public class SmsServiceTests extends BaseTestUtils {

    @Autowired
    private SmsService smsService;

    @Test
    public void smsTest() {
        smsService.sendCode("18519507691", 6666);
    }
}

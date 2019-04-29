package com.marvel.web;

import com.marvel.web.service.BureauService;
import com.marvel.web.vo.BureauInfoReqVo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author andy
 * @version V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p>
 * <p>版权所有: Copyright©1999-2019 leyou.com. All Rights Reserved</p>
 * @Title: BureauApplicationTests
 * @Package marvel-security
 * @Description: 科局服务测试
 * @date 2019/4/2911:32
 */
public class BureauApplicationTests extends BaseTestUtils{

    @Resource
    private BureauService bureauService;

    @Test
    public void updateBureau(){
        Assert.assertNotNull(bureauService);
        BureauInfoReqVo infoReqVo = assembleBureauInfo();
        Assert.assertNotNull(infoReqVo);
        String result = bureauService.updateBureauInfo(infoReqVo);
        Assert.assertEquals("{}",result);
    }


    private BureauInfoReqVo assembleBureauInfo(){
        BureauInfoReqVo reqVo = new BureauInfoReqVo();
        reqVo.setId(1l);
        reqVo.setAreaId(124);
        reqVo.setMobile("12345678909");
        reqVo.setRemark("测试下");
        return reqVo;
    }
}

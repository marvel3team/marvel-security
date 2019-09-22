package com.marvel.web;

import com.alibaba.fastjson.JSON;
import com.marvel.common.models.PageBean;
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
        BureauInfoReqVo infoReqVo = assembleBureauInfo(1);
        infoReqVo.setName("神");
        infoReqVo.setId(625456843300274176L);
        String result = bureauService.updateBureauInfo(infoReqVo);
        Assert.assertEquals("{}",result);
    }

    @Test
    public void saveBureau(){
        Assert.assertNotNull(bureauService);
        for (int i = 0; i < 20; i++) {
            BureauInfoReqVo infoReqVo = assembleBureauInfo(i);
            Assert.assertNotNull(infoReqVo);
            String add = bureauService.addBureauInfo(infoReqVo);
            Assert.assertEquals("{}",add);
        }

    }

    @Test
    public void delBureau(){
        Assert.assertNotNull(bureauService);
        String del = bureauService.delBureauInfo(625456843300274176L);
        Assert.assertEquals("{}",del);
    }

    @Test
    public void getBureauUsers(){
        Assert.assertNotNull(bureauService);
        PageBean pageBean = bureauService.getBureauUserInfoList(12333L,-1L,10);
        System.out.println(JSON.toJSONString(pageBean));
    }


    private BureauInfoReqVo assembleBureauInfo(int i){
        BureauInfoReqVo reqVo = new BureauInfoReqVo();
        reqVo.setAreaId(124);
        reqVo.setName("科举人员"+i);
        reqVo.setCompanyId(12333L);
        reqVo.setMobile("123456789"+i);
        reqVo.setRemark("");
        return reqVo;
    }
}

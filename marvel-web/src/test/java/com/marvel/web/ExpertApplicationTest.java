package com.marvel.web;

import com.marvel.common.models.PageBean;
import com.marvel.web.controller.req.ExpertInfoReq;
import com.marvel.web.po.ExpertInfo;
import com.marvel.web.service.ExpertService;
import com.marvel.web.vo.CompanyListVo;
import com.marvel.web.vo.ExpertInfoVo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author andy
 * @version V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p>
 * <p>版权所有: Copyright©1999-2019 leyou.com. All Rights Reserved</p>
 * @Title: ExpertApplicationTest
 * @Package marvel-security
 * @Description: 专家信息测试类
 * @date 2019/4/2915:24
 */
public class ExpertApplicationTest extends BaseTestUtils {

    @Resource
    private ExpertService expertService;

    @Test
    public void findByPage() {
        Assert.assertNotNull(expertService);
        long cursor = -1;
        int count = 10;
        PageBean<ExpertInfoVo> pageBean = expertService.getExpertList(cursor, count);
        Assert.assertNotNull(pageBean);
    }

    @Test
    public void addExpert() {
        Assert.assertNotNull(expertService);
        for (int i = 0; i < 20; i++) {
            ExpertInfo infoReq = assembleExpertInfoReq(i);
            boolean add = expertService.save(infoReq);
            Assert.assertTrue(add);
        }

    }

    @Test
    public void updateExpert() {
        Assert.assertNotNull(expertService);
        ExpertInfo infoReq = assembleExpertInfoReq(11);
        infoReq.setId(625471651642867712L);
        boolean add = expertService.update(infoReq);
        Assert.assertTrue(add);

    }

    @Test
    public void delExpert() {
        Assert.assertNotNull(expertService);
        boolean add = expertService.delExpertInfo(625471651642867712L);
        Assert.assertTrue(add);

    }

    @Test
    public void getExpert() {
        Assert.assertNotNull(expertService);
        ExpertInfoVo add = expertService.getExpertInfo(625471651831611392L);
        System.out.println(add);

    }

    private ExpertInfo assembleExpertInfoReq(int i) {
        ExpertInfo infoReq = new ExpertInfo();
        infoReq.setIdCardNo("1233333" + i);
        infoReq.setName("专家" + i);
        infoReq.setCompanyId(12333L);
        infoReq.setWorkCompany("菜鸟科技" + i);
        infoReq.setWorkAddress("");
        infoReq.setWorkLife(12);
        infoReq.setPositionalTitle("");
        infoReq.setIsSyndic(1);
        infoReq.setSex(1);
        infoReq.setLevel(-1);
        infoReq.setEvaluateRange("");
        infoReq.setCollage("");
        infoReq.setHomeAddress("");
        infoReq.setMobile("");
        infoReq.setRemark("");
        infoReq.setSignUrl("");
        infoReq.setNation("");
        infoReq.setHighestDegree("");
        infoReq.setJobResume("");
        infoReq.setCategories("");
        infoReq.setHonor("");
        return infoReq;
    }
}

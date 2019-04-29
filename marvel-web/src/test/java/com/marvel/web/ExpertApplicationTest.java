package com.marvel.web;

import com.marvel.common.models.PageBean;
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
public class ExpertApplicationTest extends BaseTestUtils{

    @Resource
    private ExpertService expertService;

    @Test
    public void findByPage(){
        Assert.assertNotNull(expertService);
        long cursor = -1;
        int count = 10;
        PageBean<ExpertInfoVo> pageBean = expertService.getExpertList(cursor,count);
        Assert.assertNotNull(pageBean);
    }
}

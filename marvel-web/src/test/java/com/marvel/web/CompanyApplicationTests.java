package com.marvel.web;

import com.marvel.common.models.PageBean;
import com.marvel.web.po.Problem;
import com.marvel.web.service.CompanyService;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyListVo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author andy
 * @version V1.0
 * @Title: CompanyApplicationTests
 * @Package marvel-security
 * @Description:
 * @date 2019/4/2810:35
 */
public class CompanyApplicationTests  extends BaseTestUtils{

    @Resource
    private CompanyService companyService;



    @Test
    public void getCompanyPage(){
        Assert.assertNotNull(companyService);
        long cursor = -1;
        int count = 10;
        PageBean<CompanyListVo> pageBean = companyService.getCompanyList(cursor,count);
        Assert.assertNotNull(pageBean);
    }


    @Test
    public void getCompanyInfo(){
        Assert.assertNotNull(companyService);
        long id = 1;
        CompanyDetailVo companyDetailVo = companyService.getCompanyInfo(id);
        Assert.assertNotNull(companyDetailVo);
    }
}

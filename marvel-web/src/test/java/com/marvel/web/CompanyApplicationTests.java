package com.marvel.web;

import com.marvel.common.models.PageBean;
import com.marvel.web.mapper.CompanyStandardMapper;
import com.marvel.web.po.CompanyStandard;
import com.marvel.web.service.CompanyService;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyInfoReqVo;
import com.marvel.web.vo.CompanyListVo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    private CompanyStandardMapper companyStandardMapper;



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

    @Test
    public void updateCompanyInfo(){
        CompanyInfoReqVo updateCompanyInfo = new CompanyInfoReqVo();
        updateCompanyInfo.setId(1l);
        updateCompanyInfo.setIndustryId(2l);
        updateCompanyInfo.setAreaId(123);
        updateCompanyInfo.setBusinessCode("G12138");
        updateCompanyInfo.setBusinessLicenseNo("TG101010101");
        updateCompanyInfo.setMobile("1111111111");
        updateCompanyInfo.setName("哈里波提");
        updateCompanyInfo.setLegalPreson("哈利");
        updateCompanyInfo.setRegistedCapital("3000000");
        updateCompanyInfo.setEmail("xxxx@gmail.com");
        String update = companyService.updateCompanyInfo(updateCompanyInfo);
        Assert.assertEquals("{}",update);
    }

    @Test
    public void getCompnayByIds(){
        Assert.assertNotNull(companyStandardMapper);
        List<Long> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);
        List<CompanyStandard> list= companyStandardMapper.getCompanyByIds(ids);
        Assert.assertNotNull(list);
    }
}

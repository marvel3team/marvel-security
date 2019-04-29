package com.marvel.web;

import com.alibaba.fastjson.JSON;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.mapper.CompanyStandardMapper;
import com.marvel.web.po.CompanyStandard;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname StandardApplicationTests
 * @Description TODO
 * @Date 2019-04-29 16:27
 * @Author zhongjie
 */
public class StandardApplicationTests extends BaseTestUtils {

    @Resource
    private CompanyStandardMapper companyStandardMapper;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Test
    public void saveTest(){
        CompanyStandard standard = mock();
        int save = companyStandardMapper.save(standard);
        Assert.assertTrue(save > 0);
    }

    private CompanyStandard mock() {
        CompanyStandard standard = new CompanyStandard();
        standard.setId(snowflakeIdGenerator.generateId());
        standard.setName("测试");
        standard.setAreaId(1000);
        standard.setType(1);
        standard.setIndustryId(snowflakeIdGenerator.generateId());
        standard.setRegistedCapital(snowflakeIdGenerator.generateId());
        standard.setLegalPerson("法人");
        standard.setLegalPersonMobile("13683945621");
        standard.setBusinessTypeCode("GQ1334949586");
        standard.setEmail("zz13562@126.com");
        standard.setBusinessLicenseId("ENF33333333333");
        return standard;
    }

    @Test
    public void getByIdTest(){
        Long id = 11111L;
        CompanyStandard company = companyStandardMapper.getCompanyById(id);
        System.out.println(JSON.toJSONString(company));
        Assert.assertNotNull(company);
    }

    @Test
    public void getByIdsTest(){
        List<Long> ids = Lists.newArrayList(11111L, 572460544074711040L);
        List<CompanyStandard> list = companyStandardMapper.getCompanyByIds(ids);
        System.out.println(JSON.toJSONString(list));
        Assert.assertNotNull(list);
    }

    @Test
    public void getByPage(){
        List<CompanyStandard> list = companyStandardMapper.getCompanyListPage(1, null, 10);
        System.out.println(JSON.toJSONString(list));
        Assert.assertNotNull(list);
    }

    @Test
    public void updateTest(){
        Long id = 11111L;
        CompanyStandard company = companyStandardMapper.getCompanyById(id);
        company.setName("新名称");
        company.setBusinessLicenseId("H33333333");
        int update = companyStandardMapper.updateCompanyStandard(company);
        Assert.assertTrue(update > 0);
        CompanyStandard newCompany = companyStandardMapper.getCompanyById(id);
        Assert.assertTrue(company.getName().equals(newCompany.getName()));
    }

}

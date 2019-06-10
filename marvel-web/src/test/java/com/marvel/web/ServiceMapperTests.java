package com.marvel.web;

import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.mapper.ServiceMapper;
import com.marvel.web.po.ServiceInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname ServiceMapperTests
 * @Description ServiceMapper
 * @Date 2019/4/24 下午10:37
 * @Author zhongjie
 */
public class ServiceMapperTests extends BaseTestUtils{

    @Resource
    private ServiceMapper serviceMapper;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Test
    public void saveTest(){
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setId(snowflakeIdGenerator.generateId());
        serviceInfo.setServiceType(1);
        serviceInfo.setServiceName("安全隐患排查");
        serviceInfo.setServiceCycle(10);
        int save = serviceMapper.save(serviceInfo);
        Assert.assertTrue(save == 1);
    }

    @Test
    public void getListTest(){
        Assert.assertNotNull(serviceMapper);
        List<ServiceInfo> list = serviceMapper.findByPage(null,null, 10);
        Assert.assertNotNull(list);
    }
}

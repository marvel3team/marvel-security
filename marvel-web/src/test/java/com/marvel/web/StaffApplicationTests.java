package com.marvel.web;

import com.alibaba.fastjson.JSON;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.mapper.StaffMapper;
import com.marvel.web.po.Staff;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Classname StaffApplicationTests
 * @Description TODO
 * @Date 2019-04-29 15:32
 * @Author zhongjie
 */
public class StaffApplicationTests extends BaseTestUtils{

    @Resource
    private StaffMapper staffMapper;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Test
    public void saveTest(){
        Staff staff = mock();
        int insert = staffMapper.insert(staff);
        Assert.assertTrue(insert > 0);
    }

    @Test
    public void getByIdTest(){
        Long id = 572447286907371520L;
        Staff staff = staffMapper.findById(id);
        System.out.println(JSON.toJSONString(staff));
        Assert.assertNotNull(staff);
    }

    private Staff mock() {
        Staff staff = new Staff();
        staff.setId(snowflakeIdGenerator.generateId());
        staff.setName("测试");
        staff.setMobile("13748201045");
        staff.setIdCardNo("360435199003214458");
        staff.setCompanyId(snowflakeIdGenerator.generateId());
        staff.setDepartmentId(snowflakeIdGenerator.generateId());
        staff.setWorkshopId(snowflakeIdGenerator.generateId());
        staff.setTeamId(snowflakeIdGenerator.generateId());
        staff.setProfessionId(snowflakeIdGenerator.generateId());
        staff.setIsSpecialPersonnel(1);
        staff.setSpecialPersonnelId(snowflakeIdGenerator.generateId());
        staff.setRemark("备注一下");
        return staff;
    }

}

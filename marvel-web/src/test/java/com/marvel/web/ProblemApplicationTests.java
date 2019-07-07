package com.marvel.web;

import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.mapper.ProblemMapper;
import com.marvel.web.po.Problem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname ProblemApplicationTests
 * @Description Problem
 * @Date 2019-04-24 15:22
 * @Author zhongjie
 */
public class ProblemApplicationTests extends BaseTestUtils{

    @Resource
    private ProblemMapper problemMapper;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Test
    public void saveTest(){
        Assert.assertNotNull(problemMapper);
        Problem problem = mockProblem();
        int save = problemMapper.save(problem);
        Assert.assertTrue(save == 1);
    }

    @Test
    public void findByIdTest(){
        Assert.assertNotNull(problemMapper);
        long id = 572184271712681984l;
        Problem problem = problemMapper.findById(id);
        Assert.assertNotNull(problem);
    }

    @Test
    public void updateTest() {
        Assert.assertNotNull(problemMapper);
        Problem problem = mockProblem();
        long id = 570635707547123712l;
        problem.setId(id);
        problem.setStatus(2);
        problemMapper.update(problem);
        Problem byId = problemMapper.findById(id);
        Assert.assertEquals(byId.getStatus(), problem.getStatus());
    }

    @Test
    public void findByPage(){
        Assert.assertNotNull(problemMapper);
        List<Problem> list = problemMapper.findByPage(2, null, null, 10);
        Assert.assertNotNull(list);
    }

    private Problem mockProblem() {
        Problem problem = new Problem();
        problem.setId(snowflakeIdGenerator.generateId());
        problem.setPlanId(snowflakeIdGenerator.generateId());
        problem.setRuleId(1000000);
        problem.setExpertId(snowflakeIdGenerator.generateId());
        problem.setProjectId(2000000);
        problem.setProjectName("项目名");
        problem.setCorrectiveAction("整改措施");
        problem.setProblemContent("问题内容");
        problem.setTerm(1);
        problem.setStatus(1);
        problem.setUpdateTime(System.currentTimeMillis());
        return problem;
    }
}

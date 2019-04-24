package com.marvel.web;

import com.alibaba.fastjson.JSON;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.mapper.ProblemPicMapper;
import com.marvel.web.po.ProblemPic;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ProblemPicApplicationTests
 * @Description TODO
 * @Date 2019-04-24 14:51
 * @Author zhongjie
 */
public class ProblemPicApplicationTests extends BaseTestUtils{

    @Resource
    private ProblemPicMapper problemPicMapper;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Test
    public void batchInsertTest(){
        Assert.assertNotNull(problemPicMapper);
        List<ProblemPic> pics = mockList(2);
        int i = problemPicMapper.batchInsert(pics);
        Assert.assertTrue(i == 2);
    }

    @Test
    public void deleteTest() {
        long problemId = 570627101791617024L;
        int i = problemPicMapper.deleteByProblemId(problemId);
        Assert.assertTrue(i > 0);
    }

    @Test
    public void batchGetTest() {
        List<Long> ids = Lists.newArrayList();
        ids.add(570627572686127104L);
        ids.add(570627572686127105L);
        List<ProblemPic> pics = problemPicMapper.batchGet(ids);
        System.out.println(JSON.toJSONString(pics));
    }

    private List<ProblemPic> mockList(int size) {
        List<ProblemPic> pics = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ProblemPic problemPic = new ProblemPic();
            problemPic.setProblemId(snowflakeIdGenerator.generateId());
            problemPic.setUrl("http://xximg1.meitudata.com/g3Xpw5OUOVazL89LNJZi0xPGa2WxY.jpg");
            pics.add(problemPic);
        }
        return pics;
    }
}

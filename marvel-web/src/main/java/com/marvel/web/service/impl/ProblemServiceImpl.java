package com.marvel.web.service.impl;

import com.google.common.collect.Lists;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.mapper.ProblemMapper;
import com.marvel.web.mapper.ProblemPicMapper;
import com.marvel.web.po.Problem;
import com.marvel.web.po.ProblemPic;
import com.marvel.web.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Classname ProblemServiceImpl
 * @Description 问题操作实现类
 * @Date 2019/4/23 下午11:27
 * @Author zhongjie
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemMapper problemMapper;

    @Resource
    private ProblemPicMapper problemPicMapper;

    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public Problem save(RequestContext requestContext, Problem problem) {
        problem.setId(snowflakeIdGenerator.generateId());
        problem.setStatus(1);
        problem.setUpdateTime(System.currentTimeMillis());

        ArrayList<ProblemPic> pics = Lists.newArrayList();
        problem.getPics().stream().forEach(pic -> {
            ProblemPic problemPic = new ProblemPic();
            problemPic.setProblemId(problem.getId());
            problemPic.setUrl(pic);
            pics.add(problemPic);
        });
        int result = problemPicMapper.save(pics);
        if (result > 1) {
            result = problemMapper.save(problem);
        }
        if (result < 1) {
            //
        }
        return problem;
    }

    @Override
    public boolean update(RequestContext requestContext, Problem convert) {
        return true;
    }
}

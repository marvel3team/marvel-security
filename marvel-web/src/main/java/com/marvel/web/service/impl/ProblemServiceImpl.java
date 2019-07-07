package com.marvel.web.service.impl;

import com.google.common.collect.Lists;
import com.marvel.common.models.PageBean;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.ProblemMapper;
import com.marvel.web.mapper.ProblemPicMapper;
import com.marvel.web.po.Problem;
import com.marvel.web.po.ProblemPic;
import com.marvel.web.service.ProblemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        int result = problemPicMapper.batchInsert(getList(problem));
        if (result >= 1) {
            result = problemMapper.save(problem);
        }
        if (result < 1
        ) {
            throw BusinessException.SAVE_ERROR;
        }
        return problem;
    }

    @Override
    public boolean update(RequestContext requestContext, Problem problem) {
        Long problemId = problem.getId();
        Problem originProblem = problemMapper.findById(problemId);
        if (Objects.isNull(originProblem)) {
            throw BusinessException.DATA_NOT_EXISTS;
        }
        replaceProblem(problem, originProblem);
        if (!CollectionUtils.isEmpty(problem.getPics())) {
            problemPicMapper.deleteByProblemId(problemId);
        }
        problemPicMapper.batchInsert(getList(problem));
        int update = problemMapper.update(problem);
        return update > 0;
    }

    @Override
    public PageBean<Problem> getByPage(RequestContext requestContext, Integer status, Long planId, Long cursor, Integer count) {
        PageBean<Problem> result = new PageBean<>();
        result.setCount(count);
        if (status == null || status <= 0) {
            result.setNextCursor(-1L);
            result.setList(Lists.newArrayList());
            return result;
        }
        List<Problem> list = problemMapper.findByPage(status, planId, cursor, count);
        if (CollectionUtils.isEmpty(list)) {
            result.setNextCursor(-1L);
            result.setList(Lists.newArrayList());
            return result;
        }
        List<Long> problemIds = list.stream().map(Problem::getId).collect(Collectors.toList());
        List<ProblemPic> picList = problemPicMapper.batchGet(problemIds);
        if (!CollectionUtils.isEmpty(picList)) {
            Map<Long, List<String>> mapList = picList.stream().collect(Collectors.groupingBy(ProblemPic::getProblemId, Collectors.mapping(ProblemPic::getUrl, Collectors.toList())));
            list.stream().forEach(problem -> {
                if (mapList.containsKey(problem.getId())) {
                    problem.setPics(mapList.get(problem.getId()));
                }
            });
        }
        if (list.size() < count) {
            result.setNextCursor(-1L);
        } else {
            result.setNextCursor(list.get(list.size() - 1).getId());
        }
        result.setList(list);
        return result;
    }

    /**
     * 更新最新内容
     * @param problem
     * @param originProblem
     */
    private void replaceProblem(Problem problem, Problem originProblem) {
        if (problem.getPlanId() == null || problem.getPlanId() <= 0) {
            problem.setPlanId(originProblem.getPlanId());
        }
        if (problem.getExpertId() == null || problem.getExpertId() <= 0) {
            problem.setExpertId(originProblem.getExpertId());
        }
        if (problem.getProjectId() == null || problem.getProjectId() <= 0) {
            problem.setProjectId(originProblem.getProjectId());
        }
        if (StringUtils.isBlank(problem.getProjectName())) {
            problem.setProjectName(originProblem.getProjectName());
        }
        if (problem.getRuleId() == null || problem.getRuleId() <= 0) {
            problem.setRuleId(originProblem.getRuleId());
        }
        if (StringUtils.isBlank(problem.getProblemContent())) {
            problem.setProblemContent(originProblem.getProblemContent());
        }
        if (StringUtils.isBlank(problem.getCorrectiveAction())) {
            problem.setCorrectiveAction(originProblem.getCorrectiveAction());
        }
        if (problem.getTerm() == null || problem.getTerm() <= 0) {
            problem.setTerm(originProblem.getTerm());
        }
        if (problem.getStatus() == null || problem.getStatus() <=0) {
            problem.setStatus(originProblem.getStatus());
        }
        problem.setUpdateTime(System.currentTimeMillis());
    }

    /**
     * 获取问题图片集
     * @param problem
     * @return
     */
    private List<ProblemPic> getList(Problem problem) {
        ArrayList<ProblemPic> pics = Lists.newArrayList();
        problem.getPics().stream().forEach(pic -> {
            ProblemPic problemPic = new ProblemPic();
            problemPic.setProblemId(problem.getId());
            problemPic.setUrl(pic);
            pics.add(problemPic);
        });
        return pics;
    }

}

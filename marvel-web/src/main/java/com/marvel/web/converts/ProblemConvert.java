package com.marvel.web.converts;

import com.marvel.web.controller.req.RectifyProblemReq;
import com.marvel.web.po.Problem;

/**
 * @Classname ProblemConvert
 * @Description TODO
 * @Date 2019/4/23 下午11:39
 * @Author zhongjie
 */
public class ProblemConvert {

    public static Problem convert(RectifyProblemReq req) {
        Problem problem = new Problem();
        problem.setPlanId(req.getPlanId());
        problem.setExpertId(req.getExpertId());
        problem.setProjectId(req.getProjectId());
        problem.setProjectName(req.getProjectName());
        problem.setRuleId(req.getRuleId());
        problem.setRuleName(req.getRuleName());
        problem.setId(req.getProblemId());
        problem.setProblemContent(req.getProblemName());
        problem.setCorrectiveAction(req.getCorrectiveAction());
        problem.setPics(req.getProblemPics());
        problem.setTerm(req.getTerm());
        problem.setStatus(req.getStatus());
        return problem;
    }
}

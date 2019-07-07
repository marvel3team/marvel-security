package com.marvel.web.service;

import com.marvel.common.models.PageBean;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.po.Problem;

/**
 * @Classname ProblemService
 * @Description 问题操作接口类
 * @Date 2019/4/23 下午11:26
 * @Author zhongjie
 */
public interface ProblemService {
    Problem save(RequestContext requestContext, Problem convert);

    boolean update(RequestContext requestContext, Problem convert);

    PageBean<Problem> getByPage(RequestContext requestContext, Integer status, Long planId, Long cursor, Integer count);
}

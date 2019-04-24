package com.marvel.web.service;

import com.marvel.common.models.PageBean;
import com.marvel.web.vo.ExpertInfoVo;
import com.marvel.web.vo.PlanDetailVo;

/**
 * @Classname ExpertService
 * @Description
 * @Author andy
 * @Date 2019/4/22 下午11:48
 * @Version 1.0
 */
public interface ExpertService {


    /**
     * 分页查询专家信息
     * @param cursor
     * @param count
     * @return
     */
    PageBean<ExpertInfoVo> getExpertList(Integer cursor, Integer count);

    /**
     * 查询专家信息
     * @param id
     * @return
     */
    ExpertInfoVo getExpertInfo(Long id);

    /**
     * 分页查询专家下的计划
     * @param id
     * @param cursor
     * @param count
     * @return
     */
    PageBean<PlanDetailVo> getExpertPlanList(Long id, Integer cursor, Integer count);
}

package com.marvel.web.service;

import com.marvel.common.models.PageBean;
import com.marvel.web.vo.ExpertInfoVo;

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
}

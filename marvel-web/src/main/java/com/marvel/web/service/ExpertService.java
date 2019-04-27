package com.marvel.web.service;

import com.marvel.common.models.PageBean;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.controller.req.WorkTimeReq;
import com.marvel.web.po.ExpertInfo;
import com.marvel.web.po.ExpertTime;
import com.marvel.web.vo.ExpertInfoVo;
import com.marvel.web.vo.PlanDetailVo;

import java.util.List;

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

    /**
     * Description: 更新专家信息
     *
     * @param requestContext
     * @param convert
     * @return
     * @Date 下午11:04 2019/4/27
     * @Author zhongjie
     **/
    boolean update(RequestContext requestContext, ExpertInfo convert);

    /**
     * Description: 修改专家时间
     *
     * @param requestContext
     * @param id
     * @param workTimes
     * @return
     * @Date 上午12:19 2019/4/28
     * @Author zhongjie
     **/
    boolean updateExpertTime(RequestContext requestContext, Long id, List<WorkTimeReq> workTimes);

    /**
     * 获取专家时间列表
     * @param requestContext
     * @param id
     * @return
     */
    List<ExpertTime> getExpertTimes(RequestContext requestContext, Long id);

    /**
     * 设置专家时间列表
     * @param requestContext
     * @param id
     * @param workTimes
     * @return
     */
    boolean createExpertTime(RequestContext requestContext, Long id, List<WorkTimeReq> workTimes);
}

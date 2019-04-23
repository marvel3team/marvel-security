package com.marvel.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.enums.PlanStatus;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.PlanMapper;
import com.marvel.web.po.Plan;
import com.marvel.web.service.PlanService;
import com.marvel.web.vo.RemoteInfoReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname PlanServiceImpl
 * @Description
 * @Author andy
 * @Date 2019/4/23 下午9:37
 * @Version 1.0
 */
@Service
public class PlanServiceImpl implements PlanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanServiceImpl.class);

    @Resource
    private PlanMapper planMapper;
    @Resource
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public boolean createRemoteInfo(RemoteInfoReqVo remoteInfo) {
        Long bureauId = RequestContext.getRequestContext().getUid();
        try {
            Long planId = snowflakeIdGenerator.generateId();
            Plan plan = new Plan();
            plan.setCompanyId(remoteInfo.getCompanyId());
            plan.setId(planId);
            plan.setBureauId(bureauId);
            plan.setPlanSubject(remoteInfo.getPlanSubject());
            plan.setSuperversionLevel(remoteInfo.getSuperversionLevel());
            plan.setTimeSlot(remoteInfo.getTimeSlat());
            plan.setPlanTime(remoteInfo.getPlanTime());
            plan.setStatus(PlanStatus.BUREAU.value());
            int insert = planMapper.insert(plan);
            if (insert > 0 ){
                return true;
            }
        }catch (Exception e){
            LOGGER.error("PlanService-->createRemoteInfo-->exception, remoteInfo:{}", JSON.toJSON(remoteInfo),e);
        }
        return false;
    }

    @Override
    public boolean updateRemoteInfo(RemoteInfoReqVo remoteInfo) {
        try {
            Plan plan = planMapper.selectById(remoteInfo.getId());
            if (null == plan){
                throw BusinessException.PLAN_NOT_EXISTS;
            }
            plan.setCompanyId(remoteInfo.getCompanyId());
            plan.setPlanSubject(remoteInfo.getPlanSubject());
            plan.setSuperversionLevel(remoteInfo.getSuperversionLevel());
            plan.setTimeSlot(remoteInfo.getTimeSlat());
            plan.setPlanTime(remoteInfo.getPlanTime());
            plan.setStatus(remoteInfo.getStatus());
            int update = planMapper.update(plan);
            if (update > 0 ){
                return true;
            }
        }catch (Exception e){
            LOGGER.error("PlanService-->updateRemoteInfo-->exception, remoteInfo:{}", JSON.toJSON(remoteInfo),e);
        }
        return false;
    }
}

package com.marvel.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.enums.PlanStatus;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.PlanMapper;
import com.marvel.web.mapper.RespondPlanMapper;
import com.marvel.web.po.Plan;
import com.marvel.web.po.RespondPlan;
import com.marvel.web.service.PlanService;
import com.marvel.web.vo.RemoteInfoReqVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private RespondPlanMapper respondPlanMapper;
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
            plan.setName(remoteInfo.getName());
            plan.setTypeName(remoteInfo.getTypeName());
            plan.setProvince(remoteInfo.getProvince());
            plan.setCity(remoteInfo.getCity());
            plan.setOtherCity(remoteInfo.getOtherCity());
            plan.setServiceContent(remoteInfo.getServiceContent());
            plan.setDomain(remoteInfo.getDomain());
            plan.setDomainDetails(remoteInfo.getDomainDetails());
            plan.setDomainMince(remoteInfo.getDomainMince());
            plan.setServiceType(remoteInfo.getServiceType());
            plan.setInvestigationCompany(remoteInfo.getInvestigationCompany());

            List<Long> expertIds = remoteInfo.getExpertIds();
            if (!CollectionUtils.isEmpty(expertIds)) {
                List<RespondPlan> respondPlans = assembleRespondPlans(plan, expertIds);
                int insertRows = respondPlanMapper.batchInsert(respondPlans);
                if (insertRows == 0) {
                    return false;
                }
            }
            int insert = planMapper.insert(plan);
            if (insert > 0 ){
                return true;
            }
        }catch (Exception e){
            LOGGER.error("PlanService-->createRemoteInfo-->exception, remoteInfo:{}", JSON.toJSON(remoteInfo),e);
        }
        return false;
    }

    /**
     * 组装
     * @param plan
     * @param expertIds
     * @return
     */
    private List<RespondPlan> assembleRespondPlans(Plan plan, List<Long> expertIds) {
        List<RespondPlan> respondPlans = new ArrayList<>(expertIds.size());
        expertIds.stream().forEach(expertId -> {
            RespondPlan respondPlan = new RespondPlan();
            respondPlan.setPlanId(plan.getId());
            respondPlan.setExpertId(expertId);
            respondPlan.setType(1);
            respondPlan.setStatus(plan.getStatus());
            respondPlan.setPlanTime(plan.getPlanTime());
            respondPlan.setRemark(StringUtils.EMPTY);
            respondPlans.add(respondPlan);
        });
        return respondPlans;
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
            plan.setName(remoteInfo.getName());
            plan.setTypeName(remoteInfo.getTypeName());
            plan.setProvince(remoteInfo.getProvince());
            plan.setCity(remoteInfo.getCity());
            plan.setOtherCity(remoteInfo.getOtherCity());
            plan.setServiceContent(remoteInfo.getServiceContent());
            plan.setDomain(remoteInfo.getDomain());
            plan.setDomainDetails(remoteInfo.getDomainDetails());
            plan.setDomainMince(remoteInfo.getDomainMince());
            plan.setServiceType(remoteInfo.getServiceType());
            plan.setInvestigationCompany(remoteInfo.getInvestigationCompany());

            int update = planMapper.update(plan);
            if (update > 0 ){
                respondPlanMapper.updateStatus(plan.getId(), plan.getStatus(), System.currentTimeMillis());
                return true;
            }
        }catch (Exception e){
            LOGGER.error("PlanService-->updateRemoteInfo-->exception, remoteInfo:{}", JSON.toJSON(remoteInfo),e);
        }
        return false;
    }
}

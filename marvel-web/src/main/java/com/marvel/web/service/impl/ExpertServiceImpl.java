package com.marvel.web.service.impl;

import com.google.common.collect.Lists;
import com.marvel.common.models.PageBean;
import com.marvel.common.utils.PaginationUtils;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.controller.req.WorkTimeReq;
import com.marvel.web.enums.Sex;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.*;
import com.marvel.web.po.*;
import com.marvel.web.service.ExpertService;
import com.marvel.web.vo.ExpertInfoVo;
import com.marvel.web.vo.PlanDetailVo;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname ExpertServiceImpl
 * @Description
 * @Author andy
 * @Date 2019/4/22 下午11:48
 * @Version 1.0
 */
@Service
public class ExpertServiceImpl implements ExpertService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpertServiceImpl.class);

    @Resource
    private ExpertInfoMapper expertInfoMapper;
    @Resource
    private CompanyIndustryMapper companyIndustryMapper;
    @Resource
    private CompanyStandardMapper companyStandardMapper;
    @Resource
    private ExpertPlanMapper expertPlanMapper;
    @Resource
    private BureauMapper bureauMapper;
    @Resource
    private ExpertTimeMapper expertTimeMapper;


    @Override
    public PageBean<ExpertInfoVo> getExpertList(Long cursor, Integer count) {
        try {
            long total = expertInfoMapper.getExpertCount();
            if (total == 0) {
                return assemblePageBean(-1, new ArrayList<>());
            }
            List<ExpertInfo> expertInfos = expertInfoMapper.getExpertListByPage(cursor, count);
            if (null == expertInfos || expertInfos.size() == 0) {
                return assemblePageBean(-1, new ArrayList<>());
            }
            //查询公司id
            List<Long> companyIds = expertInfos.stream().map(temp -> {
                return temp.getCompanyId();
            }).collect(Collectors.toList());
            //根据公司id查询公司的行业
            List<CompanyStandard> companyStandards = companyStandardMapper.getCompanyByIds(companyIds);

            Map<Long, CompanyIndustry> industryMap = new HashMap<>();

            if (null != companyStandards && companyStandards.size() > 0) {
                //根据行业id，查询行业信息
                List<Long> industryIds = companyStandards.stream().map(temp -> {
                    return temp.getIndustryId();
                }).collect(Collectors.toList());
                List<CompanyIndustry> industryList = companyIndustryMapper.getIndustryByIds(industryIds);
                Map<Long, CompanyIndustry> industryIdMap = new HashMap<>();
                if (null != industryList && industryList.size() > 0) {
                    industryIdMap = industryList.stream().collect(Collectors.toMap(CompanyIndustry::getId, temp -> temp, (e1, e2) -> e1));
                }
                for (CompanyStandard companyStandard : companyStandards) {
                    CompanyIndustry companyIndustry = industryIdMap.getOrDefault(companyStandard.getIndustryId(), null);
                    if (null != companyIndustry) {
                        industryMap.put(companyStandard.getId(), companyIndustry);
                    }
                }
            }
            List<ExpertInfoVo> resultList = new ArrayList<>();
            for (ExpertInfo expertInfo : expertInfos) {

                CompanyIndustry companyIndustry = industryMap.getOrDefault(expertInfo.getCompanyId(), null);
                ExpertInfoVo expertInfoVo = new ExpertInfoVo();
                expertInfoVo.setId(expertInfo.getId());
                expertInfoVo.setName(expertInfo.getName());
                expertInfoVo.setIdCardNo(expertInfo.getIdCardNo());
                expertInfoVo.setWorkCompany(expertInfo.getWorkCompany());
                expertInfoVo.setWorkAddress(expertInfo.getWorkAddress());
                expertInfoVo.setWorkLife(expertInfo.getWorkLife());
                expertInfoVo.setPositionalTitle(expertInfo.getPositionalTitle());
                expertInfoVo.setIsSyndic(expertInfo.getIsSyndic());
                expertInfoVo.setLevel(expertInfo.getLevel());
                expertInfoVo.setEvaluateRange(expertInfo.getEvaluateRange());
                expertInfoVo.setCollage(expertInfo.getCollage());
                expertInfoVo.setHomeAddress(expertInfo.getHomeAddress());
                expertInfoVo.setMobile(expertInfo.getMobile());
                expertInfoVo.setRemark(expertInfo.getRemark());
                //TODO 需要组装地址
                expertInfoVo.setSignUrl(expertInfo.getSignUrl());
                expertInfoVo.setSex(Sex.valueOf(expertInfo.getSex()) == null ? "未知" : Sex.valueOf(expertInfo.getSex()).desc());
                expertInfoVo.setHonor(expertInfo.getHonor());
                expertInfoVo.setHighestDegree(expertInfo.getHighestDegree());
                expertInfoVo.setJobResume(expertInfo.getJobResume());
                expertInfoVo.setNation(expertInfo.getNation());
                expertInfoVo.setCategories(expertInfo.getCategories());
                expertInfoVo.setIndustry(null == companyIndustry ? "" : companyIndustry.getContent());
                //TODO
                expertInfoVo.setArea("");
                resultList.add(expertInfoVo);
            }
            if (CollectionUtils.isEmpty(resultList)) {
                return assemblePageBean(-1, new ArrayList<>());
            }
            if (resultList.size() < count) {
                return assemblePageBean(-1, resultList);
            }
            return assemblePageBean(resultList.get(resultList.size() - 1).getId(), resultList);
        } catch (Exception e) {
            LOGGER.error("ExpertService-->getExpertList-->exception,cursor:{},count:{}", cursor, count, e);
        }
        return assemblePageBean(-1, new ArrayList<>());
    }

    @Override
    public ExpertInfoVo getExpertInfo(Long id) {
        ExpertInfo expertInfo = expertInfoMapper.getExpertInfoById(id);
        if (null == expertInfo) {
            throw BusinessException.EXPERT_NOT_EXISTS;
        }
        ExpertInfoVo expertInfoVo = new ExpertInfoVo();
        expertInfoVo.setId(expertInfo.getId());
        expertInfoVo.setName(expertInfo.getName());
        expertInfoVo.setIdCardNo(expertInfo.getIdCardNo());
        expertInfoVo.setWorkCompany(expertInfo.getWorkCompany());
        expertInfoVo.setWorkAddress(expertInfo.getWorkAddress());
        expertInfoVo.setWorkLife(expertInfo.getWorkLife());
        expertInfoVo.setPositionalTitle(expertInfo.getPositionalTitle());
        expertInfoVo.setIsSyndic(expertInfo.getIsSyndic());
        expertInfoVo.setLevel(expertInfo.getLevel());
        expertInfoVo.setEvaluateRange(expertInfo.getEvaluateRange());
        expertInfoVo.setCollage(expertInfo.getCollage());
        expertInfoVo.setHomeAddress(expertInfo.getHomeAddress());
        expertInfoVo.setMobile(expertInfo.getMobile());
        expertInfoVo.setRemark(expertInfo.getRemark());
        //TODO 需要组装地址
        expertInfoVo.setSignUrl(expertInfo.getSignUrl());
        expertInfoVo.setSex(Sex.valueOf(expertInfo.getSex()) == null ? "未知" : Sex.valueOf(expertInfo.getSex()).desc());
        expertInfoVo.setHonor(expertInfo.getHonor());
        expertInfoVo.setHighestDegree(expertInfo.getHighestDegree());
        expertInfoVo.setJobResume(expertInfo.getJobResume());
        expertInfoVo.setNation(expertInfo.getNation());
        expertInfoVo.setCategories(expertInfo.getCategories());
        CompanyStandard companyStandard = companyStandardMapper.getCompanyById(expertInfo.getCompanyId());
        if (null == companyStandard) {
            throw BusinessException.EXPERT_NOT_EXISTS;
        }
        CompanyIndustry companyIndustry = companyIndustryMapper.getIndustryById(companyStandard.getIndustryId());
        if (null == companyIndustry) {
            return expertInfoVo;
        }
        expertInfoVo.setIndustry(companyIndustry.getContent());
        //TODO 需要添加地区表
        expertInfoVo.setArea("");
        return expertInfoVo;
    }

    @Override
    public PageBean<PlanDetailVo> getExpertPlanList(Long id, Long cursor, Integer count) {
        long total = expertPlanMapper.getExpertPlanCount(id);
        if (total == 0) {
            return assembleExpertPlanPage(-1, new ArrayList<>());
        }
        List<PlanDetailVo> planDetailVos = expertPlanMapper.getExpertPlanList(id, count, count);
        if (CollectionUtils.isEmpty(planDetailVos)) {
            return assembleExpertPlanPage(-1, new ArrayList<>());
        }
        List<Long> bureauIds = planDetailVos.stream().map(temp -> {
            return temp.getBureauId();
        }).collect(Collectors.toList());

        List<Bureau> bureauList = bureauMapper.getBureauByIds(bureauIds);
        Map<Long, Bureau> bureauMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(bureauList)) {
            bureauMap = bureauList.stream().collect(Collectors.toMap(Bureau::getId, temp -> temp, (e1, e2) -> e1));
        }
        List<PlanDetailVo> result = new ArrayList<>();
        for (PlanDetailVo planDetailVo : planDetailVos) {
            Bureau bureau = MapUtils.getObject(bureauMap, planDetailVo.getBureauId(), null);
            if (null == bureau) {
                planDetailVo.setBureauName("");
            } else {
                planDetailVo.setBureauName(bureau.getName());
            }
            result.add(planDetailVo);
        }
        if (CollectionUtils.isEmpty(result)) {
            return assembleExpertPlanPage(-1, new ArrayList<>());
        }
        if (result.size() < count) {
            return assembleExpertPlanPage(-1, result);
        }
        return assembleExpertPlanPage(result.get(result.size() - 1).getId(), result);
    }

    @Override
    public boolean update(RequestContext requestContext, ExpertInfo expert) {
        Long id = expert.getId();
        ExpertInfo originExpert = expertInfoMapper.getExpertInfoById(id);
        if (Objects.isNull(originExpert)) {
            throw BusinessException.DATA_NOT_EXISTS;
        }
        replaceExpert(expert, originExpert);
        int update = expertInfoMapper.update(expert);
        return update > 0;
    }

    @Override
    public boolean updateExpertTime(RequestContext requestContext, Long id, List<WorkTimeReq> workTimes) {
        ExpertInfo expertInfo = expertInfoMapper.getExpertInfoById(id);
        if (Objects.isNull(expertInfo)) {
            throw BusinessException.EXPERT_NOT_EXISTS;
        }
        List<ExpertTime> expertTimes = expertTimeMapper.findByExpertId(id);
        if (!CollectionUtils.isEmpty(expertTimes)) {
            expertTimeMapper.deleteByExpertId(id);
        }
        List<ExpertTime> list = assembleExpertTimes(expertInfo, workTimes);
        int update = expertTimeMapper.batchInsert(list);
        return update > 0;
    }

    @Override
    public List<ExpertTime> getExpertTimes(RequestContext requestContext, Long id) {
        if (Objects.isNull(id) || id <= 0) {
            return Lists.newArrayList();
        }
        ExpertInfo expertInfo = expertInfoMapper.getExpertInfoById(id);
        if (Objects.isNull(expertInfo)) {
            return Lists.newArrayList();
        }
        List<ExpertTime> list = expertTimeMapper.findByExpertId(id);
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }

        return list;
    }

    @Override
    public boolean createExpertTime(RequestContext requestContext, Long id, List<WorkTimeReq> workTimes) {
        ExpertInfo expertInfo = expertInfoMapper.getExpertInfoById(id);
        if (Objects.isNull(expertInfo)) {
            throw BusinessException.EXPERT_NOT_EXISTS;
        }
        List<ExpertTime> list = assembleExpertTimes(expertInfo, workTimes);
        int result = expertTimeMapper.batchInsert(list);
        return result > 0;
    }

    /**
     * 组装数据
     *
     * @param expertInfo
     * @param workTimes
     * @return
     */
    private List<ExpertTime> assembleExpertTimes(ExpertInfo expertInfo, List<WorkTimeReq> workTimes) {
        List<ExpertTime> list = Lists.newArrayList();
        workTimes.stream().forEach(workTimeReq -> {
            ExpertTime expertTime = new ExpertTime();
            expertTime.setExpertId(expertInfo.getId());
            expertTime.setExpertType(1);
            expertTime.setStartTime(workTimeReq.getStartTime());
            expertTime.setEndTime(workTimeReq.getEndTime());
            list.add(expertTime);
        });
        return list;
    }

    /**
     * 更新最新内容
     *
     * @param expert
     * @param originExpert
     */
    private void replaceExpert(ExpertInfo expert, ExpertInfo originExpert) {
        if (StringUtils.isBlank(expert.getName())) {
            expert.setName(originExpert.getName());
        }
        if (StringUtils.isBlank(expert.getIdCardNo())) {
            expert.setIdCardNo(originExpert.getIdCardNo());
        }
        if (expert.getCompanyId() == null || expert.getCompanyId() <= 0) {
            expert.setCompanyId(originExpert.getCompanyId());
        }
        if (StringUtils.isBlank(expert.getWorkCompany())) {
            expert.setWorkCompany(originExpert.getWorkCompany());
        }
        if (StringUtils.isBlank(expert.getWorkAddress())) {
            expert.setWorkAddress(originExpert.getWorkAddress());
        }
        if (expert.getWorkLife() == null || expert.getWorkLife() <= 0) {
            expert.setWorkLife(originExpert.getWorkLife());
        }
        if (StringUtils.isBlank(expert.getPositionalTitle())) {
            expert.setPositionalTitle(originExpert.getPositionalTitle());
        }
        if (expert.getIsSyndic() == null || expert.getIsSyndic() <= 0) {
            expert.setIsSyndic(originExpert.getIsSyndic());
        }
        if (expert.getLevel() == null || expert.getLevel() <= 0) {
            expert.setLevel(originExpert.getLevel());
        }
        if (StringUtils.isBlank(expert.getEvaluateRange())) {
            expert.setEvaluateRange(originExpert.getEvaluateRange());
        }
        if (StringUtils.isBlank(expert.getCollage())) {
            expert.setCollage(originExpert.getCollage());
        }
        if (StringUtils.isBlank(expert.getHomeAddress())) {
            expert.setHomeAddress(originExpert.getHomeAddress());
        }
        if (StringUtils.isBlank(expert.getMobile())) {
            expert.setMobile(originExpert.getMobile());
        }
        if (StringUtils.isBlank(expert.getRemark())) {
            expert.setRemark(originExpert.getRemark());
        }
        if (StringUtils.isBlank(expert.getSignUrl())) {
            expert.setSignUrl(originExpert.getSignUrl());
        }
    }


    /**
     * 组装返回数据
     *
     * @param nextCursor
     * @param listVos
     * @return
     */
    private PageBean<ExpertInfoVo> assemblePageBean(long nextCursor, List<ExpertInfoVo> listVos) {
        PageBean<ExpertInfoVo> pageBean = new PageBean<>();
        pageBean.setNextCursor(nextCursor);
        pageBean.setList(listVos);
        return pageBean;
    }


    /**
     * 组装分页数据
     *
     * @param nextCursor
     * @param listVos
     * @return
     */
    private PageBean<PlanDetailVo> assembleExpertPlanPage(long nextCursor, List<PlanDetailVo> listVos) {
        PageBean<PlanDetailVo> pageBean = new PageBean<>();
        pageBean.setNextCursor(nextCursor);
        pageBean.setList(listVos);
        return pageBean;
    }

}

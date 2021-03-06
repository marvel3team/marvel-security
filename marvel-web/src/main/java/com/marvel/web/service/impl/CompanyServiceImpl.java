package com.marvel.web.service.impl;

import com.google.common.collect.Lists;
import com.marvel.common.models.PageBean;
import com.marvel.common.utils.PaginationUtils;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.enums.IndustryType;
import com.marvel.web.enums.SafetyLevel;
import com.marvel.web.enums.UserType;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.*;
import com.marvel.web.po.*;
import com.marvel.web.service.CompanyService;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyInfoReqVo;
import com.marvel.web.vo.CompanyListVo;
import com.marvel.web.vo.PlanDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname CompanyServiceImpl
 * @Description 企业信息相关接口
 * @Author andy
 * @Date 2019/4/22 下午10:13
 * @Version 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Resource
    private CompanyStandardMapper companyStandardMapper;
    @Resource
    private CompanyBaseMapper companyBaseMapper;
    @Resource
    private PlanMapper planMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RespondPlanMapper respondPlanMapper;
    @Resource
    private BureauMapper bureauMapper;
    @Resource
    private ServiceMapper serviceMapper;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public PageBean<CompanyListVo> getCompanyList(Long cursor, Integer count) {
        try {
            Long userId = RequestContext.getRequestContext().getUid();
            //根据id查询类型
            User user = userMapper.findByUid(userId);
            if (null == user) {
                throw BusinessException.USER_NO_EXISTS;
            }

            long total = companyStandardMapper.getCompanyCount(null);
            if (total == 0) {
                return assemblePageBean(-1, new ArrayList<>());
            }
            List<CompanyStandard> standardList = companyStandardMapper.getCompanyListPage(null, cursor, count);
            if (CollectionUtils.isEmpty(standardList)) {
                return assemblePageBean(-1, new ArrayList<>());
            }

            if (standardList.size() < count) {
                return assemblePageBean(-1, standardList);
            }
            return assemblePageBean(standardList.get(standardList.size() - 1).getId(), standardList);
        } catch (Exception e) {
            LOGGER.error("CompanyService-->getCompanyList-->exception,cursor:{},count:{}", cursor, count, e);
            throw BusinessException.QUERY_DB_ERROR;
        }

    }

    @Override
    public CompanyDetailVo getCompanyInfo(Long id) {
        CompanyDetailVo companyDetailVo = new CompanyDetailVo();
        try {
            CompanyStandard companyStandard = companyStandardMapper.getCompanyById(id);
            if (null == companyStandard) {
                throw BusinessException.COMPANY_NOT_EXISTS;
            }
            companyDetailVo.setId(id);
            companyDetailVo.setName(companyStandard.getName());
            companyDetailVo.setAreaId(companyStandard.getAreaId());
            companyDetailVo.setRegistedCapital(new BigDecimal(companyStandard.getRegistedCapital()).divide(new BigDecimal(100)).toPlainString());
            companyDetailVo.setLegalPerson(companyStandard.getLegalPerson());
            companyDetailVo.setMobile(companyStandard.getLegalPersonMobile());
            companyDetailVo.setBusinessCode(companyStandard.getBusinessTypeCode());
            companyDetailVo.setEmail(companyStandard.getEmail());
            companyDetailVo.setBusinessLicenseId(companyStandard.getBusinessLicenseId());
            CompanyBase companyBase = companyBaseMapper.getCompanBaseById(id);
            if (null == companyBase) {
                return companyDetailVo;
            }
            companyDetailVo.setProductionDay(companyBase.getProductionDay());
            companyDetailVo.setTotalInvest(new BigDecimal(companyBase.getTotalInvest()).divide(new BigDecimal(100)).toPlainString());
            companyDetailVo.setFixedInvest(new BigDecimal(companyBase.getFixedInvest()).divide(new BigDecimal(100)).toPlainString());
            companyDetailVo.setLastYearIncome(new BigDecimal(companyBase.getLastYearIncome()).divide(new BigDecimal(100)).toPlainString());
            companyDetailVo.setFactoryArea(companyBase.getFactoryArea());
            companyDetailVo.setCoveredArea(companyBase.getCoveredArea());
            companyDetailVo.setRawStockDosage(companyBase.getRawStockDosage());
            companyDetailVo.setAccessoriesDosage(companyBase.getAccessoriesDosage());
            companyDetailVo.setYearlyCapacity(companyBase.getYearlyCapacity());
            companyDetailVo.setOutputUnit(companyBase.getOutputUnit());
            companyDetailVo.setIsSpecialEquipment(companyBase.getIsSpecialEquipment());
            companyDetailVo.setIsDistributionRoom(companyBase.getIsDistributionRoom());
            companyDetailVo.setIsTransformer(companyBase.getIsTransformer());
            companyDetailVo.setIsStanCertificate(companyBase.getIsStanCertificate());
            companyDetailVo.setStanCertificateId(companyBase.getStanCertificateId());
            companyDetailVo.setStanCertificateType(companyBase.getStanCertificateType());
            companyDetailVo.setIsDeclareOnline(companyBase.getIsDeclareOnline());
            companyDetailVo.setIsSafeProof(companyBase.getIsSafeProof());
            companyDetailVo.setSafeProofArchiveNo(companyBase.getSafeProofArchiveNo());
            companyDetailVo.setWorkSystem(companyBase.getWorkSystem());
            companyDetailVo.setProductionDepartmentPeoples(companyBase.getProductionDepartmentPeoples());
            companyDetailVo.setOfficePeoples(companyBase.getOfficePeoples());
            companyDetailVo.setWorkDaysYearly(companyBase.getYearlyWorkDays());
            companyDetailVo.setIndustryType(IndustryType.valueOf(companyBase.getIndustryType()) == null ? "" : IndustryType.valueOf(companyBase.getIndustryType()).desc());
            companyDetailVo.setSafetyLevel(SafetyLevel.valueOf(companyBase.getSafetyLevel()) == null ? "" : SafetyLevel.valueOf(companyBase.getSafetyLevel()).desc());
            companyDetailVo.setCertificateStartTime(companyBase.getCertificateStartTime());
            companyDetailVo.setCertificateEndTime(companyBase.getCertificateEndTime());
            companyDetailVo.setMajorRiskSources(companyBase.getMajorRiskSources());
            companyDetailVo.setHigherRiskSources(companyBase.getHigherRiskSources());
            companyDetailVo.setGeneralRiskSources(companyBase.getGeneralRiskSources());
            companyDetailVo.setLowRiskSources(companyBase.getLowRiskSources());

            return companyDetailVo;
        } catch (Exception e) {
            LOGGER.error("CompanyService-->getCompanyInfo-->exception,id:{}", id, e);
            throw BusinessException.QUERY_DB_ERROR;
        }
    }

    @Override
    public PageBean<PlanDetailVo> getPlanList(Long cursor, Integer count, Long id, String name, Integer status) {
        if (Objects.isNull(id) && id <= 0) {
            CompanyStandard companyStandard = companyStandardMapper.getCompanyByName(name);
            id = companyStandard.getId();
        }
        List<Plan> planList = planMapper.getCompanyPlanList(id, status, cursor, count);
        if (null == planList || planList.size() == 0) {
            return assemblePlanDetailPage(-1, new ArrayList<>());
        }
        List<Long> planIds = planList.stream().map(temp -> {
            return temp.getId();
        }).collect(Collectors.toList());
        List<RespondPlan> respondPlanList = respondPlanMapper.getRespondPlanByPlanIds(planIds);

        Map<Long, RespondPlan> respondPlanMap = new HashMap<>();
        if (null != respondPlanList && respondPlanList.size() > 0) {
            respondPlanMap = respondPlanList.stream().collect(Collectors.toMap(RespondPlan::getPlanId, temp -> temp, (e1, e2) -> e1));
        }

        List<Long> bureauIds = planList.stream().map(temp -> {
            return temp.getBureauId();
        }).collect(Collectors.toList());
        Map<Long, Bureau> bureauMap = new HashMap<>();

        List<Bureau> bureauList = bureauMapper.getBureauByIds(bureauIds);
        if (null != bureauList && bureauList.size() > 0) {
            bureauMap = bureauList.stream().collect(Collectors.toMap(Bureau::getId, temp -> temp, (e1, e2) -> e1));
        }

        List<Long> companyIds = planList.stream().map(temp -> {
            return temp.getCompanyId();
        }).collect(Collectors.toList());

        List<CompanyStandard> companyStandards = companyStandardMapper.getCompanyByIds(companyIds);
        Map<Long, CompanyStandard> companyStandardsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(companyStandards)) {
            companyStandardsMap = companyStandards.stream().collect(Collectors.toMap(CompanyStandard::getId, temp -> temp, (e1, e2) -> e1));
        }

        List<PlanDetailVo> resultList = new ArrayList<>();

        for (Plan plan : planList) {
            Bureau bureau = bureauMap.getOrDefault(plan.getBureauId(), null);
            RespondPlan respondPlan = respondPlanMap.getOrDefault(plan.getId(), null);

            PlanDetailVo planDetailVo = new PlanDetailVo();
            planDetailVo.setId(plan.getId());
            planDetailVo.setPlanName(plan.getName());
            CompanyStandard standard = companyStandardsMap.get(plan.getCompanyId());
            planDetailVo.setCompanyName(standard == null ? StringUtils.EMPTY : standard.getName());
            planDetailVo.setProvince(standard == null ? StringUtils.EMPTY : standard.getProvince());
            planDetailVo.setCity(standard == null ? StringUtils.EMPTY : standard.getCity());
            planDetailVo.setOtherCity(standard == null ? StringUtils.EMPTY : standard.getOtherCity());
            planDetailVo.setAddress(standard == null ? StringUtils.EMPTY : standard.getAddress());
            if (standard != null && standard.getIndustryId() > 0) {
                IndustryType industryType = IndustryType.valueOf(standard.getIndustryId().intValue());
                planDetailVo.setIndustryType(industryType == null ? StringUtils.EMPTY : industryType.desc());
            }
            planDetailVo.setDomain(plan.getDomain());
            planDetailVo.setDomainDetails(plan.getDomainDetails());
            planDetailVo.setDomainMince(plan.getDomainMince());
            planDetailVo.setLegalPerson(standard == null ? StringUtils.EMPTY : standard.getLegalPerson());
            planDetailVo.setLegalPersonMobile(standard == null ? StringUtils.EMPTY : standard.getLegalPersonMobile());
            planDetailVo.setBureauName(null == bureau ? "" : bureau.getName());
            if (null != respondPlan) {
                planDetailVo.setFinishStatus(respondPlan.getStatus());
                planDetailVo.setFinishTime(respondPlan.getPlanTime());
                planDetailVo.setRespodType(respondPlan.getType());
            }
            planDetailVo.setBureauId(plan.getBureauId());
            planDetailVo.setPlanStatus(plan.getStatus());
            planDetailVo.setSuperversionLevel(plan.getSuperversionLevel());
            planDetailVo.setTimeSlat(plan.getTimeSlot());
            planDetailVo.setPlanTime(plan.getPlanTime());
            resultList.add(planDetailVo);

        }
        long nextCursor = -1;
        if (planList.size() >= count) {
            nextCursor = planList.get(planList.size() - 1).getId();
        }
        return assemblePlanDetailPage(nextCursor, resultList);
    }

    @Override
    public PageBean<ServiceInfo> getServiceList(RequestContext requestContext, String serviceName, Long cursor, Integer count) {
        PageBean<ServiceInfo> result = new PageBean<>();
        result.setCount(count);
        List<ServiceInfo> list = serviceMapper.findByPage(serviceName, cursor, count);
        if (CollectionUtils.isEmpty(list)) {
            result.setNextCursor(-1L);
            result.setList(Lists.newArrayList());
            return result;
        }
        if (list.size() < count) {
            result.setNextCursor(-1L);
        } else {
            result.setNextCursor(list.get(list.size() - 1).getId());
        }
        result.setList(list);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateCompanyInfo(CompanyInfoReqVo companyInfoReqVo) {
        CompanyStandard companyStandard = companyStandardMapper.getCompanyById(companyInfoReqVo.getId());
        if (null == companyStandard) {
            throw BusinessException.COMPANY_NOT_EXISTS;
        }
        companyStandard.setAreaId(companyInfoReqVo.getAreaId() == null ? companyStandard.getAreaId() : companyInfoReqVo.getAreaId());
        companyStandard.setBusinessLicenseId(companyInfoReqVo.getBusinessCode());
        companyStandard.setBusinessLicenseId(companyInfoReqVo.getBusinessLicenseId());
        companyStandard.setRegistedCapital(StringUtils.isBlank(companyInfoReqVo.getRegistedCapital()) ? null : new BigDecimal(companyInfoReqVo.getRegistedCapital()).multiply(new BigDecimal(100)).longValue());
        companyStandard.setLegalPerson(companyInfoReqVo.getLegalPerson());
        companyStandard.setLegalPersonMobile(companyInfoReqVo.getMobile());
        companyStandard.setEmail(companyInfoReqVo.getEmail());
        companyStandard.setName(companyInfoReqVo.getName());
        companyStandard.setIndustryId(companyInfoReqVo.getIndustryId());

        int update = companyStandardMapper.updateCompanyStandard(companyStandard);
        if (update < 1) {
            throw BusinessException.UPDATE_ERROR;
        }
        boolean canInsert = false;
        boolean hasBase = false;
        CompanyBase companyBase = companyBaseMapper.getCompanBaseById(companyInfoReqVo.getId());
        if (null == companyBase && companyInfoReqVo.getProductionDay() != 0 && "0".equals(companyInfoReqVo.getTotalInvest())) {
            canInsert = true;
            companyBase = new CompanyBase();
            companyBase.setId(companyInfoReqVo.getId());
            hasBase = true;
        } else if (null != companyBase) {
            canInsert = false;
            hasBase = true;
        }else {
            companyBase = new CompanyBase();
        }

        companyBase.setProductionDay(companyInfoReqVo.getProductionDay());
        companyBase.setTotalInvest(new BigDecimal(companyInfoReqVo.getTotalInvest()).multiply(new BigDecimal(100)).longValue());
        companyBase.setFixedInvest(new BigDecimal(companyInfoReqVo.getFixedInvest()).multiply(new BigDecimal(100)).longValue());
        companyBase.setLastYearIncome(new BigDecimal(companyInfoReqVo.getLastYearIncome()).multiply(new BigDecimal(100)).longValue());
        companyBase.setFactoryArea(companyInfoReqVo.getFactoryArea());
        companyBase.setCoveredArea(companyInfoReqVo.getCoveredArea());
        companyBase.setRawStockDosage(companyInfoReqVo.getRawStockDosage());
        companyBase.setAccessoriesDosage(companyInfoReqVo.getAccessoriesDosage());
        companyBase.setYearlyCapacity(companyInfoReqVo.getYearlyCapacity());
        companyBase.setOutputUnit(companyInfoReqVo.getOutputUnit());
        companyBase.setIsSpecialEquipment(companyInfoReqVo.getIsSpecialEquipment());
        companyBase.setIsDistributionRoom(companyInfoReqVo.getIsDistributionRoom());
        companyBase.setIsTransformer(companyInfoReqVo.getIsTransformer());
        companyBase.setStanCertificateType(companyInfoReqVo.getStanCertificateType());
        companyBase.setStanCertificateId(companyInfoReqVo.getStanCertificateId());
        companyBase.setIsDeclareOnline(companyInfoReqVo.getIsDeclareOnline());
        companyBase.setIsSafeProof(companyInfoReqVo.getIsSafeProof());
        companyBase.setSafeProofArchiveNo(companyInfoReqVo.getSafeProofArchiveNo());
        companyBase.setWorkSystem(companyInfoReqVo.getWorkSystem());
        companyBase.setProductionDepartmentPeoples(companyInfoReqVo.getProductionDepartmentPeoples());
        companyBase.setOfficePeoples(companyInfoReqVo.getOfficePeoples());
        companyBase.setYearlyWorkDays(companyInfoReqVo.getWorkDaysYearly());
        companyBase.setIndustryType(companyInfoReqVo.getIndustryType());
        companyBase.setSafetyLevel(companyInfoReqVo.getSafetyLevel());
        companyBase.setCertificateStartTime(companyInfoReqVo.getCertificateStartTime());
        companyBase.setCertificateEndTime(companyInfoReqVo.getCertificateEndTime());
        companyBase.setMajorRiskSources(companyInfoReqVo.getMajorRiskSources());
        companyBase.setHigherRiskSources(companyInfoReqVo.getHigherRiskSources());
        companyBase.setGeneralRiskSources(companyInfoReqVo.getGeneralRiskSources());
        companyBase.setLowRiskSources(companyInfoReqVo.getLowRiskSources());

        int result = -1;
        if (canInsert && hasBase) {
            result = companyBaseMapper.insertCompanyBase(companyBase);
        } else if (!canInsert && hasBase) {
            result = companyBaseMapper.updateCompanyBase(companyBase);
        } else {
            return "{}";
        }
        if (result < 1) {
            throw BusinessException.UPDATE_ERROR;
        }

        return "{}";
    }

    @Override
    public void addService(String serviceName, String serviceDesc) {
        ServiceInfo serviceInfo = assembleServiceInfo(serviceName, serviceDesc);
        serviceMapper.save(serviceInfo);
    }

    @Override
    public boolean deleteService(Long serviceId) {
        return serviceMapper.deleteById(serviceId) > 0;
    }

    @Override
    public boolean addService(Long serviceId, String serviceName, String serviceDesc) {
        ServiceInfo serviceInfo = getService(serviceId);
        if (Objects.nonNull(serviceName)) {
            serviceInfo.setServiceName(serviceName);
        }
        if (Objects.nonNull(serviceDesc)) {
            serviceInfo.setServiceDesc(serviceDesc);
        }
        return serviceMapper.update(serviceInfo) > 0;
    }

    @Override
    public ServiceInfo getService(Long serviceId) {
        ServiceInfo serviceInfo = serviceMapper.queryById(serviceId);
        if (Objects.isNull(serviceInfo)) {
            throw BusinessException.SERVICE_NOT_EXISTS;
        }
        return serviceInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveCompanyInfo(CompanyInfoReqVo companyInfoReqVo) {

        Long userId = RequestContext.getRequestContext().getUid();
        //根据id查询类型
        User user = userMapper.findByUid(userId);
        if (null == user) {
            throw BusinessException.USER_NO_EXISTS;
        }
        Long id = snowflakeIdGenerator.generateId();
        if (id == null) {
            throw BusinessException.SAVE_ERROR;
        }
        CompanyStandard companyStandard = new CompanyStandard();
        companyStandard.setId(id);
        companyStandard.setAreaId(companyInfoReqVo.getAreaId());
        companyStandard.setBusinessLicenseId(companyInfoReqVo.getBusinessCode());
        companyStandard.setBusinessLicenseId(companyInfoReqVo.getBusinessLicenseId());
        companyStandard.setRegistedCapital(StringUtils.isBlank(companyInfoReqVo.getRegistedCapital()) ? 0 : new BigDecimal(companyInfoReqVo.getRegistedCapital()).multiply(new BigDecimal(100)).longValue());
        companyStandard.setLegalPerson(companyInfoReqVo.getLegalPerson());
        companyStandard.setType(user.getType());
        companyStandard.setLegalPersonMobile(companyInfoReqVo.getMobile());
        companyStandard.setEmail(companyInfoReqVo.getEmail());
        companyStandard.setName(companyInfoReqVo.getName());
        companyStandard.setIndustryId(companyInfoReqVo.getIndustryId());

        int insert = companyStandardMapper.save(companyStandard);
        if (insert < 0) {
            throw BusinessException.SAVE_ERROR;
        }
        if (companyInfoReqVo.getProductionDay() != 0 && "0".equals(companyInfoReqVo.getTotalInvest())) {
            CompanyBase companyBase = new CompanyBase();
            companyBase.setId(id);
            companyBase.setProductionDay(companyInfoReqVo.getProductionDay());
            companyBase.setTotalInvest(new BigDecimal(companyInfoReqVo.getTotalInvest()).multiply(new BigDecimal(100)).longValue());
            companyBase.setFixedInvest(new BigDecimal(companyInfoReqVo.getFixedInvest()).multiply(new BigDecimal(100)).longValue());
            companyBase.setLastYearIncome(new BigDecimal(companyInfoReqVo.getLastYearIncome()).multiply(new BigDecimal(100)).longValue());
            companyBase.setFactoryArea(companyInfoReqVo.getFactoryArea());
            companyBase.setCoveredArea(companyInfoReqVo.getCoveredArea());
            companyBase.setRawStockDosage(companyInfoReqVo.getRawStockDosage());
            companyBase.setAccessoriesDosage(companyInfoReqVo.getAccessoriesDosage());
            companyBase.setYearlyCapacity(companyInfoReqVo.getYearlyCapacity());
            companyBase.setOutputUnit(companyInfoReqVo.getOutputUnit());
            companyBase.setIsSpecialEquipment(companyInfoReqVo.getIsSpecialEquipment());
            companyBase.setIsDistributionRoom(companyInfoReqVo.getIsDistributionRoom());
            companyBase.setIsTransformer(companyInfoReqVo.getIsTransformer());
            companyBase.setStanCertificateType(companyInfoReqVo.getStanCertificateType());
            companyBase.setStanCertificateId(companyInfoReqVo.getStanCertificateId());
            companyBase.setIsDeclareOnline(companyInfoReqVo.getIsDeclareOnline());
            companyBase.setIsSafeProof(companyInfoReqVo.getIsSafeProof());
            companyBase.setIsStanCertificate(companyInfoReqVo.getIsStanCertificate());
            companyBase.setSafeProofArchiveNo(companyInfoReqVo.getSafeProofArchiveNo());
            companyBase.setWorkSystem(companyInfoReqVo.getWorkSystem());
            companyBase.setProductionDepartmentPeoples(companyInfoReqVo.getProductionDepartmentPeoples());
            companyBase.setOfficePeoples(companyInfoReqVo.getOfficePeoples());
            companyBase.setYearlyWorkDays(companyInfoReqVo.getWorkDaysYearly());
            companyBase.setIndustryType(companyInfoReqVo.getIndustryType());
            companyBase.setSafetyLevel(companyInfoReqVo.getSafetyLevel());
            companyBase.setCertificateStartTime(companyInfoReqVo.getCertificateStartTime());
            companyBase.setCertificateEndTime(companyInfoReqVo.getCertificateEndTime());
            companyBase.setMajorRiskSources(companyInfoReqVo.getMajorRiskSources());
            companyBase.setHigherRiskSources(companyInfoReqVo.getHigherRiskSources());
            companyBase.setGeneralRiskSources(companyInfoReqVo.getGeneralRiskSources());
            companyBase.setLowRiskSources(companyInfoReqVo.getLowRiskSources());

            int result = companyBaseMapper.insertCompanyBase(companyBase);
            if (result < 1) {
                throw BusinessException.SAVE_ERROR;
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);

        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delCompanyInfo(Long id) {

        CompanyStandard companyStandard = companyStandardMapper.getCompanyById(id);
        if (null == companyStandard) {
            throw BusinessException.COMPANY_NOT_EXISTS;
        }
        int delete = companyStandardMapper.deleteCompanyStandard(id);
        if (delete < 1) {
            throw BusinessException.DELETE_ERROR;
        }
        CompanyBase companyBase = companyBaseMapper.getCompanBaseById(id);
        if (null != companyBase) {
            int result = companyBaseMapper.deleteCompanyBase(id);
            if (result < 1) {
                throw BusinessException.DELETE_ERROR;
            }
        }
        return "{}";
    }

    /**
     * 组装服务内容
     *
     * @param serviceName
     * @param serviceDesc
     * @return
     */
    private ServiceInfo assembleServiceInfo(String serviceName, String serviceDesc) {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setId(snowflakeIdGenerator.generateId());
        serviceInfo.setServiceName(serviceName);
        serviceInfo.setServiceDesc(serviceDesc);
        serviceInfo.setServiceType(0);
        serviceInfo.setServiceCycle(0);
        return serviceInfo;
    }


    /**
     * 组装返回数据
     *
     * @param nextCursor
     * @param listVoList
     * @return
     */
    private PageBean<CompanyListVo> assemblePageBean(long nextCursor, List<CompanyStandard> listVoList) {
        PageBean<CompanyListVo> pageBean = new PageBean<>();
        pageBean.setNextCursor(nextCursor);
        //组装base数据
        List<CompanyBase> companyBases = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(listVoList)) {
            List<Long> companyIds = listVoList.stream().map(standard -> {
                return standard.getId();
            }).collect(Collectors.toList());
            companyBases = companyBaseMapper.getCompanyBases(companyIds);
        }
        Map<Long, CompanyBase> companyBaseMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(companyBases)) {
            companyBaseMap = companyBases.stream().collect(Collectors.toMap(CompanyBase::getId, temp -> temp, (e1, e2) -> e1));
        }

        Map<Long, CompanyBase> finalCompanyBaseMap = companyBaseMap;
        List<CompanyListVo> listVos = listVoList.stream().map(temp -> {
            CompanyListVo companyListVo = new CompanyListVo();
            companyListVo.setAreaId(temp.getAreaId());
            companyListVo.setBusinessCode(temp.getBusinessTypeCode());
            companyListVo.setBusinessLicenseId(temp.getBusinessLicenseId());
            companyListVo.setEmail(temp.getEmail());
            companyListVo.setMobile(temp.getLegalPersonMobile());
            companyListVo.setLegalPerson(temp.getLegalPerson());
            companyListVo.setId(temp.getId());
            companyListVo.setName(temp.getName());
            companyListVo.setRegistedCapital(new BigDecimal(temp.getRegistedCapital()).divide(new BigDecimal(100)).toPlainString());
            if (finalCompanyBaseMap.containsKey(temp.getId())) {
                CompanyBase companyBase = finalCompanyBaseMap.get(temp.getId());
                if (Objects.nonNull(companyBase)) {
                    companyListVo.setIndustryType(IndustryType.valueOf(companyBase.getIndustryType()) == null ? "" : IndustryType.valueOf(companyBase.getIndustryType()).desc());
                    companyListVo.setSafetyLevel(SafetyLevel.valueOf(companyBase.getSafetyLevel()) == null ? "" : SafetyLevel.valueOf(companyBase.getSafetyLevel()).desc());
                    companyListVo.setCertificateStartTime(companyBase.getCertificateStartTime());
                    companyListVo.setCertificateEndTime(companyBase.getCertificateEndTime());
                    companyListVo.setMajorRiskSources(companyBase.getMajorRiskSources());
                    companyListVo.setHigherRiskSources(companyBase.getHigherRiskSources());
                    companyListVo.setGeneralRiskSources(companyBase.getGeneralRiskSources());
                    companyListVo.setLowRiskSources(companyBase.getLowRiskSources());
                }
            }
            return companyListVo;
        }).collect(Collectors.toList());
        pageBean.setList(listVos);
        return pageBean;
    }


    /**
     * 组装计划分页数据
     *
     * @param nextCursor
     * @param listVoList
     * @return
     */
    private PageBean<PlanDetailVo> assemblePlanDetailPage(long nextCursor, List<PlanDetailVo> listVoList) {
        PageBean<PlanDetailVo> pageBean = new PageBean<>();
        pageBean.setNextCursor(nextCursor);
        pageBean.setList(listVoList);
        return pageBean;
    }
}

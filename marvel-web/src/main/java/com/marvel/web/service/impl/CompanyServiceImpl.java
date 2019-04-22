package com.marvel.web.service.impl;

import com.marvel.common.models.PageBean;
import com.marvel.common.utils.PaginationUtils;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.CompanyBaseMapper;
import com.marvel.web.mapper.CompanyStandardMapper;
import com.marvel.web.po.CompanyBase;
import com.marvel.web.po.CompanyStandard;
import com.marvel.web.service.CompanyService;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public PageBean<CompanyListVo> getCompanyList(Integer cursor, Integer count) {
        try {
            long total = companyStandardMapper.getCompanyCount();
            if (total == 0) {
                return assemblePageBean(-1, new ArrayList<>());
            }
            List<CompanyStandard> standardList = companyStandardMapper.getCompanyListPage(count * (cursor - 1), count);
            if (null == standardList || standardList.size() == 0){
                return assemblePageBean(-1, new ArrayList<>());
            }
            long nextCursor = PaginationUtils.nextCursor(cursor,count,total);
            return assemblePageBean(nextCursor,standardList);
        }catch (Exception e){
            LOGGER.error("CompanyService-->getCompanyList-->exception,cursor:{},count:{}",cursor,count,e);
            throw BusinessException.QUERY_DB_ERROR;
        }

    }

    @Override
    public CompanyDetailVo getCompanyInfo(Long id) {
        CompanyDetailVo companyDetailVo = new CompanyDetailVo();
        try {
            CompanyStandard companyStandard = companyStandardMapper.getCompanyById(id);
            if (null == companyStandard){
                throw BusinessException.COMPANY_NOT_EXISTS;
            }
            companyDetailVo.setId(id);
            companyDetailVo.setName(companyStandard.getName());
            companyDetailVo.setAreaId(companyStandard.getAreaId());
            companyDetailVo.setRegistedCapital(new BigDecimal(companyStandard.getRegistedCapital()).divide(new BigDecimal(100)).toPlainString());
            companyDetailVo.setLegalPerson(companyStandard.getLegalPerson());
            companyDetailVo.setMobile(companyStandard.getLegalPersonMobile());
            companyDetailVo.setBusinessCode(companyStandard.getBussinessTypeCode());
            companyDetailVo.setEmail(companyStandard.getEmail());
            companyDetailVo.setBusinessLicenseId(companyStandard.getBusinessLicenseId());
            CompanyBase companyBase = companyBaseMapper.getCompanBaseById(id);
            if (null == companyBase){
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

            return companyDetailVo;
        }catch (Exception e){
            LOGGER.error("CompanyService-->getCompanyInfo-->exception,id:{}",id,e);
            throw BusinessException.QUERY_DB_ERROR;
        }
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
        List<CompanyListVo> listVos = listVoList.stream().map(temp->{
            CompanyListVo companyListVo = new CompanyListVo();
            companyListVo.setAreaId(temp.getAreaId());
            companyListVo.setBusinessCode(temp.getBussinessTypeCode());
            companyListVo.setBusinessLicenseId(temp.getBusinessLicenseId());
            companyListVo.setEmail(temp.getEmail());
            companyListVo.setMobile(temp.getLegalPersonMobile());
            companyListVo.setLegalPerson(temp.getLegalPerson());
            companyListVo.setId(temp.getId());
            companyListVo.setName(temp.getName());
            companyListVo.setRegistedCapital(new BigDecimal(temp.getRegistedCapital()).divide(new BigDecimal(100)).toPlainString());
            return companyListVo;
        }).collect(Collectors.toList());
        pageBean.setList(listVos);
        return pageBean;
    }
}

package com.marvel.web.service.impl;

import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.BureauMapper;
import com.marvel.web.mapper.CompanyStandardMapper;
import com.marvel.web.po.Bureau;
import com.marvel.web.po.CompanyBase;
import com.marvel.web.po.CompanyStandard;
import com.marvel.web.service.BureauService;
import com.marvel.web.vo.BureauCompanyVo;
import com.marvel.web.vo.BureauInfoReqVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Classname BureauServiceImpl
 * @Description
 * @Author andy
 * @Date 2019/4/24 下午11:35
 * @Version 1.0
 */
@Service
public class BureauServiceImpl implements BureauService {


    @Resource
    private BureauMapper bureauMapper;

    @Resource
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Resource
    private CompanyStandardMapper companyStandardMapper;

    @Override
    public String updateBureauInfo(BureauInfoReqVo bureauInfoReqVo) {
        Bureau bureau = bureauMapper.getBureauById(bureauInfoReqVo.getId());
        if (null == bureau){
            throw BusinessException.BUREAU_NOT_EXISTS;
        }
        bureau.setAreaId(bureauInfoReqVo.getAreaId());
        bureau.setName(bureauInfoReqVo.getName());
        bureau.setMobile(bureauInfoReqVo.getMobile());
        bureau.setRemark(bureauInfoReqVo.getRemark());
        int update = bureauMapper.update(bureau);
        if (update < 1){
            throw BusinessException.UPDATE_ERROR;
        }
        return "{}";
    }

    @Override
    public String addBureauInfo(BureauInfoReqVo bureauInfoReqVo) {
        Long id = snowflakeIdGenerator.generateId();
        if (id == null){
            throw BusinessException.SAVE_ERROR;
        }
        Bureau bureau = new Bureau();
        bureau.setId(id);
        bureau.setAreaId(bureauInfoReqVo.getAreaId());
        bureau.setName(bureauInfoReqVo.getName());
        bureau.setMobile(bureauInfoReqVo.getMobile());
        bureau.setRemark(bureauInfoReqVo.getRemark());
        int insert = bureauMapper.insert(bureau);
        if (insert < 1){
            throw BusinessException.SAVE_ERROR;
        }
        return "{}";
    }

    @Override
    public String delBureauInfo(Long id) {
        Bureau bureau = bureauMapper.getBureauById(id);
        if (null == bureau){
            throw BusinessException.BUREAU_NOT_EXISTS;
        }
        int insert = bureauMapper.delete(id);
        if (insert < 1){
            throw BusinessException.DELETE_ERROR;
        }
        return "{}";
    }

    @Override
    public String saveBureauCompanyInfo(BureauCompanyVo bureauCompanyVo) {
        Long id = snowflakeIdGenerator.generateId();
        if (id == null){
            throw BusinessException.SAVE_ERROR;
        }
        CompanyStandard companyStandard = new CompanyStandard();
        companyStandard.setId(id);
        companyStandard.setAreaId(companyInfoReqVo.getAreaId() == null ? companyStandard.getAreaId() : companyInfoReqVo.getAreaId());
        companyStandard.setBusinessLicenseId(companyInfoReqVo.getBusinessCode());
        companyStandard.setBusinessLicenseId(companyInfoReqVo.getBusinessLicenseNo());
        companyStandard.setRegistedCapital(StringUtils.isBlank(companyInfoReqVo.getRegistedCapital()) ? null : new BigDecimal(companyInfoReqVo.getRegistedCapital()).multiply(new BigDecimal(100)).longValue());
        companyStandard.setLegalPerson(companyInfoReqVo.getLegalPreson());
        companyStandard.setLegalPersonMobile(companyInfoReqVo.getMobile());
        companyStandard.setEmail(companyInfoReqVo.getEmail());
        companyStandard.setType(3);
        companyStandard.setName(companyInfoReqVo.getName());
        companyStandard.setIndustryId(companyInfoReqVo.getIndustryId());

        int insert = companyStandardMapper.save(companyStandard);
        if (insert < 0){
            throw BusinessException.SAVE_ERROR;
        }
        return "{}";
    }

    @Override
    public String updateBureauCompanyInfo(BureauCompanyVo bureauCompanyVo) {
        CompanyStandard companyStandard = companyStandardMapper.getCompanyById(bureauCompanyVo.getId());
        if (null == companyStandard) {
            throw BusinessException.COMPANY_NOT_EXISTS;
        }
        companyStandard.setAreaId(bureauCompanyVo.getAreaId() == null ? bureauCompanyVo.getAreaId() : bureauCompanyVo.getAreaId());
        companyStandard.setBusinessLicenseId(bureauCompanyVo.getBusinessCode());
        companyStandard.setBusinessLicenseId(bureauCompanyVo.getBusinessLicenseNo());
        companyStandard.setRegistedCapital(StringUtils.isBlank(bureauCompanyVo.getRegistedCapital()) ? null : new BigDecimal(bureauCompanyVo.getRegistedCapital()).multiply(new BigDecimal(100)).longValue());
        companyStandard.setLegalPerson(bureauCompanyVo.getLegalPreson());
        companyStandard.setLegalPersonMobile(bureauCompanyVo.getMobile());
        companyStandard.setEmail(bureauCompanyVo.getEmail());
        companyStandard.setName(bureauCompanyVo.getName());
        companyStandard.setIndustryId(bureauCompanyVo.getIndustryId());
        int update = companyStandardMapper.updateCompanyStandard(companyStandard);
        if (update < 1) {
            throw BusinessException.UPDATE_ERROR;
        }
        return "{}";
    }

    @Override
    public String delBureauCompanyInfo(Long id) {
        CompanyStandard companyStandard = companyStandardMapper.getCompanyById(id);
        if (null == companyStandard){
            throw BusinessException.COMPANY_NOT_EXISTS;
        }
        int delete = companyStandardMapper.deleteCompanyStandard(id);
        if (delete < 1){
            throw BusinessException.DELETE_ERROR;
        }
        return "{}";
    }
}

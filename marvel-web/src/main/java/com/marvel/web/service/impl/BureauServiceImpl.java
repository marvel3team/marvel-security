package com.marvel.web.service.impl;

import com.google.common.collect.Lists;
import com.marvel.common.models.PageBean;
import com.marvel.common.uuid.SnowflakeIdGenerator;
import com.marvel.web.enums.IndustryType;
import com.marvel.web.enums.SafetyLevel;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.BureauMapper;
import com.marvel.web.mapper.CompanyStandardMapper;
import com.marvel.web.po.Bureau;
import com.marvel.web.po.CompanyBase;
import com.marvel.web.po.CompanyStandard;
import com.marvel.web.service.BureauService;
import com.marvel.web.vo.BureauCompanyVo;
import com.marvel.web.vo.BureauInfoReqVo;
import com.marvel.web.vo.CompanyListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        if (null == bureau) {
            throw BusinessException.BUREAU_NOT_EXISTS;
        }
        bureau.setAreaId(bureauInfoReqVo.getAreaId());
        bureau.setName(bureauInfoReqVo.getName());
        bureau.setMobile(bureauInfoReqVo.getMobile());
        bureau.setRemark(bureauInfoReqVo.getRemark());
        int update = bureauMapper.update(bureau);
        if (update < 1) {
            throw BusinessException.UPDATE_ERROR;
        }
        return "{}";
    }

    @Override
    public String addBureauInfo(BureauInfoReqVo bureauInfoReqVo) {
        Long id = snowflakeIdGenerator.generateId();
        if (id == null) {
            throw BusinessException.SAVE_ERROR;
        }
        Bureau bureau = new Bureau();
        bureau.setId(id);
        bureau.setAreaId(bureauInfoReqVo.getAreaId());
        bureau.setName(bureauInfoReqVo.getName());
        bureau.setCompanyId(bureauInfoReqVo.getCompanyId());
        bureau.setMobile(bureauInfoReqVo.getMobile());
        bureau.setRemark(bureauInfoReqVo.getRemark());
        int insert = bureauMapper.insert(bureau);
        if (insert < 1) {
            throw BusinessException.SAVE_ERROR;
        }
        return "{}";
    }

    @Override
    public String delBureauInfo(Long id) {
        Bureau bureau = bureauMapper.getBureauById(id);
        if (null == bureau) {
            throw BusinessException.BUREAU_NOT_EXISTS;
        }
        int insert = bureauMapper.delete(id);
        if (insert < 1) {
            throw BusinessException.DELETE_ERROR;
        }
        return "{}";
    }

    @Override
    public String saveBureauCompanyInfo(BureauCompanyVo bureauCompanyVo) {
        Long id = snowflakeIdGenerator.generateId();
        if (id == null) {
            throw BusinessException.SAVE_ERROR;
        }
        CompanyStandard companyStandard = new CompanyStandard();
        companyStandard.setId(id);
        companyStandard.setAreaId(bureauCompanyVo.getAreaId() == null ? companyStandard.getAreaId() : bureauCompanyVo.getAreaId());
        companyStandard.setBusinessLicenseId(bureauCompanyVo.getBusinessCode());
        companyStandard.setBusinessLicenseId(bureauCompanyVo.getBusinessLicenseNo());
        companyStandard.setRegistedCapital(StringUtils.isBlank(bureauCompanyVo.getRegistedCapital()) ? -1 : new BigDecimal(bureauCompanyVo.getRegistedCapital()).multiply(new BigDecimal(100)).longValue());
        companyStandard.setLegalPerson(bureauCompanyVo.getLegalPreson());
        companyStandard.setLegalPersonMobile(bureauCompanyVo.getMobile());
        companyStandard.setEmail(bureauCompanyVo.getEmail());
        companyStandard.setType(3);
        companyStandard.setName(bureauCompanyVo.getName());

        int insert = companyStandardMapper.save(companyStandard);
        if (insert < 0) {
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
        int update = companyStandardMapper.updateCompanyStandard(companyStandard);
        if (update < 1) {
            throw BusinessException.UPDATE_ERROR;
        }
        return "{}";
    }

    @Override
    public String delBureauCompanyInfo(Long id) {
        CompanyStandard companyStandard = companyStandardMapper.getCompanyById(id);
        if (null == companyStandard) {
            throw BusinessException.COMPANY_NOT_EXISTS;
        }
        int delete = companyStandardMapper.deleteCompanyStandard(id);
        if (delete < 1) {
            throw BusinessException.DELETE_ERROR;
        }
        return "{}";
    }

    @Override
    public PageBean<BureauInfoReqVo> getBureauUserInfoList(Long id, Long cursor, Integer count) {

        long total = bureauMapper.getBureauCountByCompanyId(id);
        if (total == 0) {
            return assemblePageBean(-1, new ArrayList<>());
        }
        List<Bureau> bureauList = bureauMapper.getBureauByCompanyId(id, cursor, count);
        if (CollectionUtils.isEmpty(bureauList)) {
            return assemblePageBean(-1, new ArrayList<>());
        }

        if (bureauList.size() < total) {
            return assemblePageBean(-1, bureauList);
        }
        return assemblePageBean(bureauList.get(bureauList.size() - 1).getId(), bureauList);

    }

    /**
     * 组装返回数据
     *
     * @param nextCursor
     * @param bureauList
     * @return
     */
    private PageBean<BureauInfoReqVo> assemblePageBean(long nextCursor, List<Bureau> bureauList) {
        PageBean<BureauInfoReqVo> pageBean = new PageBean<>();
        pageBean.setNextCursor(nextCursor);

        //组装base数据
        List<BureauInfoReqVo> resultList = bureauList.stream().map(temp -> {
            BureauInfoReqVo reqVo = new BureauInfoReqVo();
            reqVo.setId(temp.getId());
            reqVo.setAreaId(temp.getAreaId() == -1 ? null : temp.getAreaId());
            reqVo.setMobile(temp.getMobile());
            reqVo.setName(temp.getName());
            reqVo.setCompanyId(temp.getCompanyId() == -1 ? null : temp.getCompanyId());
            reqVo.setRemark(temp.getRemark());
            return reqVo;
        }).collect(Collectors.toList());
        pageBean.setList(resultList);
        return pageBean;
    }
}

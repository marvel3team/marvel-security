package com.marvel.web.service.impl;

import com.marvel.common.models.PageBean;
import com.marvel.common.utils.PaginationUtils;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.CompanyIndustryMapper;
import com.marvel.web.mapper.CompanyStandardMapper;
import com.marvel.web.mapper.ExpertInfoMapper;
import com.marvel.web.po.CompanyIndustry;
import com.marvel.web.po.CompanyStandard;
import com.marvel.web.po.ExpertInfo;
import com.marvel.web.service.ExpertService;
import com.marvel.web.vo.ExpertInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.MapUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Override
    public PageBean<ExpertInfoVo> getExpertList(Integer cursor, Integer count) {
        try {
            long total = expertInfoMapper.getExpertCount();
            if (total == 0) {
                return assemblePageBean(-1, new ArrayList<>());
            }
            List<ExpertInfo> expertInfos = expertInfoMapper.getExpertListByPage(count * (cursor - 1), count);
            if (null == expertInfos || expertInfos.size() ==0 ){
                return assemblePageBean(-1, new ArrayList<>());
            }
            //查询公司id
            List<Long> companyIds = expertInfos.stream().map(temp->{return temp.getCompanyId();}).collect(Collectors.toList());
            //根据公司id查询公司的行业
            List<CompanyStandard> companyStandards = companyStandardMapper.getCompanyByIds(companyIds);

            Map<Long,CompanyIndustry> industryMap = new HashMap<>();

            if (null != companyStandards && companyStandards.size()>0){
                //根据行业id，查询行业信息
                List<Long> industryIds = companyStandards.stream().map(temp->{return temp.getIndustryId();}).collect(Collectors.toList());
                List<CompanyIndustry> industryList = companyIndustryMapper.getIndustryByIds(industryIds);
                Map<Long,CompanyIndustry> industryIdMap = new HashMap<>();
                if (null != industryList && industryList.size()>0){
                    industryIdMap = industryList.stream().collect(Collectors.toMap(CompanyIndustry::getId,temp->temp,(e1,e2)->e1));
                }
                for (CompanyStandard companyStandard : companyStandards){
                    CompanyIndustry companyIndustry = industryIdMap.getOrDefault(companyStandard.getIndustryId(),null);
                    if (null != companyIndustry){
                        industryMap.put(companyStandard.getId(),companyIndustry);
                    }
                }
            }
            List<ExpertInfoVo> resultList = new ArrayList<>();
            for (ExpertInfo expertInfo : expertInfos){

                CompanyIndustry companyIndustry = industryMap.getOrDefault(expertInfo.getCompanyId(),null);
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
                expertInfoVo.setIndustry(null == companyIndustry ?"":companyIndustry.getContent());
                //TODO
                expertInfoVo.setArea("");
                resultList.add(expertInfoVo);
            }
            long nextCursor = PaginationUtils.nextCursor(cursor,count,total);
            return assemblePageBean(nextCursor,resultList);
        }catch (Exception e){
            LOGGER.error("ExpertService-->getExpertList-->exception,cursor:{},count:{}",cursor,count,e);
        }
        return assemblePageBean(-1,new ArrayList<>());
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
}

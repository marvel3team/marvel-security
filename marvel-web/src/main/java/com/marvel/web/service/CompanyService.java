package com.marvel.web.service;


import com.marvel.common.models.PageBean;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.po.ServiceInfo;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyInfoReqVo;
import com.marvel.web.vo.CompanyListVo;
import com.marvel.web.vo.PlanDetailVo;

/**
 * @Classname CompanyService
 * @Description
 * @Author andy
 * @Date 2019/4/22 下午10:01
 */
public interface CompanyService {


    /**
     * @param cursor
     * @param count
     * @Title: getCompanyList
     * @Description: 分页查询公司信息
     * @return:
     * @throws:
     * @author: andy
     * @date: 2019/4/22 下午10:42
     */
    PageBean<CompanyListVo> getCompanyList(Integer cursor, Integer count);


    /**
     * @param id
     * @return
     * @throws
     * @Title
     * @Description 查询企业详情
     * @author andy
     * @date 2019/4/22 下午11:20
     */
    CompanyDetailVo getCompanyInfo(Long id);

    /**
     * @param cursor
     * @param count
     * @param id
     * @return
     * @throws
     * @Title
     * @Description 分页查询公司下的所有计划信息
     * @author andy
     * @date 2019/4/23 下午11:13
     */
    PageBean<PlanDetailVo> getPlanList(Integer cursor, Integer count, Long id);

    /**
     * Description: 服务列表
     *
     * @Param
     * @param requestContext
     * @param cursor
     * @param count
     * @return
     * @Date 下午10:30 2019/4/24
     * @Author zhongjie
     **/
    PageBean<ServiceInfo> getServiceList(RequestContext requestContext, Long cursor, Integer count);

    /**
     * 更新企业信息
     * @param companyInfoReqVo
     * @return
     */
    String updateCompanyInfo(CompanyInfoReqVo companyInfoReqVo);
}

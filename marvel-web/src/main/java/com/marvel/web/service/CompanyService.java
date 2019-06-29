package com.marvel.web.service;


import com.marvel.common.models.PageBean;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.po.ServiceInfo;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyInfoReqVo;
import com.marvel.web.vo.CompanyListVo;
import com.marvel.web.vo.PlanDetailVo;

import java.util.Map;

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
    PageBean<CompanyListVo> getCompanyList(Long cursor, Integer count);


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
    PageBean<PlanDetailVo> getPlanList(Long cursor, Integer count, Long id);

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
    PageBean<ServiceInfo> getServiceList(RequestContext requestContext, String serviceName, Long cursor, Integer count);

    /**
     * 更新企业信息
     * @param companyInfoReqVo
     * @return
     */
    String updateCompanyInfo(CompanyInfoReqVo companyInfoReqVo);

    /**
     * 服务内容新增
     * @param serviceName
     * @param serviceDesc
     */
    void addService(String serviceName, String serviceDesc);

    /**
     * 删除服务内容
     * @param serviceId
     * @return
     */
    boolean deleteService(Long serviceId);

    /**
     * 新增服务内容
     * @param serviceId
     * @param serviceName
     * @param serviceDesc
     * @return
     */
    boolean addService(Long serviceId, String serviceName, String serviceDesc);

    /**
     * 获取服务
     * @param serviceId
     * @return
     */
    ServiceInfo getService(Long serviceId);

    /**
     * 新增企业信息
     * @param companyInfoReqVo
     * @return
     */
    Map<String,Object> saveCompanyInfo(CompanyInfoReqVo companyInfoReqVo);

    /**
     * 删除企业信息
     * @param id
     * @return
     */
    String delCompanyInfo(Long id);
}

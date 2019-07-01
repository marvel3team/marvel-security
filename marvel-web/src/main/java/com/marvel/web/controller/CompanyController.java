package com.marvel.web.controller;


import com.alibaba.fastjson.JSON;
import com.marvel.common.models.PageBean;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.constants.Constants;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.po.ServiceInfo;
import com.marvel.web.service.CompanyService;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyInfoReqVo;
import com.marvel.web.vo.CompanyListVo;
import com.marvel.web.vo.PlanDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
* @Classname CompanyController
* @Description 企业信息相关接口 
* @Author lizhui
* @Date 2019/4/22 下午10:01
*/
@RestController
@RequestMapping("/v1/company")
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);


    @Autowired
    private CompanyService companyService;



    /**
     * @Title getCompanyList
     * @Description 分页查询企业信息
     * @param cursor
     * @param count
     * @return com.marvel.common.models.PageBean<com.marvel.web.vo.CompanyListVo>
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:15
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_company_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<CompanyListVo> getCompanyList(@RequestParam(name = "cursor",required = false,defaultValue = "-1") Long cursor,
                                                  @RequestParam(name = "count",required = false,defaultValue = "10")Integer count){
        PageBean<CompanyListVo> pageBean = companyService.getCompanyList(cursor,count);
        return pageBean;
    }


    /**
     * @Title getCompanyInfo
     * @Description 根据企业id查询企业信息 
     * @param id
     * @return com.marvel.web.vo.CompanyDetailVo
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_company_info.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public CompanyDetailVo getCompanyInfo(@RequestParam(name = "id",required = false,defaultValue = "-1") Long id){
        if (null == id || id < 0){
            LOGGER.error("CompanyController-->getCompanyInfo-->parameter invalid,id:{}",id);
            throw BusinessException.INVALID_PARAMS;
        }
        return companyService.getCompanyInfo(id);
    }


    /**
     * @Title getPlanList
     * @Description 根据企业id查询公司下所有计划
     * @param id
     * @param name
     * @param status 是否完成 1完成 2未完成
     * @return com.marvel.web.vo.CompanyDetailVo
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_plan_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<PlanDetailVo> getPlanList(@RequestParam(name = "cursor",required = false,defaultValue = "-1") Long cursor,
                                              @RequestParam(name = "count",required = false,defaultValue = "20")Integer count,
                                              @RequestParam(name = "id",required = false,defaultValue = "-1") Long id,
                                              @RequestParam(name = "name",required = false, defaultValue = "") String name,
                                              @RequestParam(name = "status", required = false, defaultValue = "1") Integer status){
        if ((null == id || id < 0 ) && StringUtils.isBlank(name)){
            LOGGER.error("CompanyController-->getPlanList-->parameter invalid,id:{}, name:{}",id,name);
            throw BusinessException.INVALID_PARAMS;
        }
        return companyService.getPlanList(cursor,count,id,name,status);
    }

    /***
     * Description: //新增服务内容
     *
     * @param serviceName
     * @param serviceDesc
     * @return
     * @Date 下午9:51 2019/6/11
     * @Author zhongjie
     **/
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/add_service_content.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addService(@RequestParam(name = "serviceName", required = false, defaultValue = "") String serviceName,
                                                @RequestParam(name = "serviceDesc", required = false, defaultValue = "") String serviceDesc){
        if (StringUtils.isBlank(serviceName) || StringUtils.isBlank(serviceDesc)) {
            throw BusinessException.INVALID_PARAMS;
        }
        companyService.addService(serviceName, serviceDesc);
        return StringUtils.EMPTY;
    }

    /***
     * Description: //删除服务
     *
     * @param serviceId 服务ID
     * @return
     * @Date 下午10:14 2019/6/11
     * @Author zhongjie
     **/
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/delete_service_content.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String deleteService(@RequestParam(name = "serviceId", required = false, defaultValue = "") Long serviceId){
        if (serviceId == null || serviceId <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = companyService.deleteService(serviceId);
        if (!result) {
            throw BusinessException.DELETE_ERROR;
        }
        return StringUtils.EMPTY;
    }

    /***
     * Description: //编辑服务内容
     *
     * @param serviceId
     * @param serviceName
     * @param serviceDesc
     * @return
     * @Date 下午10:20 2019/6/11
     * @Author zhongjie
     **/
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_service_content.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String editService(@RequestParam(name = "serviceId", required = false, defaultValue = "") Long serviceId,
                              @RequestParam(name = "serviceName", required = false, defaultValue = "") String serviceName,
                              @RequestParam(name = "serviceDesc", required = false, defaultValue = "") String serviceDesc){
        if (serviceId == null || serviceId <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = companyService.addService(serviceId, serviceName, serviceDesc);
        if (!result) {
            throw BusinessException.SAVE_ERROR;
        }
        return StringUtils.EMPTY;
    }

    /***
     * Description: //查询单条服务内容
     *
     * @Param
     * @param serviceId
     * @return
     * @Date 下午10:24 2019/6/11
     * @Author zhongjie
     **/
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/query_service_content.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ServiceInfo getService(@RequestParam(name = "serviceId", required = false, defaultValue = "") Long serviceId){
        if (serviceId == null || serviceId <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }
        ServiceInfo serviceInfo = companyService.getService(serviceId);
        return serviceInfo;
    }

    /**
     * Description: 服务内容列表
     *
     * @param cursor
     * @param count
     * @return
     * @Date 下午10:27 2019/4/24
     * @Author zhongjie
     **/
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_service_content_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<ServiceInfo> getServiceList(@RequestParam(name = "serviceName", required = false, defaultValue = "") String serviceName,
                                                @RequestParam(name = "cursor", required = false, defaultValue = "-1") Long cursor,
                                                @RequestParam(name = "count", required = false, defaultValue = "10") Integer count){
        if (count == null) {
            count = Constants.DEFAULT_COUNT;
        }
        PageBean<ServiceInfo> result = companyService.getServiceList(RequestContext.getRequestContext(), serviceName ,cursor, count);
        return result;
    }

    /**
     * @Title updateCompanyInfo
     * @Description 修改公司信息
     * @param companyInfoReqVo
     * @return java.lang.String
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_company_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateCompanyInfo(@RequestBody CompanyInfoReqVo companyInfoReqVo){
        if (!checkParameter(companyInfoReqVo) || companyInfoReqVo.getId() == null){
            LOGGER.error("CompanyController-->updateCompanyInfo-->parameter invalid parameter,reqBody:{}", JSON.toJSON(companyInfoReqVo));
            throw BusinessException.INVALID_PARAMS;
        }
        return companyService.updateCompanyInfo(companyInfoReqVo);
    }


    /**
     * @Title updateCompanyInfo
     * @Description 新增公司信息
     * @param companyInfoReqVo
     * @return java.lang.String
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/save_company_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String,Object> saveCompanyInfo(@RequestBody CompanyInfoReqVo companyInfoReqVo){
        if (!checkParameter(companyInfoReqVo)){
            LOGGER.error("CompanyController-->saveCompanyInfo-->parameter invalid parameter,reqBody:{}", JSON.toJSON(companyInfoReqVo));
            throw BusinessException.INVALID_PARAMS;
        }
        return companyService.saveCompanyInfo(companyInfoReqVo);
    }


    /**
     * @Title updateCompanyInfo
     * @Description 删除公司信息
     * @param id
     * @return java.lang.String
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/del_company_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String delCompanyInfo(@RequestParam(name = "id")Long id){
        if (null == id){
            LOGGER.error("CompanyController-->delCompanyInfo-->parameter invalid parameter,reqBody:{}", id);
            throw BusinessException.INVALID_PARAMS;
        }
        return companyService.delCompanyInfo(id);
    }


    /**
     * 参数校验
     * @param companyInfoReqVo
     * @return
     */
    private boolean checkParameter(CompanyInfoReqVo companyInfoReqVo) {
        if (companyInfoReqVo.getId() == null){
            return false;
        }
        if (companyInfoReqVo.getAreaId() == null){
            return false;
        }
        if (StringUtils.isBlank(companyInfoReqVo.getRegistedCapital()) || StringUtils.isBlank(companyInfoReqVo.getLegalPreson())){
            return false;
        }
        if (StringUtils.isBlank(companyInfoReqVo.getMobile()) || StringUtils.isBlank(companyInfoReqVo.getBusinessCode()) || StringUtils.isBlank(companyInfoReqVo.getEmail())){
            return false;
        }

        return true;
    }


}

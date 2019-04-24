package com.marvel.web.controller;


import com.marvel.common.models.PageBean;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.constants.Constants;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.po.ServiceInfo;
import com.marvel.web.service.CompanyService;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyListVo;
import com.marvel.web.vo.PlanDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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
    @MarvelCheck
    @RequestMapping(value = "/get_company_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<CompanyListVo> getCompanyList(@RequestParam(name = "cursor",required = false,defaultValue = "1") Integer cursor,
                                                  @RequestParam(name = "count",required = false,defaultValue = "20")Integer count){
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
    @MarvelCheck
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
     * @return com.marvel.web.vo.CompanyDetailVo
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck
    @RequestMapping(value = "/get_plan_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<PlanDetailVo> getPlanList(@RequestParam(name = "cursor",required = false,defaultValue = "1") Integer cursor,
                                              @RequestParam(name = "count",required = false,defaultValue = "20")Integer count,
                                              @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){
        if (null == id || id < 0){
            LOGGER.error("CompanyController-->getPlanList-->parameter invalid,id:{}",id);
            throw BusinessException.INVALID_PARAMS;
        }
        return companyService.getPlanList(cursor,count,id);
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
    public PageBean<ServiceInfo> getServiceList(@RequestParam(name = "cursor", required = false, defaultValue = "-1") Long cursor,
                                                @RequestParam(name = "count", required = false, defaultValue = "10") Integer count){
        if (count == null) {
            count = Constants.DEFAULT_COUNT;
        }
        PageBean<ServiceInfo> result = companyService.getServiceList(RequestContext.getRequestContext(), cursor, count);
        return result;
    }

}

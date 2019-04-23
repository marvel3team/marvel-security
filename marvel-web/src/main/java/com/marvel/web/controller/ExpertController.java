package com.marvel.web.controller;

import com.marvel.common.models.PageBean;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.service.ExpertService;
import com.marvel.web.vo.ExpertInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname ExpertController
 * @Description
 * @Author andy
 * @Date 2019/4/22 下午11:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1/expert")
public class ExpertController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpertController.class);


    @Autowired
    private ExpertService expertService;


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
    @RequestMapping(value = "/get_expert_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<ExpertInfoVo> getExpertList(@RequestParam(name = "cursor",required = false,defaultValue = "1") Integer cursor,
                                                @RequestParam(name = "count",required = false,defaultValue = "20")Integer count){
        PageBean<ExpertInfoVo> pageBean = expertService.getExpertList(cursor,count);
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
    @RequestMapping(value = "/get_expert_info.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ExpertInfoVo getExpertInfo(@RequestParam(name = "id",required = false,defaultValue = "-1") Long id){
        if (null == id || id < 0){
            LOGGER.error("ExpertController-->getExpertInfo-->parameter invalid,id:{}",id);
            throw BusinessException.INVALID_PARAMS;
        }
        return expertService.getExpertInfo(id);
    }

}

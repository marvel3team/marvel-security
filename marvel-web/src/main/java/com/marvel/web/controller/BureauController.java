package com.marvel.web.controller;

import com.alibaba.fastjson.JSON;
import com.marvel.common.models.PageBean;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.service.BureauService;
import com.marvel.web.vo.BureauCompanyVo;
import com.marvel.web.vo.BureauInfoReqVo;
import com.marvel.web.vo.CompanyListVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname BureauController
 * @Description
 * @Author andy
 * @Date 2019/4/24 下午11:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1/bureau")
public class BureauController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BureauController.class);

    @Autowired
    private BureauService bureauService;

    /**
     * 修改科局人员信息
     *
     * @param bureauInfoReqVo
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_bureau_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateBureauInfo(@RequestBody BureauInfoReqVo bureauInfoReqVo) {
        if (!checkParameter(bureauInfoReqVo)) {
            LOGGER.error("BureauController-->updateBureauInfo-->parameter invalid parameter,reqBody:{}", JSON.toJSON(bureauInfoReqVo));
            throw BusinessException.INVALID_PARAMS;
        }
        return bureauService.updateBureauInfo(bureauInfoReqVo);
    }

    /**
     * 新增科局人员信息
     *
     * @param bureauInfoReqVo
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/add_bureau_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addBureauInfo(@RequestBody BureauInfoReqVo bureauInfoReqVo) {
        if (!checkParameter(bureauInfoReqVo)) {
            LOGGER.error("BureauController-->addBureauInfo-->parameter invalid parameter,reqBody:{}", JSON.toJSON(bureauInfoReqVo));
            throw BusinessException.INVALID_PARAMS;
        }
        return bureauService.addBureauInfo(bureauInfoReqVo);
    }

    /**
     * 删除科局人员信息
     *
     * @param id
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/del_bureau_info.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String delBureauInfo(@RequestParam(name = "id") Long id) {
        if (null == id) {
            LOGGER.error("BureauController-->delBureauInfo-->parameter invalid parameter,reqBody:{}", id);
            throw BusinessException.INVALID_PARAMS;
        }
        return bureauService.delBureauInfo(id);
    }

    /**
     * 查询科局所有的人员
     *
     * @param id
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_bureau_user_info.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<BureauInfoReqVo> getBureauUserInfoList(@RequestParam(name = "cursor",required = false,defaultValue = "-1") Long cursor,
                                                           @RequestParam(name = "count",required = false,defaultValue = "10")Integer count,
                                                           @RequestParam(name = "id") Long id) {
        if (null == id) {
            LOGGER.error("BureauController-->getBureauUserInfoList-->parameter invalid parameter,reqBody:{}", id);
            throw BusinessException.INVALID_PARAMS;
        }
        return bureauService.getBureauUserInfoList(id,cursor,count);
    }


    /**
     * @param bureauCompanyVo
     * @return java.lang.String
     * @throws
     * @Title saveBureauCompanyInfo
     * @Description 新增科局公司信息
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/save_bureau_company_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String saveBureauCompanyInfo(@RequestBody BureauCompanyVo bureauCompanyVo) {
        if (!checkBureauParameter(bureauCompanyVo)) {
            LOGGER.error("BureauController-->saveBureauCompanyInfo-->parameter invalid parameter,reqBody:{}", JSON.toJSON(bureauCompanyVo));
            throw BusinessException.INVALID_PARAMS;
        }
        return bureauService.saveBureauCompanyInfo(bureauCompanyVo);
    }

    /**
     * @param bureauCompanyVo
     * @return java.lang.String
     * @throws
     * @Title updateBureauCompanyInfo
     * @Description修改科局公司信息
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_bureau_company_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateBureauCompanyInfo(@@RequestParam BureauCompanyVo bureauCompanyVo) {
        if (!checkBureauParameter(bureauCompanyVo)) {
            LOGGER.error("BureauController-->updateBureauCompanyInfo-->parameter invalid parameter,reqBody:{}", JSON.toJSON(bureauCompanyVo));
            throw BusinessException.INVALID_PARAMS;
        }
        return bureauService.updateBureauCompanyInfo(bureauCompanyVo);
    }

    /**
     * @param id
     * @return java.lang.String
     * @throws
     * @Title delBureauCompanyInfo
     * @Description删除科局公司信息
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/del_bureau_company_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String delBureauCompanyInfo(@RequestParam(name = "id") Long id) {
        if (null == id) {
            LOGGER.error("BureauController-->delBureauCompanyInfo-->parameter invalid parameter,reqBody:{}", id);
            throw BusinessException.INVALID_PARAMS;
        }
        return bureauService.delBureauCompanyInfo(id);
    }

    private boolean checkBureauParameter(BureauCompanyVo bureauCompanyVo) {
        if (StringUtils.isBlank(bureauCompanyVo.getName())) {
            return false;
        }
        return true;
    }

    /**
     * 校验参数
     *
     * @param bureauInfoReqVo
     * @return
     */
    private boolean checkParameter(BureauInfoReqVo bureauInfoReqVo) {
        if (bureauInfoReqVo.getAreaId() == null || StringUtils.isBlank(bureauInfoReqVo.getMobile()) || StringUtils.isBlank(bureauInfoReqVo.getName())) {
            return false;
        }
        return true;
    }
}

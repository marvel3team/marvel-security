package com.marvel.web.controller;

import com.alibaba.fastjson.JSON;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.service.BureauService;
import com.marvel.web.vo.BureauInfoReqVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @MarvelCheck
    @RequestMapping(value = "/update_bureau_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateBureauInfo(@RequestBody BureauInfoReqVo bureauInfoReqVo){
        if (!checkParameter(bureauInfoReqVo)){
            LOGGER.error("BureauController-->updateBureauInfo-->parameter invalid parameter,reqBody:{}", JSON.toJSON(bureauInfoReqVo));
            throw BusinessException.INVALID_PARAMS;
        }
        return bureauService.updateBureauInfo(bureauInfoReqVo);
    }


    /**
     * 校验参数
     * @param bureauInfoReqVo
     * @return
     */
    private boolean checkParameter(BureauInfoReqVo bureauInfoReqVo) {
        if (bureauInfoReqVo.getId() == null || bureauInfoReqVo.getId() < 0){
            return false;
        }
        if (bureauInfoReqVo.getAreaId() == null || StringUtils.isBlank(bureauInfoReqVo.getMobile()) || StringUtils.isBlank(bureauInfoReqVo.getName())){
            return false;
        }
        return true;
    }
}

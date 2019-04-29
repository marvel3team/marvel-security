package com.marvel.web.controller;

import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.service.PlanService;
import com.marvel.web.vo.RemoteInfoReqVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Classname PlanController
 * @Description
 * @Author andy
 * @Date 2019/4/23 下午9:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/v1/plan")
public class PlanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;


    /**
     * @Title createRemoteInfo
     * @Description 新建远程业务信息
     * @param remoteInfo
     * @return com.marvel.common.models.PageBean<com.marvel.web.vo.CompanyListVo>
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:15
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/create_remote_info.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String createRemoteInfo(@RequestBody RemoteInfoReqVo remoteInfo){

        if (checkParameter(remoteInfo)){
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = planService.createRemoteInfo(remoteInfo);
        if (!result){
            throw BusinessException.CREATE_PLAN_ERROR;
        }
        return "{}";
    }


    /**
     * @Title updateRemoteInfo
     * @Description 更新远程计划
     * @param remoteInfo
     * @return java.lang.String
     * @throws
     * @author andy
     * @date 2019/4/23 下午10:54
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_remote_info.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateRemoteInfo(@RequestBody RemoteInfoReqVo remoteInfo){

        if (checkParameter(remoteInfo)){
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = planService.updateRemoteInfo(remoteInfo);
        if (!result){
            throw BusinessException.CREATE_PLAN_ERROR;
        }
        return "{}";
    }


    /**
     * 验证参数
     * @param remoteInfo
     * @return
     */
    private boolean checkParameter(RemoteInfoReqVo remoteInfo) {
        boolean result = Optional.ofNullable(remoteInfo).filter(temp->temp.getCompanyId() == null || temp.getCompanyId()<0 ).filter(temp->temp.getPlanTime() == null)
                .filter(temp->temp.getTimeSlat()==null).filter(temp->temp.getSuperversionLevel() == null)
                .filter(temp-> StringUtils.isBlank(temp.getPlanSubject())).isPresent();

        return result;
    }

}

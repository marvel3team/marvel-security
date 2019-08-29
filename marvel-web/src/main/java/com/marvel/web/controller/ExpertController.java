package com.marvel.web.controller;

import com.google.common.collect.Lists;
import com.marvel.common.models.PageBean;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.controller.req.ExpertInfoReq;
import com.marvel.web.controller.req.WorkTimeReq;
import com.marvel.web.converts.ExpertConvert;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.po.ExpertTime;
import com.marvel.web.service.ExpertService;
import com.marvel.web.vo.ExpertInfoVo;
import com.marvel.web.vo.ExpertTimeVo;
import com.marvel.web.vo.PlanDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * @Description 分页查询专家信息
     * @param cursor
     * @param count
     * @return com.marvel.common.models.PageBean<com.marvel.web.vo.CompanyListVo>
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:15
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_expert_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<ExpertInfoVo> getExpertList(@RequestParam(name = "cursor",required = false,defaultValue = "-1") Long cursor,
                                                @RequestParam(name = "count",required = false,defaultValue = "10")Integer count){
        PageBean<ExpertInfoVo> pageBean = expertService.getExpertList(cursor,count);
        return pageBean;
    }


    /**
     * @Title getCompanyInfo
     * @Description 根据专家id查询专家信息 
     * @param id
     * @return com.marvel.web.vo.CompanyDetailVo
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_expert_info.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ExpertInfoVo getExpertInfo(@RequestParam(name = "id",required = false,defaultValue = "-1") Long id){
        if (null == id || id < 0){
            LOGGER.error("ExpertController-->getExpertInfo-->parameter invalid,id:{}",id);
            throw BusinessException.INVALID_PARAMS;
        }
        return expertService.getExpertInfo(id);
    }


    /**
     * @Title getExpertPlanList
     * @Description 根据专家id查询专家下所有的计划信息 
     * @param id
     * @return com.marvel.web.vo.CompanyDetailVo
     * @throws
     * @author andy
     * @date 2019/4/22 下午11:49
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_expert_plan_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<PlanDetailVo> getExpertPlanList(@RequestParam(name = "cursor",required = false,defaultValue = "-1") Long cursor,
                                                    @RequestParam(name = "count",required = false,defaultValue = "20")Integer count,
                                                    @RequestParam(name = "id",required = false,defaultValue = "-1") Long id){
        if (null == id || id < 0){
            LOGGER.error("ExpertController-->getExpertPlanList-->parameter invalid,id:{}",id);
            throw BusinessException.INVALID_PARAMS;
        }
        return expertService.getExpertPlanList(id,cursor,count);
    }

    /**
     * 修改专家信息
     * @param expertInfoReq
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_expert_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateExpertInfo(@RequestBody ExpertInfoReq expertInfoReq){
        if (Objects.isNull(expertInfoReq) || expertInfoReq.getId() == null) {
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = expertService.update(RequestContext.getRequestContext(), ExpertConvert.convert(expertInfoReq));
        if (!result) {
            throw BusinessException.UPDATE_ERROR;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 新增专家信息
     * @param expertInfoReq
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/save_expert_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String saveExpertInfo(@RequestBody ExpertInfoReq expertInfoReq){
        if (Objects.isNull(expertInfoReq)) {
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = expertService.save(RequestContext.getRequestContext(), ExpertConvert.convert(expertInfoReq));
        if (!result) {
            throw BusinessException.SAVE_ERROR;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 删除专家信息
     * @param id
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/del_expert_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String delExpertInfo(@RequestParam(name = "id") Long id){
        if (id == null) {
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = expertService.delExpertInfo(RequestContext.getRequestContext(), id);
        if (!result) {
            throw BusinessException.DELETE_ERROR;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 设置专家时间
     * @param id 专家ID
     * @param workTimes 时间列表
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/create_expert_time.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String createExpertTimes(@RequestParam(name = "id") Long id, @RequestParam(name = "workTime") List<WorkTimeReq> workTimes){
        checkParams(id, workTimes);
        boolean result = expertService.createExpertTime(RequestContext.getRequestContext(), id, workTimes);
        if (!result) {
            throw BusinessException.SAVE_ERROR;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取专家时间
     * @param id 专家ID
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_expert_time_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<ExpertTimeVo> getExpertTimes(@RequestParam(name = "id") Long id){
        List<ExpertTime> list = expertService.getExpertTimes(RequestContext.getRequestContext(), id);
        List<ExpertTimeVo> vos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(expertTime -> {
                ExpertTimeVo vo = new ExpertTimeVo();
                vo.setStartTime(expertTime.getStartTime());
                vo.setEndTime(expertTime.getEndTime());
                vos.add(vo);
            });
        }
        return new PageBean<>(vos);
    }

    /**
     * 修改专家时间
     * @param id 专家ID
     * @param workTimes
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_expert_time.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateExpertTime(@RequestParam(name = "id") Long id, @RequestParam(name = "workTime") List<WorkTimeReq> workTimes){
        checkParams(id, workTimes);
        boolean result = expertService.updateExpertTime(RequestContext.getRequestContext(), id, workTimes);
        if (!result) {
            throw BusinessException.UPDATE_ERROR;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 参数检查
     * @param id
     * @param workTimes
     */
    private void checkParams(Long id, List<WorkTimeReq> workTimes) {
        if (Objects.isNull(id) || id <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (CollectionUtils.isEmpty(workTimes)) {
            throw BusinessException.INVALID_PARAMS;
        }
        workTimes.stream().forEach(workTimeReq -> {
            if (workTimeReq.getStartTime() == null || workTimeReq.getEndTime() == null) {
                throw BusinessException.INVALID_PARAMS;
            }
            if (workTimeReq.getStartTime().compareTo(workTimeReq.getEndTime()) == 1) {
                throw BusinessException.INVALID_PARAMS;
            }
        });
    }

}

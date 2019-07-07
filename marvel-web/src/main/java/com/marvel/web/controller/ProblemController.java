package com.marvel.web.controller;

import com.marvel.common.models.PageBean;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.constants.Constants;
import com.marvel.web.controller.req.RectifyProblemReq;
import com.marvel.web.converts.ProblemConvert;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.po.Problem;
import com.marvel.web.service.ProblemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Classname ProblemController
 * @Description 安全问题入口类
 * @Date 2019/4/23 下午9:32
 * @Author zhongjie
 */
@RestController
@RequestMapping("/v1/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @MarvelCheck(auth = true)
    @RequestMapping(value = "/add_rectify_problem.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addRectifyProblem(RectifyProblemReq rectifyProblemReq){
        checkParams(rectifyProblemReq);
        problemService.save(RequestContext.getRequestContext(), ProblemConvert.convert(rectifyProblemReq));
        return StringUtils.EMPTY;
    }

    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_rectify_problem.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateRectifyProblem(RectifyProblemReq rectifyProblemReq){
        if (Objects.isNull(rectifyProblemReq) || rectifyProblemReq.getProblemId() == null) {
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = problemService.update(RequestContext.getRequestContext(), ProblemConvert.convert(rectifyProblemReq));
        if (!result) {
            throw BusinessException.UPDATE_ERROR;
        }
        return StringUtils.EMPTY;
    }

    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_problem_list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageBean<Problem> getRectifyProblemList(@RequestParam(name = "cursor", required = false, defaultValue = "-1") Long cursor,
                                          @RequestParam(name = "count", required = false, defaultValue = "10") Integer count,
                                          @RequestParam(name = "status") Integer status){
        if (count == null) {
            count = Constants.DEFAULT_COUNT;
        }
        PageBean<Problem> result = problemService.getByPage(RequestContext.getRequestContext(), status, cursor, count);
        return result;
    }


    /**
     * 参数校验
     * @param rectifyProblemReq
     */
    private void checkParams(RectifyProblemReq rectifyProblemReq) {
        if (Objects.isNull(rectifyProblemReq)) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (rectifyProblemReq.getPlanId() == null || rectifyProblemReq.getPlanId() <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (rectifyProblemReq.getExpertId() == null || rectifyProblemReq.getExpertId() <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (rectifyProblemReq.getProjectId() == null || rectifyProblemReq.getProjectId() <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (StringUtils.isBlank(rectifyProblemReq.getProjectName())) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (rectifyProblemReq.getRuleId() == null || rectifyProblemReq.getRuleId() <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (StringUtils.isBlank(rectifyProblemReq.getRuleName())) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (StringUtils.isBlank(rectifyProblemReq.getProblemName())) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (StringUtils.isBlank(rectifyProblemReq.getCorrectiveAction())) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (CollectionUtils.isEmpty(rectifyProblemReq.getProblemPics())) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (rectifyProblemReq.getTerm() == null || rectifyProblemReq.getTerm() <= 0) {
            throw BusinessException.INVALID_PARAMS;
        }

    }

}

package com.marvel.web.controller;

import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.controller.req.StaffInfoReq;
import com.marvel.web.converts.StaffConvert;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Classname StaffController
 * @Description 员工apis
 * @Date 2019/4/24 下午11:18
 * @Author zhongjie
 */
@RestController
@RequestMapping("/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 修改员工信息
     * @param staffInfoReq
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/update_staff_info.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateStaffInfo(StaffInfoReq staffInfoReq){
        if (Objects.isNull(staffInfoReq) || staffInfoReq.getId() == null) {
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = staffService.update(RequestContext.getRequestContext(), StaffConvert.convert(staffInfoReq));
        if (!result) {
            throw BusinessException.UPDATE_ERROR;
        }
        return StringUtils.EMPTY;
    }

}

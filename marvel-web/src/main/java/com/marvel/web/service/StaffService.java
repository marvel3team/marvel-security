package com.marvel.web.service;

import com.marvel.framework.context.RequestContext;
import com.marvel.web.po.Staff;

/**
 * @Classname StaffService
 * @Description 员工service接口类
 * @Date 2019/4/24 下午11:37
 * @Author zhongjie
 */
public interface StaffService {

    /**
     * 更新员工信息
     * @param requestContext
     * @param convert
     * @return
     */
    boolean update(RequestContext requestContext, Staff convert);
}

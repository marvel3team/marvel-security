package com.marvel.web.service;

import com.marvel.web.vo.BureauInfoReqVo;

/**
 * @Classname BureauService
 * @Description
 * @Author andy
 * @Date 2019/4/24 下午11:35
 * @Version 1.0
 */
public interface BureauService {


    /**
     * 更新科局员工信息
     * @param bureauInfoReqVo
     * @return
     */
    String updateBureauInfo(BureauInfoReqVo bureauInfoReqVo);
}

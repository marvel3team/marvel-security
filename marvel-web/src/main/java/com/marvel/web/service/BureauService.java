package com.marvel.web.service;

import com.marvel.common.models.PageBean;
import com.marvel.web.vo.BureauCompanyVo;
import com.marvel.web.vo.BureauInfoReqVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 新增科局人员信息
     * @param bureauInfoReqVo
     * @return
     */
    String addBureauInfo(BureauInfoReqVo bureauInfoReqVo);

    /**
     * 删除科局人员信息
     * @param id
     * @return
     */
    String delBureauInfo(Long id);

    String saveBureauCompanyInfo(BureauCompanyVo bureauCompanyVo);

    String updateBureauCompanyInfo(BureauCompanyVo bureauCompanyVo);

    String delBureauCompanyInfo(Long id);

    /**
     * 根据科举id查询科剧下所有的人员信息
     * @param id
     * @return
     */
    PageBean<BureauInfoReqVo> getBureauUserInfoList(Long id,Long cursor,Integer count);
}

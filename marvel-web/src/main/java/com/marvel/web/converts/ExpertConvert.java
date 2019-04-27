package com.marvel.web.converts;

import com.marvel.web.controller.req.ExpertInfoReq;
import com.marvel.web.po.ExpertInfo;

/**
 * @Classname ExpertConvert
 * @Description Expert转换器
 * @Date 2019/4/24 下午11:26
 * @Author zhongjie
 */
public class ExpertConvert {

    /**
     * 转换
     * @param expertInfoReq
     * @return
     */
    public static ExpertInfo convert(ExpertInfoReq expertInfoReq) {
        ExpertInfo expertInfo = new ExpertInfo();
        expertInfo.setId(expertInfoReq.getId());
        expertInfo.setName(expertInfoReq.getName());
        expertInfo.setIdCardNo(expertInfoReq.getIdCardNo());
        expertInfo.setCompanyId(expertInfoReq.getCompanyId());
        expertInfo.setWorkAddress(expertInfoReq.getCompanyAddress());
        expertInfo.setWorkLife(expertInfoReq.getWorkLife());
        expertInfo.setPositionalTitle(expertInfoReq.getPositionalTitle());
        expertInfo.setIsSyndic(expertInfoReq.getIsSyndic());
        expertInfo.setLevel(expertInfoReq.getLevel());
        expertInfo.setEvaluateRange(expertInfoReq.getEvaluateRange());
        expertInfo.setCollage(expertInfoReq.getCollage());
        expertInfo.setHomeAddress(expertInfoReq.getHomeAddress());
        expertInfo.setMobile(expertInfoReq.getMobile());
        expertInfo.setRemark(expertInfoReq.getRemark());
        expertInfo.setSignUrl(expertInfoReq.getSignUrl());
        return expertInfo;
    }
}

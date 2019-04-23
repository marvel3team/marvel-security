package com.marvel.web.service;

import com.marvel.web.vo.RemoteInfoReqVo;

/**
 * @Classname PlanService
 * @Description 计划服务
 * @Author andy
 * @Date 2019/4/23 下午9:37
 * @Version 1.0
 */
public interface PlanService {

    /**
     * 创建计划
     * @param remoteInfo
     * @return
     */
    boolean createRemoteInfo(RemoteInfoReqVo remoteInfo);

    /**
     * 更新
     * @param remoteInfo
     * @return
     */
    boolean updateRemoteInfo(RemoteInfoReqVo remoteInfo);
}

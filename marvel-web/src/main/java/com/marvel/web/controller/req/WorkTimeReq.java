package com.marvel.web.controller.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname WorkTimeReq
 * @Description 专家工作时间列表
 * @Date 2019/4/28 上午12:15
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkTimeReq implements Serializable {

    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;

}

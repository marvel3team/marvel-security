package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname ResondPlan
 * @Description 响应计划表
 * @Author andy
 * @Date 2019/4/23 下午11:24
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespondPlan implements Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 计划id
     */
    private Long planId;

    /**
     * 专家id
     */
    private Long expertId;

    /**
     * 计划时间
     */
    private Long planTime;

    /**
     * 方式 1远程 2现场
     */
    private Integer type;

    /**
     * 状态 1 未完成 2 已经完成 3 超时取消
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}

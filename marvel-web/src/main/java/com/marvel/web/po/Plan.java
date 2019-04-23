package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname Plan
 * @Description
 * @Author andy
 * @Date 2019/4/23 下午10:24
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan implements Serializable {

    /**
     * 计划id
     */
    private Long id;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 科局工作人员id
     */
    private Long bureauId;

    /**
     * 计划时间
     */
    private Long planTime;

    /**
     * 时间段
     */
    private Integer timeSlot;

    /**
     * 企业工作人员
     */
    private Long staffId;

    /**
     * 监管等级
     */
    private Integer superversionLevel;

    /**
     * 计划科目
     */
    private String planSubject;

    /**
     * 不符合项id
     */
    private Long nonConformanceId;

    /**
     * 整改结果id
     */
    private Long rectificationResultId;

    /**
     * 完成时间
     */
    private Long finishTime;

    /**
     * 状态
     */
    private Integer status;



}

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

    /**
     * 业务名称
     */
    private String name;

    /**
     * 业务类型名称
     */
    private String typeName;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 其他城市
     */
    private String otherCity;

    /**
     * 服务内容
     */
    private String serviceContent;

    /**
     * 领域
     */
    private String domain;

    /**
     * 具体领域
     */
    private String domainDetails;

    /**
     * 领域细分
     */
    private String domainMince;

    /**
     * 服务方式
     */
    private String serviceType;

    /**
     * 排查企业
     */
    private String investigationCompany;

}

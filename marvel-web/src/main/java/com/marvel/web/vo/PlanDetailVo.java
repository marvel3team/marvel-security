package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname PlanDetailVo
 * @Description 计划信息
 * @Author andy
 * @Date 2019/4/23 下午11:08
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetailVo {

    /**
     * 计划id
     */
    private Long id;


    /**
     * 科局员工id
     */
    private Long bureauId;

    /**
     * 科局员工名称
     */
    private String bureauName;

    /**
     * 计划时间
     */
    private Long planTime;

    /**
     * 计划天数
     */
    private Integer timeSlat;

    /**
     * 监管等级
     */
    private Integer superversionLevel;

    /**
     * 计划科目
     */
    private String planSubject;

    /**
     * 状态
     */
    private Integer planStatus;

    /**
     * 计划方式
     */
    private Integer respodType;

    /**
     * 完成状态
     */
    private Integer finishStatus;


    /**
     * 完成时间
     */
    private Long finishTime;

    /**
     * 专家名称
     */
    private String expertName;

    /**
     * 计划名称 - 业务名称
     */
    private String planName;

    /**
     * 企业名称
     */
    private String companyName;

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
     * address
     */
    private String address;

    /**
     * 行业类型
     */
    private String industryType;

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
     * 法人-联系人
     */
    private String legalPerson;

    /**
     * 法人联系方式
     */
    private String legalPersonMobile;

}

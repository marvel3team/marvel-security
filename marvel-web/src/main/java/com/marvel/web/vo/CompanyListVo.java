package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname CompanyListVo
 * @Description 公司列表返回信息
 * @Author andy
 * @Date 2019/4/22 下午10:36
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyListVo {

    /**
     * 企业id
     */
    private Long id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 地区编码
     */
    private Integer areaId = -1;

    /**
     * 注册资本
     */
    private String registedCapital = "";

    /**
     * 法人代表
     */
    private String legalPerson = "";

    /**
     * 法人电话
     */
    private String mobile = "";

    /**
     * 经营类型
     */
    private String businessCode = "";

    /**
     * 邮箱
     */
    private String email = "";

    /**
     * 营业执照
     */
    private String businessLicenseId = "";

    /**
     * 行业类型
     */
    private String industryType = "";

    /**
     * 安全等级
     */
    private String safetyLevel = "";

    /**
     * 安全证书到期日期
     */
    private Long certificateStartTime = 0L;

    /**
     * 安全证书到期日期
     */
    private Long certificateEndTime = 0L;

    /**
     * 重大风险源
     */
    private String majorRiskSources = "";

    /**
     * 较大风险源
     */
    private String higherRiskSources = "";

    /**
     * 一般风险源
     */
    private String generalRiskSources = "";

    /**
     * 低级风险源
     */
    private String lowRiskSources = "";

}

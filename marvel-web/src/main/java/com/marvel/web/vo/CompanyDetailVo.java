package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname CompanyDetailVo
 * @Description 企业详情
 * @Author andy
 * @Date 2019/4/22 下午11:18
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailVo {

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
    private Integer areaId;

    /**
     * 注册资本
     */
    private String registedCapital;

    /**
     * 法人代表
     */
    private String legalPerson;

    /**
     * 法人电话
     */
    private String mobile;

    /**
     * 经营类型
     */
    private String businessCode;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 营业执照
     */
    private String businessLicenseId;

    /**
     * 年生产天数
     */
    private Integer productionDay;
    /**
     * 总投资金额（元）
     */
    private String totalInvest;
    /**
     * 固定投资（元）
     */
    private String fixedInvest;
    /**
     * 上一年营业收入（元）
     */
    private String lastYearIncome;
    /**
     * 厂区占地面积（平米 * 100）
     */
    private Integer factoryArea;
    /**
     * 建筑面积（平米 * 100）
     */
    private Integer coveredArea;
    /**
     * 原材料年用量
     */
    private Integer rawStockDosage;
    /**
     * 辅料年用量
     */
    private Integer accessoriesDosage;
    /**
     * 年产量
     */
    private Integer yearlyCapacity;
    /**
     * 产量单位 1吨 ....
     */
    private Integer outputUnit;
    /**
     * 有无特种设备 1有
     */
    private Integer isSpecialEquipment;
    /**
     * 有无配电室 1有
     */
    private Integer isDistributionRoom;
    /**
     * 有无变压器 1有
     */
    private Integer isTransformer;
    /**
     * 是否取得标准化证书 1是
     */
    private Integer isStanCertificate;
    /**
     * 标准化证书类型（是否采用枚举更合适？）
     */
    private String stanCertificateType;
    /**
     * 标准化证书编号
     */
    private String stanCertificateId;
    /**
     * 是否网上申报 1是
     */
    private Integer isDeclareOnline;
    /**
     * 是否有安全部员证 1是
     */
    private Integer isSafeProof;
    /**
     * 安全部员证存档编号
     */
    private String safeProofArchiveNo;
    /**
     * 工作制度（1 一班倒 2 两班倒 3 三班倒）
     */
    private Integer workSystem;
    /**
     * 生产车间人数
     */
    private Integer productionDepartmentPeoples;
    /**
     * 办公室及后勤人数
     */
    private Integer officePeoples;
    /**
     * 年工作天数
     */
    private Integer workDaysYearly;
}

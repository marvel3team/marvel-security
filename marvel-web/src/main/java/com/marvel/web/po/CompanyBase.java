package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname CompanyBase
 * @Description 企业基础数据信息
 * @Date 2019/4/21 下午5:16
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyBase implements Serializable {
    /**
     * 企业id
     */
    private Long id;
    /**
     * 年生产天数
     */
    private Integer productionDay;
    /**
     * 总投资金额（分）
     */
    private Long totalInvest;
    /**
     * 固定投资（分）
     */
    private Long fixedInvest;
    /**
     * 上一年营业收入（分）
     */
    private Long lastYearIncome;
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
    private Integer yearlyWorkDays;
    /**
     * 行业类型
     */
    private Integer industryType;

    /**
     * 安全等级
     */
    private Integer safetyLevel;
    /**
     * 安全证书到期日期
     */
    private Long certificateStartTime;
    /**
     * 安全证书到期日期
     */
    private Long certificateEndTime;
    /**
     * 重大风险源
     */
    private String majorRiskSources;
    /**
     * 较大风险源
     */
    private String higherRiskSources;
    /**
     * 一般风险源
     */
    private String generalRiskSources;
    /**
     * 低级风险源
     */
    private String lowRiskSources;
}

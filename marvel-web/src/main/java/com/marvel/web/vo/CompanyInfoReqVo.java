package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @Classname CompnyInfoReqVo
 * @Description 企业信息请求类
 * @Author andy
 * @Date 2019/4/24 下午11:07
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfoReqVo {

    /**
     * 企业id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 地区id
     */
    private Integer areaId;

    /**
     * 注册资本
     */
    private String registedCapital;

    /**
     * 法人
     */
    private String legalPreson;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 经营类型编码
     */
    private String businessCode;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 营业执照编码
     */
    private String businessLicenseNo;

    /**
     * 行业id
     */
    private Long industryId;


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

package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname CompanyInfo
 * @Description 企业基本信息
 * @Author andy
 * @Date 2019/4/22 下午10:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStandard implements Serializable {

    /**
     * 企业id
     */
    private Long id;

    /**
     * 企业名称
     */
    private String name = "";

    /**
     * 地区id
     */
    private Integer areaId = -1;

    /**
     * 省
     */
    private String province = "";

    /**
     * 市，地区
     */
    private String city = "";

    /**
     * 县，其他城市
     */
    private String otherCity = "";

    /**
     * 详细地址
     */
    private String address = "";

    /**
     * 企业类型 1 被检查公司  2 检查公司 3 科局公司 4 专家公司
     */
    private Integer type = 1;

    /**
     * 行业id
     */
    private Long industryId = -1L;

    /**
     * 注册资本（分）
     */
    private Long registedCapital = 0L;

    /**
     * 法人名称
     */
    private String legalPerson = "";

    /**
     * 法人电话
     */
    private String legalPersonMobile = "";

    /**
     * 经营类型
     */
    private String businessTypeCode = "";

    /**
     * 邮箱
     */
    private String email = "";

    /**
     * 营业执照编码
     */
    private String businessLicenseId = "";


}

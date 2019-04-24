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


}

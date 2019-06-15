package com.marvel.web.controller.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname ExpertInfoReq
 * @Description 专家信息DTO
 * @Author zhongjie
 * @Date 2019/4/26 下午11:41
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertInfoReq {

    /**
     * 专家id
     */
    private Long id;

    /**
     * 专家名称
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 公司名称
     */
    private String workCompany;

    /**
     * 工作地址
     */
    private String companyAddress;

    /**
     * 工作年限
     */
    private Integer workLife;

    /**
     * 职称
     */
    private String positionalTitle;

    /**
     * 是否评审员 1 是 2 否
     */
    private Integer isSyndic;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 评审范围
     */
    private String evaluateRange;

    /**
     * 专业
     */
    private String collage;

    /**
     * 居住地址
     */
    private String homeAddress;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 备注
     */
    private String remark;

    /**
     * 签名照片地址
     */
    private String signUrl;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 民族
     */
    private String nation;

    /**
     * 最高学历
     */
    private String highestDegree;

    /**
     * 工作履历
     */
    private String jobResume;

    /**
     * 专业类别
     */
    private String categories;

    /**
     * 荣誉
     */
    private String honor;


}

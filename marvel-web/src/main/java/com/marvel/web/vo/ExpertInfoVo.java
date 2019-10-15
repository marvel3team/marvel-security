package com.marvel.web.vo;

import lombok.Data;

/**
 * @Classname ExpertInfoVo
 * @Description 专家信息
 * @Author andy
 * @Date 2019/4/22 下午11:52
 * @Version 1.0
 */
@Data
public class ExpertInfoVo {

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
    private String idCardNo = "";

    /**
     * 公司名称
     */
    private String workCompany = "";

    /**
     * 工作地址
     */
    private String workAddress = "";

    /**
     * 工作年限
     */
    private Integer workLife = 0;

    /**
     * 职称
     */
    private String positionalTitle = "";

    /**
     * 是否评审员 1 是 2 否
     */
    private Integer isSyndic = 2;

    /**
     * 级别
     */
    private Integer level = -1;

    /**
     * 评审范围
     */
    private String evaluateRange = "";

    /**
     * 专业
     */
    private String collage = "";

    /**
     * 居住地址
     */
    private String homeAddress = "";

    /**
     * 手机号
     */
    private String mobile = "";

    /**
     * 备注
     */
    private String remark = "";

    /**
     * 签名照片地址
     */
    private String signUrl = "";

    /**
     * 地区
     */
    private String area = "";

    /**
     * 行业
     */
    private String industry = "";

    /**
     * 性别
     */
    private String sex = "";

    /**
     * 民族
     */
    private String nation = "";

    /**
     * 最高学历
     */
    private String highestDegree = "";

    /**
     * 工作履历
     */
    private String jobResume = "";

    /**
     * 专业类别
     */
    private String categories = "";

    /**
     * 荣誉
     */
    private String honor = "";


}

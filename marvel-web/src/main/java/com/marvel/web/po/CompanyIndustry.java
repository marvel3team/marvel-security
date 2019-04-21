package com.marvel.web.po;

/**
 * @Classname CompanyIndustry
 * @Description 企业行业信息
 * @Date 2019/4/21 下午6:06
 * @Author zhongjie
 */
public class CompanyIndustry {
    /**
     * 行业ID
     */
    private Long id;
    /**
     * 行业分类type，枚举类型
     */
    private Integer type;
    /**
     * 行业信息
     */
    private String content;
    /**
     * 服务周期
     */
    private Integer serviceCycle;
    /**
     * 费用（精确到分）
     */
    private Long cost;
}

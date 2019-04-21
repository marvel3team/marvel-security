package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname CompanyAppraise
 * @Description 企业鉴定信息
 * @Date 2019/4/21 下午5:07
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAppraise implements Serializable {
    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 鉴定方式 1 年检 2 三年检 3 半年检 4 月检
     */
    private Integer appraiseMethod;
    /**
     * 上次鉴定日期
     */
    private Long lastAppraiseTime;
    /**
     * 上次计划执行id
     */
    private Long lastPlanExecuteId;
}

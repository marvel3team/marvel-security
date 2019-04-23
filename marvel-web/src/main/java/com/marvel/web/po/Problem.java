package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @Classname Problem
 * @Description t_problem_info
 * 整改问题信息
 * @Date 2019/4/23 下午11:12
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem implements Serializable {
    /**
     * 问题ID
     */
    private Long id;
    /**
     * 服务计划 id
     */
    private Long planId;
    /**
     * 专家 id
     */
    private Long expertId;
    /**
     * 项目Id
     */
    private Integer projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 细则Id
     */
    private Integer ruleId;
    /**
     * 细则名称
     */
    private String ruleName;
    /**
     * 问题描述
     */
    private String problemName;
    /**
     * 整改措施
     */
    private String correctiveAction;
    /**
     * 期限(天)
     */
    private Integer term;
    /**
     * 整改状态 1待整改 2已整改 3整改不合格 4整改完成
     */
    private Integer status;
    /**
     * 现场图片
     */
    List<String> pics;
    /**
     * 更新时间
     */
    private Long updateTime;

}

package com.marvel.web.controller.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname RectifyProblemReq
 * @Description 整改问题
 * @Date 2019/4/23 下午9:47
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RectifyProblemReq implements Serializable {

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
     * 问题ID
     */
    private Long problemId;
    /**
     * 问题描述
     */
    private String problemName;
    /**
     * 整改措施
     */
    private String correctiveAction;
    /**
     * 现场图片集合
     */
    private List<String> problemPics;
    /**
     * 期限(天)
     */
    private Integer term;
    /**
     * 整改状态 1待整改 2已整改 3整改不合格 4整改完成
     */
    private Integer status;
}

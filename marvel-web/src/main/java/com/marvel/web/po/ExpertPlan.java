package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname ExpertPlan
 * @Description 专家计划
 * @Author andy
 * @Date 2019/4/23 下午11:26
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertPlan implements Serializable {

    /**
     * 计划id
     */
    private Long planId;

    /**
     * 专家id
     */
    private Long expertId;
}

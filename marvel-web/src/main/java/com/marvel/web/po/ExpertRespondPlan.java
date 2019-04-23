package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname ExpertRespondPlan
 * @Description 专家响应计划
 * @Author andy
 * @Date 2019/4/23 下午11:27
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertRespondPlan implements Serializable {

    /**
     * 计划id
     */
    private Long planId;

    /**
     * 专家id
     */
    private Long expertId;


}

package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname ExpertTime
 * @Description 专家时间
 * @Date 2019/4/27 下午11:56
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertTime implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 专家ID
     */
    private Long expertId;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 专家类型？ 是否放到专家表更合适
     */
    private Integer expertType;

}

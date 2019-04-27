package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname ExpertTimeVo
 * @Description 专家时间VO
 * @Date 2019/4/28 上午12:36
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertTimeVo implements Serializable {

    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;

}

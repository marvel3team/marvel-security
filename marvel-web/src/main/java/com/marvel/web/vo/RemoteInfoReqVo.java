package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname RemoteInfoReqVo
 * @Description
 * @Author andy
 * @Date 2019/4/23 下午9:41
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoteInfoReqVo {

    /**
     * 计划id
     */
    private Long id;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 科局id
     */
    private Long bureauId;

    /**
     * 计划开始时间
     */
    private Long planTime;

    /**
     * 计划周期
     */
    private Integer timeSlat;

    /**
     * 监管等级
     */
    private Integer superversionLevel;

    /**
     * 计划科目
     */
    private String planSubject;

    /**
     * 状态
     */
    private Integer status;
}

package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname BureauInfoReqVo
 * @Description 科局人员信息
 * @Author andy
 * @Date 2019/4/24 下午11:36
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BureauInfoReqVo {

    /**
     * id
     */
    private Long id;

    /**
     * 地区id
     */
    private Integer areaId;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 备注
     */
    private String remark;
}

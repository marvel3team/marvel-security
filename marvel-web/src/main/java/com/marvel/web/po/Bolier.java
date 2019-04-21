package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname Bolier
 * @Description 锅炉信息
 * @Date 2019/4/21 下午4:55
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bolier implements Serializable {

    /**
     * 企业id
     */
    private Long companyId;
    /**
     * 设备编号或厂家编号
     */
    private String deviceNo;
    /**
     * 容积
     */
    private Integer volume;
    /**
     * 压力
     */
    private Integer pressure;
    /**
     * 备案证存档编号
     */
    private String filingNo;
    /**
     * 管理员id
     */
    private Long adminId;

}

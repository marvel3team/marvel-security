package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname CompanyDevice
 * @Description 公司设备
 * @Date 2019/4/21 下午5:49
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDevice implements Serializable {
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 设备数量
     */
    private Integer number;
    /**
     * 规格型号(是否是确认的几种？用枚举类型？)
     */
    private String normalType;
    /**
     * 是否特殊设备 1是
     */
    private Integer isSpecialDevice;
    /**
     * 是否主要设备 1是
     */
    private Integer isMainDevice;
    /**
     * 是否环保设备 1是
     */
    private Integer isEnvironmentDevice;
    /**
     * 是否职业健康设备 1是
     */
    private Integer isOccupationalHealthDevice;
    /**
     * 是否移动设备 1是
     */
    private Integer isMobileDevice;
    /**
     * 是否安全检验 1是
     */
    private Integer isSafeCheck;
    /**
     * 安全检验周期（天）
     */
    private Integer safeCheckCycle;

}

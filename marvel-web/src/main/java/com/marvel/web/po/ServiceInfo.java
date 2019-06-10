package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname ServiceInfo
 * @Description t_service_info
 * @Date 2019/4/24 下午10:18
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfo implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 类型
     */
    private Integer serviceType;
    /**
     * 名称
     */
    private String serviceName;
    /**
     * 周期
     */
    private Integer serviceCycle;

    /**
     * 业务描述
     */
    private String serviceDesc;

}

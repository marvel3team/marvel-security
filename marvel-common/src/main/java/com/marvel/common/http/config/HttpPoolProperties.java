package com.marvel.common.http.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname HttpPoolProperties
 * @Description http pool配置类
 * @Date 2019-04-22 14:06
 * @Author zhongjie
 */
@Component
@Data
@ConfigurationProperties(prefix = "http-pool")
public class HttpPoolProperties {
    /**
     * 设置整个连接池最大连接数
     */
    private Integer maxTotal;
    /**
     * 单路由的默认最大连接
     */
    private Integer defaultMaxPerRoute;
    /**
     * 建立连接的timeout时间
     */
    private Integer connectTimeout;
    /**
     * 从连接池获取连接的timeout
     */
    private Integer connectionRequestTimeout;
    /**
     * 数据传输处理时间
     */
    private Integer socketTimeout;
    /**
     * 空闲永久连接检查间隔
     */
    private Integer validateAfterInactivity;
}

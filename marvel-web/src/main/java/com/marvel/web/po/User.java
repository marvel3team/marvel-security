package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname User
 * @Description 用户类，对应表关系
 * @Date 2019/2/19 上午10:40
 * @Author zj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 编号
     */
    private Long uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 登录状态
     */
    private Integer status;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 创建时间,精确到毫秒
     */
    private Long createTime;
    /**
     * 更新时间,精确到毫秒
     */
    private Long updateTime;

}

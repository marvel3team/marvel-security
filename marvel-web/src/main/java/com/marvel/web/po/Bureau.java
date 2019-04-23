package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname Bureau
 * @Description 科局人员信息
 * @Date 2019/4/21 下午5:00
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bureau implements Serializable {
    /**
     * 科局人员id
     */
    private Long id;
    /**
     * 地区id
     */
    private Integer aresId;

    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 姓名
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

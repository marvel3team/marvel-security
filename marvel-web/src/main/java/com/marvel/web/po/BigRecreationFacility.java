package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname BigRecreationFacility
 * @Description 大型娱乐设施信息
 * @Date 2019/4/21 下午4:51
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BigRecreationFacility implements Serializable {

    /**
     * 企业id
     */
    private Long companyId;
    /**
     * 管理人员id
     */
    private Long adminId;
}

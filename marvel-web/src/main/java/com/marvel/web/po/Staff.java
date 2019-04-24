package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname Staff
 * @Description t_company_staff
 * @Date 2019/4/24 下午10:54
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements Serializable {

    /**
     * 员工id
     */
    private Long id;
    /**
     * 所属公司id
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
     * 身份证号码
     */
    private String idCardNo;
    /**
     * 部门id
     */
    private Long departmentId;
    /**
     * 车间id
     */
    private Long workshopId;
    /**
     * 班组id
     */
    private Long teamId;
    /**
     * 工种id（枚举？可确定的？）
     */
    private Long professionId;
    /**
     * 是否特种作业人员 1是
     */
    private Integer isSpecialPersonnel;
    /**
     * 特种作业人员id
     */
    private Long specialPersonnelId;
    /**
     * 备注
     */
    private String remark;

}

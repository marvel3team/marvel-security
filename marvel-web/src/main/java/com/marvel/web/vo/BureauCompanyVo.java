package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author andy
 * @version V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p>
 * <p>版权所有: Copyright©1999-2019 leyou.com. All Rights Reserved</p>
 * @Title: BureauCompanyVo
 * @Package marvel-security
 * @Description: 科局公司
 * @date 2019/8/2921:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BureauCompanyVo {

    /**
     * 企业id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 地区id
     */
    private Integer areaId = -1;

    /**
     * 注册资本
     */
    private String registedCapital = "";

    /**
     * 法人
     */
    private String legalPreson = "";

    /**
     * 手机号
     */
    private String mobile = "";

    /**
     * 经营类型编码
     */
    private String businessCode = "";

    /**
     * 邮箱
     */
    private String email = "";


    /**
     * 营业执照编码
     */
    private String businessLicenseNo = "";
}

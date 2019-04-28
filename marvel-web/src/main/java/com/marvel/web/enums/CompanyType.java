package com.marvel.web.enums;

/**
 * @author andy
 * @version V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p>
 * <p>版权所有: Copyright©1999-2019 leyou.com. All Rights Reserved</p>
 * @Title: CompanyType
 * @Package marvel-security
 * @Description: 公司类型
 * @date 2019/4/2816:13
 */
public enum CompanyType {

    ENTERPRISE(1, "被检查企业"),
    GOVERNMENT(2, "科局"),
    EXPERT(3, "专家公司"),
    BUSINESSMAN(4, "业务员");

    private int value;
    private String desc;

    CompanyType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int value(){
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public static CompanyType valueOf(Integer value) {
        if (value != null) {
            CompanyType[] values = CompanyType.values();
            for(CompanyType type : values) {
                if (value == type.value()) {
                    return type;
                }
            }
        }
        return null;
    }
}

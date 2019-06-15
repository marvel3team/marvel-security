package com.marvel.web.enums;

/**
 * @Classname IndustryType
 * @Description 行业类别
 * @Author andy
 * @Date 2019/6/15 下午9:33
 * @Version 1.0
 */
public enum IndustryType {

    /**
     * 行业类别，待完善
     */
    JIXIE(1, "机械")

    ;

    private int value;
    private String desc;

    IndustryType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public int value(){
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public static IndustryType valueOf(Integer value) {
        if (value != null) {
            IndustryType[] values = IndustryType.values();
            for(IndustryType status : values) {
                if (value == status.value()) {
                    return status;
                }
            }
        }
        return null;
    }
}

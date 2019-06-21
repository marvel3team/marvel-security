package com.marvel.web.enums;

/**
 * @Classname SafetyLevel
 * @Description 安全等级枚举
 * @Author andy
 * @Date 2019/6/15 下午9:36
 * @Version 1.0
 */
public enum SafetyLevel {

    /**
     * 一级
     */
    LEVEL_ONE(1, "一级"),
    /**
     * 二级
     */
    LEVEL_TWO(2, "二级"),
    /**
     * 三级
     */
    LEVEL_THREE(3, "三级"),

    ;

    private int value;
    private String desc;

    SafetyLevel(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public int value(){
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public static SafetyLevel valueOf(Integer value) {
        if (value != null) {
            SafetyLevel[] values = SafetyLevel.values();
            for(SafetyLevel status : values) {
                if (value == status.value()) {
                    return status;
                }
            }
        }
        return null;
    }
}

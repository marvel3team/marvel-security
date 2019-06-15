package com.marvel.web.enums;

/**
 * @Classname Sex
 * @Description 性别
 * @Author andy
 * @Date 2019/6/15 下午9:05
 * @Version 1.0
 */
public enum Sex {

    MAN(1, "男"),
    WOMAN(2, "女");

    private int value;
    private String desc;

    Sex(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int value(){
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public static Sex valueOf(Integer value) {
        if (value != null) {
            Sex[] values = Sex.values();
            for(Sex type : values) {
                if (value == type.value()) {
                    return type;
                }
            }
        }
        return null;
    }
}

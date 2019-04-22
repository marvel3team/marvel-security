package com.marvel.web.enums;

/**
 * @Classname UserType
 * @Description 用户类型
 * @Date 2019/3/27 下午9:21
 * @Author zhongjie
 */
public enum UserType {

    ENTERPRISE(1, "企业"),
    GOVERNMENT(2, "政府"),
    EXPERT(3, "专家"),
    BUSINESSMAN(4, "业务员");

    private int value;
    private String desc;

    UserType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int value(){
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public static UserType valueOf(Integer value) {
        if (value != null) {
            UserType[] values = UserType.values();
            for(UserType type : values) {
                if (value == type.value()) {
                    return type;
                }
            }
        }
        return null;
    }
}


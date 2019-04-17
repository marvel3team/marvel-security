package com.marvel.web.enums;

/**
 * @Classname UserType
 * @Description 用户类型
 * @Date 2019/3/27 下午9:21
 * @Author zhongjie
 */
public enum UserType {

    MANAGE_DEPT(10, "应急管理部门"),
    EXPERT(20, "应急专家"),
    ENTERPRISE(30, "企业客户"),
    STAFF(40, "员工"),
    VISITOR(50, "游客");

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


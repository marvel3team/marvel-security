package com.marvel.web.enums;

/**
 * @Classname LoginStatus
 * @Description 登录状态
 * @Date 2019/3/27 下午9:21
 * @Author zhongjie
 */
public enum LoginStatus {

    LOGIN(1, "登录"),
    LOGOUT(2, "注销");

    private int value;
    private String desc;

    LoginStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int value(){
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public static LoginStatus valueOf(Integer value) {
        if (value != null) {
            LoginStatus[] values = LoginStatus.values();
            for(LoginStatus status : values) {
                if (value == status.value()) {
                    return status;
                }
            }
        }
        return null;
    }
}


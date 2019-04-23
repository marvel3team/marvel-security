package com.marvel.web.enums;

/**
 * @Classname PlanStatus
 * @Description 计划状态
 * @Author andy
 * @Date 2019/4/23 下午10:46
 * @Version 1.0
 */
public enum PlanStatus {

    BUREAU(10, "科局发起"),
    COMPANY_CLAIM(20, "企业工作人员认领任务"),
    EXPERT_CONFIRM(30, " 专家确认时间"),
    REQDY_EXECUTION(40, "准备执行"),
    RUNNING(50, "执行中"),
    NON_CONFORMITIES(60, "开具不符合项"),
    FINISH(70, "整改完毕"),
    BUREAU_CANCWEL(80, "科局取消"),
    NO_TIME_CANCEL(90, "未确定时间计划取消"),
    RUN_EXCEPTION_CANCEL(100, "执行前异常取消"),

    ;

    private int value;
    private String desc;

    PlanStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public int value(){
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public static PlanStatus valueOf(Integer value) {
        if (value != null) {
            PlanStatus[] values = PlanStatus.values();
            for(PlanStatus status : values) {
                if (value == status.value()) {
                    return status;
                }
            }
        }
        return null;
    }
}

package com.hlw.edu.enums;

/**
 * @description: Clazz状态枚举类
 */
public enum ClazzStateEnum {
    /**
     * @description: 状态枚举
     */
    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY_LIST(-1002, "添加数少于1"),
    EMPTY(-1003, "班级信息为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private ClazzStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * @description: 依据传入的state返回相应的enum值
     */
    public static ClazzStateEnum stateOf(int state){
        for (ClazzStateEnum stateEnum : values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}

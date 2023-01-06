package com.hlw.edu.enums;

/**
 * @description: Homework状态枚举类
 */
public enum HomeworkStateEnum {
    /**
     * @description: 状态枚举
     */
    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "作业信息为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private HomeworkStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * @description: 依据传入的state返回相应的enum值
     */
    public static HomeworkStateEnum stateOf(int state){
        for (HomeworkStateEnum stateEnum : values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}

package com.hlw.edu.dto;

import com.hlw.edu.entity.StudentCourse;
import com.hlw.edu.enums.StudentCourseStateEnum;

import java.util.List;

/**
 * @description: StudentCourse构造类
 */
public class StudentCourseExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的studentCourse
    private StudentCourse studentCourse;

    // 获取的studentCourse列表
    private List<StudentCourse> studentCourseList;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public StudentCourse getStudentCourse() { return studentCourse; }

    public void setStudentCourse(StudentCourse studentCourse) {
        this.studentCourse = studentCourse;
    }

    public List<StudentCourse> getStudentCourseList() { return studentCourseList; }

    public void setStudentCourseList(List<StudentCourse> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }

    // 空的构造器
    public StudentCourseExecution() {
    }

    // 失败的构造器
    public StudentCourseExecution(StudentCourseStateEnum studentCourseStateEnum) {
        this.state = studentCourseStateEnum.getState();
        this.stateInfo = studentCourseStateEnum.getStateInfo();
    }

    // 成功的构造器
    public StudentCourseExecution(StudentCourseStateEnum studentCourseStateEnum, StudentCourse studentCourse) {
        this.state = studentCourseStateEnum.getState();
        this.stateInfo = studentCourseStateEnum.getStateInfo();
        this.studentCourse = studentCourse;
    }

    // 成功的构造器
    public StudentCourseExecution(StudentCourseStateEnum studentCourseStateEnum, List<StudentCourse> studentCourseList) {
        this.state = studentCourseStateEnum.getState();
        this.stateInfo = studentCourseStateEnum.getStateInfo();
        this.studentCourseList = studentCourseList;
    }
}

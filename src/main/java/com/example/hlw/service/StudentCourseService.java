package com.hlw.edu.service;

import com.hlw.edu.dto.StudentCourseExecution;
import com.hlw.edu.entity.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    /**
     * @description: 查询全部选课信息
     */
    List<StudentCourse> getStudentCourseList();

    /**
     * @description: 通过选课ID获取指定选课信息
     */
    StudentCourse getStudentCourseById(long studentCourseId);

    /**
     * @description: 通过studentId查询选课信息列表
     */
    List<StudentCourse> getStudentCourseByStudentId(long studentId);

    /**
     * @description: 通过课程ID获取学生列表信息
     */
    List<StudentCourse> getStudentByCourseId(long courseId);

    /**
     * @description: 新增选课信息
     */
    StudentCourseExecution addStudentCourse(StudentCourse studentCourse);

    /**
     * @description: 修改选课信息
     */
    StudentCourseExecution modifyStudentCourse(StudentCourse studentCourse);

    /**
     * @description: 删除指定选课信息
     */
    StudentCourseExecution deleteStudentCourse(long studentCourseId);
}

package com.hlw.edu.service;

import com.hlw.edu.dto.FileHolder;
import com.hlw.edu.dto.StudentHomeworkExecution;
import com.hlw.edu.entity.StudentHomework;

import java.util.List;

public interface StudentHomeworkService {
    /**
     * @description: 查询全部学生作业列表信息
     */
    List<StudentHomework> getStudentHomeworkList();

    /**
     * @description: 通过学生作业ID获取指定学生作业信息
     */
    StudentHomework getStudentHomeworkById(long studentHomeworkId);

    /**
     * @description: 通过课程ID获取学生作业列表信息
     */
    List<StudentHomework> getStudentHomeworkByCourseId(long courseId);

    /**
     * @description: 通过教师ID获取学生作业列表信息
     */
    List<StudentHomework> getStudentHomeworkByTeacherId(long teacherId);

    /**
     * @description: 通过传入的信息判断是否存在符合条件的学生作业信息
     */
    StudentHomework getStudentHomeworkCondition(StudentHomework studentHomeworkCondition);

    /**
     * @description: 新增学生作业信息
     */
    StudentHomeworkExecution addStudentHomework(StudentHomework studentHomework, FileHolder fileHolder);

    /**
     * @description: 修改学生作业信息
     */
    StudentHomeworkExecution modifyStudentHomework(StudentHomework studentHomework, FileHolder fileHolder);

    /**
     * @description: 删除指定学生作业信息
     */
    StudentHomeworkExecution deleteStudentHomework(long studentHomeworkId);
}

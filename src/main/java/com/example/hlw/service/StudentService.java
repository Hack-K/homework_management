package com.hlw.edu.service;

import com.hlw.edu.dto.ImageHolder;
import com.hlw.edu.dto.StudentExecution;
import com.hlw.edu.entity.Student;

import java.util.List;

public interface StudentService {
    /**
     * @description: 查询全部教师信息
     */
    List<Student> getStudentList();

    /**
     * @description: 通过学生ID获取指定学生信息
     */
    Student getStudentById(long studentId);

    /**
     * @description: 新增学生信息
     */
    StudentExecution addStudent(Student student, ImageHolder imageHolder);

    /**
     * @description: 修改学生信息
     */
    StudentExecution modifyStudent(Student student, ImageHolder imageHolder);

    /**
     * @description: 删除指定学生信息
     */
    StudentExecution deleteStudent(long studentId);
}

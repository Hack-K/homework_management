package com.hlw.edu.dao;

import com.hlw.edu.entity.Student;

import java.util.List;

/**
 * @description: 学生实体类dao层接口
 */
public interface StudentDao {
    /**
     * @description: 查询所有学生列表
     */
    List<Student> queryStudent();

    /**
     * @description: 通过studentId查询指定学生信息
     */
    Student queryStudentById(long studentId);
    
    /**
     * @description: 新增学生信息
     */
    int addStudent(Student student);
    
    /**
     * @description: 修改学生信息
     */
    int modifyStudent(Student student);
    
    /**
     * @description: 通过studentId删除指定学生
     */
    int deleteStudent(long studentId);
}

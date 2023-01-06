package com.hlw.edu.dao;

import com.hlw.edu.entity.StudentCourse;

import java.util.List;

/**
 * @description: 选课实体类dao层接口
 */
public interface StudentCourseDao {
    /**
     * @description: 查询所有选课列表
     */
    List<StudentCourse> queryStudentCourse();
    
    /**
     * @description: 通过studentCourseId查询指定选课信息
     */
    StudentCourse queryStudentCourseById(long studentCourseId);


    /**
     * @description: 通过studentId查询选课信息列表
     */
    List<StudentCourse> queryStudentCourseByStudentId(long studentId);

    /**
     * @description: 通过courseId查询选课信息列表
     */
    List<StudentCourse> queryStudentByCourseId(long courseId);
    
    /**
     * @description: 新增选课信息
     */
    int addStudentCourse(StudentCourse studentCourse);
    
    /**
     * @description: 修改选课信息
     */
    int modifyStudentCourse(StudentCourse studentCourse);
    
    /**
     * @description: 通过studentCourseId删除指定选课信息
     */
    int deleteStudentCourse(long studentCourseId);
}

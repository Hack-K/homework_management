package com.hlw.edu.dao;

import com.hlw.edu.entity.Course;

import java.util.List;

/**
 * @description: 课程实体类dao层接口
 */
public interface CourseDao {
    /**
     * @description: 查询所有课程列表
     */
    List<Course> queryCourse();
    
    /**
     * @description: 通过courseId查询指定课程信息
     */
    Course queryCourseById(long courseId);

    /**
     * @description: 通过teacherId查询课程信息列表
     */
    List<Course> queryCourseByTeacherId(long teacherId);
    
    /**
     * @description: 新增课程信息
     */
    int addCourse(Course course);
    
    /**
     * @description: 修改课程信息
     */
    int modifyCourse(Course course);
    
    /**
     * @description: 通过courseId删除指定课程信息
     */
    int deleteCourse(long courseId);
}

package com.hlw.edu.service;

import com.hlw.edu.dto.CourseExecution;
import com.hlw.edu.entity.Course;

import java.util.List;

public interface CourseService {
    /**
     * @description: 查询全部课程信息
     */
    List<Course> getCourseList();

    /**
     * @description: 通过课程ID获取指定课程信息
     */
    Course getCourseById(long courseId);

    /**
     * @description: 通过教师ID获取指定课程信息
     */
    List<Course> getCourseByTeacherId(long teacherId);

    /**
     * @description: 新增课程信息
     */
    CourseExecution addCourse(Course course);

    /**
     * @description: 修改课程信息
     */
    CourseExecution modifyCourse(Course course);

    /**
     * @description: 删除指定课程信息
     */
    CourseExecution deleteCourse(long courseId);
}

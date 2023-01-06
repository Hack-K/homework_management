package com.hlw.edu.service;

import com.hlw.edu.dto.ImageHolder;
import com.hlw.edu.dto.TeacherExecution;
import com.hlw.edu.entity.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * @description: 查询全部教师信息
     */
    List<Teacher> getTeacherList();
    
    /**
     * @description: 通过教师ID获取指定教师信息
     */
    Teacher getTeacherById(long teacherId);

    /**
     * @description: 新增教师信息
     */
    TeacherExecution addTeacher(Teacher teacher, ImageHolder thumbnail);

    /**
     * @description: 修改教师信息
     */
    TeacherExecution modifyTeacher(Teacher teacher, ImageHolder thumbnail);
    
    /**
     * @description: 删除指定教师信息
     */
    TeacherExecution deleteTeacher(long teacherId);
}

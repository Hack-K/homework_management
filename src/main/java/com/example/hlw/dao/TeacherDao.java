package com.hlw.edu.dao;

import com.hlw.edu.entity.Teacher;

import java.util.List;

/**
 * @description: 教师实体类dao层接口
 */
public interface TeacherDao {
    /**
     * @description: 查询所有教师列表
     */
    List<Teacher> queryTeacher();

    /**
     * @description: 通过teacherId查询指定教师信息
     */
    Teacher queryTeacherById(long teacherId);

    /**
     * @description: 新增教师信息
     */
    int addTeacher(Teacher teacher);

    /**
     * @description: 修改教师信息
     */
    int modifyTeacher(Teacher teacher);

    /**
     * @description: 通过teacherId删除指定教师
     */
    int deleteTeacher(long teacherId);
}

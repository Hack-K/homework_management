package com.hlw.edu.dao;

import com.hlw.edu.entity.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 作业实体类dao层接口
 */
public interface HomeworkDao {
    /**
     * @description: 查询所有作业列表
     */
    List<Homework> queryHomework();

    /**
     * @description: 通过homeworkId查询指定作业信息
     */
    Homework queryHomeworkById(long homeworkId);

    /**
     * @description: 通过courseId查询作业信息列表
     */
    List<Homework> queryHomeworkByCourseId(long courseId);

    /**
     * @description: 通过teacherId查询作业信息列表
     */
    List<Homework> queryHomeworkByTeacherId(long teacherId);

    /**
     * @description: 通过studentId查询作业信息列表
     */
    List<Homework> queryHomeworkByStudentId(@Param("studentId") long studentId);

    /**
     * @description: 新增作业信息
     */
    int addHomework(Homework homework);

    /**
     * @description: 修改作业信息
     */
    int modifyHomework(Homework homework);

    /**
     * @description: 通过homeworkId删除指定作业信息
     */
    int deleteHomework(long homeworkId);
}

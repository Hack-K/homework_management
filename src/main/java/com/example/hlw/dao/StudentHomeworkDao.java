package com.hlw.edu.dao;

import com.hlw.edu.entity.StudentHomework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 学生作业实体类dao层接口
 */
public interface StudentHomeworkDao {
    /**
     * @description: 查询所有学生作业列表
     */
    List<StudentHomework> queryStudentHomework();

    /**
     * @description: 通过studentHomeworkId查询指定学生作业信息
     */
    StudentHomework queryStudentHomeworkById(long studentHomeworkId);

    /**
     * @description: 通过courseId查询学生作业信息列表
     */
    List<StudentHomework> queryStudentHomeworkByCourseId(long courseId);

    /**
     * @description: 通过teacherId查询学生作业信息列表
     */
    List<StudentHomework> queryStudentHomeworkByTeacherId(long teacherId);

    /**
     * @description: 查询指定的学生作业信息是否存在
     */
    int queryStudentHomeworkExist(@Param("studentHomeworkCondition") StudentHomework studentHomeworkCondition);

    /**
     * @description: 通过传入的条件获取指定的学生信息
     */
    StudentHomework queryStudentHomeworkByCondition(@Param("studentHomeworkCondition") StudentHomework studentHomeworkCondition);

    /**
     * @description: 新增学生作业信息
     */
    int addStudentHomework(StudentHomework studentHomework);

    /**
     * @description: 修改学生作业信息
     */
    int modifyStudentHomework(StudentHomework studentHomework);

    /**
     * @description: 通过studentHomeworkId删除学生作业信息
     */
    int deleteStudentHomework(long studentHomeworkId);
}

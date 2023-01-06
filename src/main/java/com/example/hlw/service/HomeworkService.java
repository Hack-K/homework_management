package com.hlw.edu.service;

import com.hlw.edu.dto.FileHolder;
import com.hlw.edu.dto.HomeworkExecution;
import com.hlw.edu.entity.Homework;

import java.util.List;

public interface HomeworkService {
    /**
     * @description: 查询全部作业信息
     */
    List<Homework> getHomeworkList();

    /**
     * @description: 通过作业ID查询指定作业信息
     */
    Homework getHomeworkById(long homeworkId);

    /**
     * @description: 通过课程ID查询作业列表信息
     */
    List<Homework> getHomeworkByCourseId(long courseId);

    /**
     * @description: 通过教师ID查询作业列表信息
     */
    List<Homework> getHomeworkByTeacherId(long teacherId);

    /**
     * @description: 通过学生ID查询作业列表信息
     */
    List<Homework> getHomeworkByStudentId(long studentId);

    /**
     * @description: 新增作业信息
     */
    HomeworkExecution addHomework(Homework homework, FileHolder fileHolder);

    /**
     * @description: 修改作业信息
     */
    HomeworkExecution modifyHomework(Homework homework, FileHolder fileHolder);

    /**
     * @description: 删除指定作业信息
     */
    HomeworkExecution deleteHomework(long homeworkId);
}

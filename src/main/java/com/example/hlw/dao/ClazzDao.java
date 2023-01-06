package com.hlw.edu.dao;

import com.hlw.edu.entity.Clazz;

import java.util.List;

/**
 * @description: 班级实体类dao层接口
 */
public interface ClazzDao {
    /**
     * @description: 查询所有班级列表
     */
    List<Clazz> queryClazz();

    /**
     * @description: 通过clazzId查询指定班级信息
     */
    Clazz queryClazzById(long clazzId);

    /**
     * @description: 新增班级信息
     */
    int addClazz(Clazz clazz);

    /**
     * @description: 修改班级信息
     */
    int modifyClazz(Clazz aClazz);

    /**
     * @description: 通过clazzId删除指定班级
     */
    int deleteClazz(long clazzId);
}

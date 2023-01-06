package com.hlw.edu.service;

import com.hlw.edu.dto.ClazzExecution;
import com.hlw.edu.entity.Clazz;

import java.util.List;

public interface ClazzService {
    /**
     * @description: 查询全部班级信息
     */
    List<Clazz> getClazzList();
    
    /**
     * @description: 通过班级Id查询唯一的班级信息
     */
    Clazz getClazzById(long clazzId);

    /**
     * @description: 新增班级信息
     */
    ClazzExecution addClazz(Clazz clazz);

    /**
     * @description: 修改班级信息
     */
    ClazzExecution modifyClazz(Clazz clazz);

    /**
     * @description: 删除指定班级
     */
    ClazzExecution deleteClazz(long clazzId);
}

package com.hlw.edu.service.Impl;

import com.hlw.edu.dao.ClazzDao;
import com.hlw.edu.dto.ClazzExecution;
import com.hlw.edu.entity.Clazz;
import com.hlw.edu.enums.ClazzStateEnum;
import com.hlw.edu.exception.ClazzOperationException;
import com.hlw.edu.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: ClazzService实现类
 */
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzDao clazzDao;

    /**
     * @description: 查询班级列表
     */
    @Override
    public List<Clazz> getClazzList() {
        return clazzDao.queryClazz();
    }

    /**
     * @description: 通过班级Id查询唯一的班级信息
     */
    @Override
    public Clazz getClazzById(long clazzId) {
        return clazzDao.queryClazzById(clazzId);
    }

    /**
     * @description: 新增班级信息
     */
    @Override
    public ClazzExecution addClazz(Clazz clazz){
        //判断传入的班级信息是否为空
        if (clazz != null) {
            try {
                //设置创建时间
                clazz.setCreateTime(new Date());
                //添加班级信息
                int effectedNum = clazzDao.addClazz(clazz);
                //判断是否添加成功
                if (effectedNum <= 0) {
                    throw new ClazzOperationException("班级创建失败");
                } else {
                    return new ClazzExecution(ClazzStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ClazzOperationException("addClazz error: " + e.getMessage());
            }
        }else {
            return new ClazzExecution(ClazzStateEnum.EMPTY_LIST);
        }
    }

    /**
     * @description: 修改班级信息
     */
    @Override
    public ClazzExecution modifyClazz(Clazz clazz){
        //空值判断
        if (clazz != null && clazz.getClazzId() != null) {
            //设置更新时间
            clazz.setLastEditTime(new Date());
            try {
                //修改班级信息
                int effectedNum = clazzDao.modifyClazz(clazz);
                //判断是否修改成功
                if (effectedNum <= 0) {
                    throw new ClazzOperationException("更新班级信息失败");
                }
                return new ClazzExecution(ClazzStateEnum.SUCCESS, clazz);
            } catch (Exception e) {
                throw new ClazzOperationException("更新班级信息失败:" + e.toString());
            }
        } else {
            return new ClazzExecution(ClazzStateEnum.EMPTY);
        }
    }

    /**
     * @description: 删除指定班级
     */
    @Override
    public ClazzExecution deleteClazz(long clazzId){
        try {
            //删除该班级信息
            int effectedNum = clazzDao.deleteClazz(clazzId);
            //判断是否删除成功
            if (effectedNum <= 0) {
                throw new ClazzOperationException("班级信息删除失败");
            } else {
                return new ClazzExecution(ClazzStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ClazzOperationException("deleteClazz error:" + e.getMessage());
        }
    }
}

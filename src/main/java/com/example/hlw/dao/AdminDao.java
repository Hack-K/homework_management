package com.hlw.edu.dao;

import com.hlw.edu.entity.Admin;

/**
 * @description: 管理员实体类dao层接口
 */
public interface AdminDao {
    /**
     * @description: 通过adminId查询指定管理员信息
     */
    Admin queryAdminById(long adminId);

    /**
     * @description: 修改管理员信息
     */
    int modifyAdmin(Admin admin);
}

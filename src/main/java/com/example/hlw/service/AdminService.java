package com.hlw.edu.service;

import com.hlw.edu.dto.AdminExecution;
import com.hlw.edu.dto.ImageHolder;
import com.hlw.edu.entity.Admin;

public interface AdminService {
    /**
     * @description: 通过管理员ID获取指定管理员信息
     */
    Admin getAdminById(long adminId);

    /**
     * @description: 修改管理员信息
     */
    AdminExecution modifyAdmin(Admin admin, ImageHolder imageHolder);
}

package com.hlw.edu.service.Impl;

import com.hlw.edu.dao.AdminDao;
import com.hlw.edu.dto.AdminExecution;
import com.hlw.edu.dto.ImageHolder;
import com.hlw.edu.entity.Admin;
import com.hlw.edu.enums.AdminStateEnum;
import com.hlw.edu.exception.AdminOperationException;
import com.hlw.edu.service.AdminService;
import com.hlw.edu.util.ImageUtil;
import com.hlw.edu.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: AdminService实现类
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    /**
     * @description: 通过管理员ID获取指定管理员信息
     */
    @Override
    public Admin getAdminById(long adminId) {
        return adminDao.queryAdminById(adminId);
    }

    /**
     * @description: 修改管理员信息
     */
    @Override
    public AdminExecution modifyAdmin(Admin admin, ImageHolder imageHolder) {
        //空值判断
        if (admin == null || admin.getAdminId() == null){
            return new AdminExecution(AdminStateEnum.EMPTY);
        }
        try {
            //1.判断是否需要处理图片
            if (imageHolder != null){
                if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())){
                    Admin tempAdmin = adminDao.queryAdminById(admin.getAdminId());
                    //如果原路径下有图片存在，则删除原路径下的所有图片
                    if (tempAdmin.getProfileImg() != null){
                        ImageUtil.deleteFileOrPath(tempAdmin.getProfileImg());
                    }
                    //添加新的图片
                    addAdminImg(admin, imageHolder);
                }
            }

            //2.修改管理员信息
            //设置更新时间
            admin.setLastEditTime(new Date());
            //修改管理员信息
            int effectedNum = adminDao.modifyAdmin(admin);
            //判断是否修改成功
            if (effectedNum <= 0){
                return new AdminExecution(AdminStateEnum.INNER_ERROR);
            }else {
                admin = adminDao.queryAdminById(admin.getAdminId());
                return new AdminExecution(AdminStateEnum.SUCCESS,admin);
            }
        }catch (Exception e){
            throw new AdminOperationException("modifyAdminError:" + e.getMessage());
        }
    }

    private void addAdminImg(Admin admin, ImageHolder thumbnail) {
        //获取admin图片目录的相对值路径
        String dest = PathUtil.getAdminImagePath(admin.getAdminId());
        String adminImgAddr = ImageUtil.generateThumbnail(thumbnail,dest);
        admin.setProfileImg(adminImgAddr);
    }
}

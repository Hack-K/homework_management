package com.hlw.edu.web.admin;

import com.hlw.edu.dto.ClazzExecution;
import com.hlw.edu.entity.Clazz;
import com.hlw.edu.enums.ClazzStateEnum;
import com.hlw.edu.service.ClazzService;
import com.hlw.edu.util.HttpServletRequestUtil;
import com.hlw.edu.util.Layui;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 班级管理类-管理员界面
 */
@Controller
@RequestMapping("/admin")
public class ClazzManagementController {
    @Autowired
    private ClazzService clazzService;

    /**
     * @description: 列出所有班级列表-返回为Layui类型
     */
    @RequestMapping(value = "/listClazz",method = RequestMethod.GET)
    @ResponseBody
    private Layui listClazz(){
        //查询班级列表数据
        List<Clazz> clazzList = clazzService.getClazzList();
        return Layui.data(clazzList.size(),clazzList);
    }

    /**
     * @description: 列出所有班级列表-返回为Map类型
     */
    @RequestMapping(value = "/listClazzMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listClazzMap(){
        //查询班级列表数据
        Map<String,Object> modelMap = new HashMap<>();
        List<Clazz> clazzList = clazzService.getClazzList();
        modelMap.put("success",true);
        modelMap.put("clazzList",clazzList);
        return modelMap;
    }

    /**
     * @description: 根据ClazzId返回唯一的班级信息-返回为Map类型
     */
    @RequestMapping(value = "/listClazzById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listClazzById(@RequestParam("clazzId") Long clazzId){
        Clazz clazz = clazzService.getClazzById(clazzId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("clazz",clazz);
        return modelMap;
    }

    /**
     * @description: 添加班级信息
     */
    //获取前端ajax传递的字符串，解析字符串为相应的clazz实体，根据解析好的数据添加班级信息
    @RequestMapping(value = "/addClazz", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addClazz(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String clazzStr = HttpServletRequestUtil.getString(request,"clazzStr");
        if (clazzStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入班级信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Clazz clazz;
        try {
            clazz = mapper.readValue(clazzStr, Clazz.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        ClazzExecution clazzExecution = clazzService.addClazz(clazz);
        if (clazzExecution.getState() == ClazzStateEnum.SUCCESS.getState()){
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg",clazzExecution.getStateInfo());
        }
        return modelMap;
    }

    /**
     * @description: 修改班级信息
     */
    //获取前端ajax传递的字符串，解析字符串为相应的clazz实体，根据解析好的数据修改班级信息
    @RequestMapping(value = "/modifyClazz", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyClazz(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //1.接受并转化相应的参数，包括班级信息
        //获取前端传过来的班级信息，并将它转换成Clazz实体类；
        String clazzStr = HttpServletRequestUtil.getString(request,"clazzStr");
        ObjectMapper mapper = new ObjectMapper();
        Clazz clazz;
        try {
            clazz = mapper.readValue(clazzStr,Clazz.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //2.修改班级
        if(clazz != null && clazz.getClazzId() != null){
            ClazzExecution clazzExecution;
            clazzExecution = clazzService.modifyClazz(clazz);
            if (clazzExecution.getState() == ClazzStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",clazzExecution.getStateInfo());
            }
            return modelMap;
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入班级Id");
            return modelMap;
        }
        //3.返回结果
    }

    /**
     * @description: 删除班级信息
     */
    //根据前端路由路径中传递的clazzId值删除指定班级信息
    @RequestMapping(value = "/deleteClazz", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> deleteClazz(@RequestParam("clazzId") Long clazzId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (clazzId != null) {
            ClazzExecution clazzExecution = clazzService.deleteClazz(clazzId);
            if (clazzExecution.getState() == ClazzStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", clazzExecution.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "未选择要删除的班级");
        }
        return modelMap;
    }
}

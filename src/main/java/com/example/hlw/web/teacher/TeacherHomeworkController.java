package com.hlw.edu.web.teacher;

import com.hlw.edu.dto.FileHolder;
import com.hlw.edu.dto.HomeworkExecution;
import com.hlw.edu.entity.Homework;
import com.hlw.edu.entity.Student;
import com.hlw.edu.entity.StudentHomework;
import com.hlw.edu.enums.HomeworkStateEnum;
import com.hlw.edu.service.HomeworkService;
import com.hlw.edu.service.StudentHomeworkService;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 作业相关操作类-教师界面
 */
@Controller
@RequestMapping("/teacher")
public class TeacherHomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private StudentHomeworkService studentHomeworkService;

    /**
     * @description: 根据courseId返回作业列表-返回为Map类型
     */
    @RequestMapping(value = "/listHomeworkMapByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listHomeworkMapByCourseId(@RequestParam("courseId") Long courseId){
        Map<String,Object> modelMap = new HashMap<>();
        List<Homework> homeworkList = homeworkService.getHomeworkByCourseId(courseId);
        modelMap.put("success",true);
        modelMap.put("homeworkList",homeworkList);
        return modelMap;
    }

    /**
     * @description: 根据courseId返回作业列表-返回为Layui类型
     */
    @RequestMapping(value = "/listHomeworkByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listHomeworkByCourseId(@RequestParam("courseId") Long courseId){
        List<Homework> homeworkList = homeworkService.getHomeworkByCourseId(courseId);
        return Layui.data(homeworkList.size(),homeworkList);
    }

    /**
     * @description: 根据teacherId返回作业列表-返回为Layui类型
     */
    @RequestMapping(value = "/listHomeworkByTeacherId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listHomeworkByTeacherId(@RequestParam("teacherId") Long teacherId){
        List<Homework> homeworkList = homeworkService.getHomeworkByTeacherId(teacherId);
        return Layui.data(homeworkList.size(),homeworkList);
    }

    /**
     * @description: 列出所有学生作业列表-返回为Map类型
     */
    @RequestMapping(value = "/listStudentHomeworkMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listStudentHomeworkMap(@RequestParam("teacherId") Long teacherId){
        Map<String,Object> modelMap = new HashMap<>();
        List<StudentHomework> studentHomeworkList = studentHomeworkService.getStudentHomeworkByTeacherId(teacherId);
        modelMap.put("success",true);
        modelMap.put("studentHomeworkList",studentHomeworkList);
        return modelMap;
    }

    /**
     * @description: 根据courseId返回学生作业列表-返回为Layui类型
     */
    @RequestMapping(value = "/listStudentHomeworkByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listStudentHomeworkByCourseId(@RequestParam("courseId") Long courseId){
        List<StudentHomework> studentHomeworkList = studentHomeworkService.getStudentHomeworkByCourseId(courseId);
        return Layui.data(studentHomeworkList.size(),studentHomeworkList);
    }

    /**
     * @description: 根据courseId返回学生作业列表-返回为Map类型
     */
    @RequestMapping(value = "/listStudentHomeworkMapByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listStudentHomeworkMapByCourseId(@RequestParam("courseId") Long courseId){
        Map<String,Object> modelMap = new HashMap<>();
        List<StudentHomework> studentHomeworkList = studentHomeworkService.getStudentHomeworkByCourseId(courseId);
        modelMap.put("success",true);
        modelMap.put("studentHomeworkList",studentHomeworkList);
        return modelMap;
    }

    /**
     * @description: 根据studentHomeworkId返回唯一的学生作业信息-返回为Map类型
     */
    @RequestMapping(value = "/getStudentHomeworkById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getStudentHomeworkById(@RequestParam("studentHomeworkId") Long studentHomeworkId){
        Map<String,Object> modelMap = new HashMap<>();
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkById(studentHomeworkId);
        modelMap.put("success",true);
        modelMap.put("studentHomework",studentHomework);
        return modelMap;
    }

    /**
     * @description: 根据homeworkId和studentId返回学生作业列表-返回为Map类型
     */
    @RequestMapping(value = "/getStudentHomeworkByCondition",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getStudentHomeworkByCondition(@RequestParam("homeworkId") Long homeworkId,@RequestParam("studentId") Long studentId){
        Map<String,Object> modelMap = new HashMap<>();
        StudentHomework studentHomeworkCondition = new StudentHomework();
        Student student = new Student();
        Homework homework = new Homework();
        student.setStudentId(studentId);
        homework.setHomeworkId(homeworkId);
        studentHomeworkCondition.setStudent(student);
        studentHomeworkCondition.setHomework(homework);
        StudentHomework studentHomework = studentHomeworkService.getStudentHomeworkCondition(studentHomeworkCondition);

        if (studentHomework == null){
            modelMap.put("success",false);
            return modelMap;
        }
        modelMap.put("success",true);
        modelMap.put("studentHomework",studentHomework);
        return modelMap;
    }

    /**
     * @description: 添加作业信息
     */
    //获取前端ajax传递的字符串，解析字符串为相应的homework实体，根据解析好的数据添加作业信息
    @RequestMapping(value = "/addHomework",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addHomework(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        //1.接受并转化相应的参数
        //获取前端传过来的作业信息，并将它转换成实体类；
        //同时获取前端传递过来的文件流，将它接受到homeworkFile里面去
        String homeworkStr = HttpServletRequestUtil.getString(request,"homeworkStr");
        if (homeworkStr == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入作业信息");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Homework homework;
        try {
            homework = mapper.readValue(homeworkStr,Homework.class);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile homeworkFile;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        homeworkFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("homeworkFile");

        //2.添加作业信息
        HomeworkExecution homeworkExecution;
        try {
            FileHolder fileHolder = null;
            if (homeworkFile != null){
                fileHolder = new FileHolder(homeworkFile.getOriginalFilename(),homeworkFile.getInputStream());
            }
            homeworkExecution = homeworkService.addHomework(homework,fileHolder);
            if (homeworkExecution.getState() == HomeworkStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",homeworkExecution.getStateInfo());
            }
        } catch (IOException e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
        //3.返回结果
    }

    /**
     * @description: 修改作业信息
     */
    //获取前端ajax传递的字符串，解析字符串为相应的homework实体，根据解析好的数据修改作业信息
    @RequestMapping(value = "/modifyHomework",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyHomework(HttpServletRequest request) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        // 接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        Homework homework;

        // 若请求中存在文件流，则取出相关的文件
        CommonsMultipartFile homeworkFile = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(
                        request.getSession().getServletContext()
                );
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            homeworkFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("homeworkFile");
        }

        try {
            String homeworkStr = HttpServletRequestUtil.getString(request, "homeworkStr");
            // 尝试获取前端传过来的表单string流并将其转换成Student实体类
            homework = mapper.readValue(homeworkStr, Homework.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        try {
            FileHolder fileHolder = null;
            // 开始进行学生信息变更操作
            if (homeworkFile != null){
                fileHolder = new FileHolder(homeworkFile.getOriginalFilename(),homeworkFile.getInputStream());
            }
            HomeworkExecution homeworkExecution = homeworkService.modifyHomework(homework, fileHolder);
            if (homeworkExecution.getState() == HomeworkStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", homeworkExecution.getStateInfo());
            }
        } catch (RuntimeException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        return modelMap;
    }

    /**
     * @description: 删除作业信息
     */
    //根据前端路由路径中传递的homeworkId值删除指定作业信息
    @RequestMapping(value = "/deleteHomework",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> deleteHomework(@RequestParam("homeworkId") Long homeworkId){
        Map<String,Object> modelMap = new HashMap<>();
        homeworkService.deleteHomework(homeworkId);
        modelMap.put("success",true);
        return modelMap;
    }
}

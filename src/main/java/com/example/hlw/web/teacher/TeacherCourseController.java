package com.hlw.edu.web.teacher;

import com.hlw.edu.entity.Course;
import com.hlw.edu.entity.StudentCourse;
import com.hlw.edu.service.CourseService;
import com.hlw.edu.service.StudentCourseService;
import com.hlw.edu.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 课程相关操作类-教师界面
 */
@Controller
@RequestMapping("/teacher")
public class TeacherCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseService studentCourseService;

    /**
     * @description: 列出所有课程列表-返回为Map类型
     */
    @RequestMapping(value = "/listCourseMap",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listCourseMap(HttpServletRequest request){
        //查询课程列表数据
        Map<String,Object> modelMap = new HashMap<>();
        long teacherId = (long) request.getSession().getAttribute("teacherId");
        List<Course> courseList = courseService.getCourseByTeacherId(teacherId);
        modelMap.put("success",true);
        modelMap.put("courseList",courseList);
        return modelMap;
    }

    /**
     * @description: 根据courseId返回唯一的课程信息-返回为Map类型
     */
    @RequestMapping(value = "/getCourseById",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getCourseById(@RequestParam("courseId") Long courseId){
        Course course = courseService.getCourseById(courseId);
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("success",true);
        modelMap.put("course",course);
        return modelMap;
    }

    /**
     * @description: 根据courseId返回学生列表-返回为Layui类型
     */
    @RequestMapping(value = "/listStudentByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Layui listStudentByCourseId(@RequestParam("courseId") Long courseId){
        List<StudentCourse> studentCourseList = studentCourseService.getStudentByCourseId(courseId);
        return Layui.data(studentCourseList.size(),studentCourseList);
    }

    /**
     * @description: 根据courseId返回学生列表-返回为Map类型
     */
    @RequestMapping(value = "/listStudentMapByCourseId",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listStudentMapByCourseId(@RequestParam("courseId") Long courseId){
        Map<String,Object> modelMap = new HashMap<>();
        List<StudentCourse> studentCourseList = studentCourseService.getStudentByCourseId(courseId);
        modelMap.put("success",true);
        modelMap.put("studentCourseList",studentCourseList);
        return modelMap;
    }
}

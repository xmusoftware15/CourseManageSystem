package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.vo.SimpleCourseVo;

import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {

    @Autowired
    private CourseController courseController;

    @RequestMapping("/teacher")
    public String teacherInfo(){
        return "/teacher/basicInfo";
    }

    @RequestMapping("/teacher/course")
    public String teacherCourse() { return "/teacher/courseInfo";}

    @RequestMapping("/teacher/createCourse")
    public String teacherCreateCourse() { return "/teacher/createCourse";}

    @RequestMapping("/teacher/course/home")
    public String teacherCourseHome() { return "/teacher/course/homePage";}

    @RequestMapping("/teacher/course/createClass")
    public String teacherCourseCreateClass() { return "/teacher/course/createClass";}

    @RequestMapping("/teacher/course/createSeminar")
    public String teacherCourseCreateSeminar() { return "/teacher/course/createSeminar";}

    @RequestMapping("/teacher/course/seminarInfo")
    public String teacherCourseSeminarInfo() { return "/teacher/course/seminarInfo";}

    @RequestMapping("/teacher/course/grade")
    public String teacherCourseGrade() { return "/teacher/course/grade";}

    @RequestMapping("/teacher/course/classInfo")
    public String teacherCourseClassInfo() { return "/teacher/course/classInfo";}

    @RequestMapping("/teacher/course/topicInfo")
    public String teacherCourseTopicInfo() { return "/teacher/course/topicInfo";}

    @RequestMapping("/teacher/course/createTopic")
    public String teacherCourseCreateTopic() { return "/teacher/course/createTopic";}

    @RequestMapping("/teacher/course/viewChoose")
    public String teacherCourseViewChoose() { return "/teacher/course/viewChoose";}

}

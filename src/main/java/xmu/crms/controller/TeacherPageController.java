package xmu.crms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherPageController {

    @RequestMapping("/bind")
    public String bind() {
        return "teacher/TeacherBindPage";
    }

    @RequestMapping("/classInfo")
    public String classInfo() {
        return "teacher/TeacherClassInfo";
    }

    @RequestMapping("/courseHome")
    public String courseHome() {
        return "teacher/TeacherCourseHomePage";
    }

    @RequestMapping("/courseInformation")
    public String courseInformation() {
        return "teacher/TeacherCourseInformation";
    }

    @RequestMapping("/createClass")
    public String createClass() {
        return "teacher/TeacherCreateClass";
    }

    @RequestMapping("/createCourse")
    public String createCourse() {
        return "teacher/TeacherCreateCoursePage";
    }

    @RequestMapping("/createSchool")
    public String createSchool() {
        return "teacher/TeacherCreateSchool";
    }

    @RequestMapping("/createSeminar")
    public String createSeminar() {
        return "teacher/TeacherCreateSeminar";
    }

    @RequestMapping("/createTopic")
    public String createTopic() {
        return "teacher/TeacherCreateTopic";
    }

    @RequestMapping("/home")
    public String home() {
        return "teacher/TeacherHomePage";
    }

    @RequestMapping("/infoModify")
    public String infoModify() {
        return "teacher/TeacherInfoModifyPage";
    }

    @RequestMapping("/scoreHome")
    public String scoreHome() {
        return "teacher/TeacherScoreHome";
    }

    @RequestMapping("/scoreReport")
    public String scoreReport() {
        return "teacher/TeacherScoreReportPage";
    }

    @RequestMapping("/seminarInfo")
    public String seminarInfo() {
        System.out.println("lal");
        return "teacher/TeacherSeminarInfo";
    }

    @RequestMapping("/topicCheckAfter")
    public String topicCheckAfter() {
        return "teacher/TeacherTopicCheckAfter";
    }

    @RequestMapping("/topicCheckBefore")
    public String topicCheckBefore() {
        return "teacher/TeacherTopicCheckBefore";
    }

    @RequestMapping("/updateClass")
    public String updateClass() {
        return "teacher/TeacherUpdateClass";
    }

    @RequestMapping("/updateSeminar")
    public String updateSeminar() {
        return "teacher/TeacherUpdateSeminar";
    }

    @RequestMapping("/updateTopic")
    public String updateTopic() {
        return "teacher/TeacherUpdateTopic";
    }

    @RequestMapping("/updateCourse")
    public String updateCourse() {
        return "teacher/TeacherUpdateCourse";
    }

    @RequestMapping("/topicCheck")
    public String topicCheck() {
        return "teacher/TeacherTopicCheck";
    }
}

package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    private ClassController classController;

    @Autowired
    CourseController courseController;

    @RequestMapping("/student")
    public String studentInfo() {
        return "/student/basicInfo";
    }

    @RequestMapping("/student/course")
    public String studentCourse() {
        return "/student/courseInfo";
    }

    @RequestMapping("/student/choose")
    public String studentChoose(Model model) {
        return "/student/chooseCourse";
    }

    @RequestMapping("/student/course/home")
    public String studentCourseHome() {
        return "/student/course/homePage";
    }

    @RequestMapping("/student/course/seminarInfo")
    public String randomSeminar() {
        return "/student/course/seminarInfo";
    }

    @RequestMapping("/student/course/seminarInfo/fixed")
    public String fixedSeminar() {
        return "/student/course/seminarInfo";

    }

    @RequestMapping("/student/course/topicInfo")
    public String topicPage() {
        return "/student/course/topicInfo";
    }
}

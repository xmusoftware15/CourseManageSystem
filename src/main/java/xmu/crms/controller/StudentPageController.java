package xmu.crms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentPageController {

    @RequestMapping("/bind")
    public String bind() {
        return "student/StudentBindPage";
    }

    @RequestMapping("/chooseCourse")
    public String chooseCourse() {
        return "student/StudentChooseCoursePage";
    }

    @RequestMapping("/courseHome")
    public String courseHome() {
        return "student/StudentCourseHome";
    }

    @RequestMapping("/courseInformation")
    public String courseInformation() {
        return "student/StudentCourseInformation";
    }

    @RequestMapping("/discussionClass")
    public String discussionClass() {
        return "student/StudentDiscussionClassPage";
    }

    @RequestMapping("/home")
    public String home() {
        return "student/StudentHomePage";
    }

    @RequestMapping("/infoModify")
    public String infoModify() {
        return "student/StudentInfoModifyPage";
    }

    @RequestMapping("/modifyGroup")
    public String modifyGroup() {
        return "student/StudentModifyGroupPage";
    }

    @RequestMapping("/viewGrade")
    public String viewGrade() {
        return "student/StudentViewGradePage";
    }

    @RequestMapping("/viewGroup")
    public String viewGroup() {
        return "student/StudentViewGroupPage";
    }

    @RequestMapping("/viewTopic")
    public String viewTopic() {
        return "student/StudentViewTopicPage";
    }
}

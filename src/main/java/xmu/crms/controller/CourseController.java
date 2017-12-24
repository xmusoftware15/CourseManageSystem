package xmu.crms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;
import xmu.crms.vo.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author badcode
 * @date 2017/11/29
 */
@RestController
public class CourseController {

    @GetMapping("/course")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getCourses(HttpServletResponse response) {
//        List<Course> courses = new ArrayList<Course>();
//        Course course1 = new Course();
//        course1.setId(1L);
//        course1.setName("OOAD");
//        course1.setNumClass(3);
//        course1.setNumStudent(60);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2017,8,1);
//        course1.setStartTime(calendar.getTime());
//        calendar.set(2018,0,1);
//        course1.setEndTime(calendar.getTime());
//        courses.add(course1);
//
//        Course course2 = new Course();
//        course2.setId(2L);
//        course2.setName("J2EE");
//        course2.setNumClass(1);
//        course2.setNumStudent(60);
//        calendar.set(2017,8,1);
//        course2.setStartTime(calendar.getTime());
//        calendar.set(2018,0,1);
//        course2.setEndTime(calendar.getTime());
//        courses.add(course2);
//        response.setStatus(200);
        return null;
    }

    @PostMapping("/course")
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
//        System.out.println(course.toString());
//        Course  newCourse = new Course();
//        newCourse.setId(23L);
        return null;
    }

    @GetMapping("/course/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDetail getCourseInfo(@PathVariable("courseId") int courseId) {
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setId(23L);
        courseDetail.setName("OOAD");
        courseDetail.setDescription("面向对象分析与设计");
        courseDetail.setTeacherName("邱明");
        courseDetail.setTeacherEmail("mingqiu@xmu.edu.cn");
        return courseDetail;
    }

    @PutMapping("/course/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourse(@RequestBody Course course, @PathVariable("courseId") int courseId) {

    }

    @DeleteMapping("/course/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable("courseId") int courseId) {

    }

    @GetMapping("/course/{courseId}/class")
    @ResponseStatus(HttpStatus.OK)
    public List<Class> getClassesByCourse() {
//        List<Class> classes = new ArrayList();
//        Class class1 = new Class();
//        class1.setId(45L);
//        class1.setName("周三1-2节");
//        classes.add(class1);
//        Class class2 = new Class();
//        class2.setId(48L);
//        class2.setName("周三3-4节");
//        classes.add(class2);
        return null;
    }

    @PostMapping("/course/{courseId}/class")
    @ResponseStatus(HttpStatus.CREATED)
    public Class createClassForCourse(@RequestBody Class newClass, @PathVariable("courseId") int courseId) {
//        Class ret = new Class();
//        ret.setId(45L);
        return null;
    }

    @GetMapping("/course/{courseId}/seminar")
    @ResponseStatus(HttpStatus.OK)
    public List getSeminarsByCourse(@PathVariable("courseId") int CourseId,
                                    @PathVariable(value = "embedGrade", required = false) Boolean embedGrade) {
//        List seminars = new ArrayList<>();
//        Seminar seminar1 = new Seminar();
//        seminar1.setId(29L);
//        seminar1.setName("界面原型设计");
//        seminar1.setDescription("界面原型设计");
//        seminar1.setGroupingMethod("fixed");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2017,8,25);
//        seminar1.setStartTime(calendar.getTime());
//        calendar.set(2017,9,9);
//        seminar1.setEndTime(calendar.getTime());
//
//        Seminar seminar2 = new Seminar();
//        seminar2.setId(32L);
//        seminar2.setName("概要设计");
//        seminar2.setDescription("模型层与数据库设计");
//        seminar2.setGroupingMethod("random");
//        calendar.set(2017,11,10);
//        seminar2.setStartTime(calendar.getTime());
//        calendar.set(2017,11,24);
//        seminar2.setEndTime(calendar.getTime());
//
//        if(null != embedGrade && embedGrade == Boolean.TRUE) {
//            seminars.add(new SeminarWithGrade(seminar1, 4));
//            seminars.add(new SeminarWithGrade(seminar2, 5));
//        } else {
//            seminars.add(seminar1);
//            seminars.add(seminar2);
//        }
        return null;
    }

    @PostMapping("/course/{courseId}/seminar")
    @ResponseStatus(HttpStatus.CREATED)
    public Seminar createSeminarForCourse(@RequestBody Seminar seminar) {
//        Seminar newSeminar = new Seminar();
//        newSeminar.setId(32L);
        return null;
    }

    @GetMapping("/course/{courseId}/seminar/current")
    @ResponseStatus(HttpStatus.OK)
    public SeminarClasses getCurrentSeminar(@PathVariable("courseId") int courseId) {
//        SeminarClasses seminarClasses = new SeminarClasses();
//        seminarClasses.setId(29L);
//        seminarClasses.setName("界面原型设计");
//        seminarClasses.setCourseName("OOAD");
//        seminarClasses.setGroupingMethond("fixed");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2017,8,25);
//        seminarClasses.setStartTime(calendar.getTime());
//        calendar.set(2017,9,9);
//        seminarClasses.setEndTime(calendar.getTime());
//        List<Class> classes = new ArrayList<>();
//        Class class1 = new Class();
//        class1.setId(53L);
//        class1.setName("周三12");
//        classes.add(class1);
//        Class class2 = new Class();
//        class2.setId(57L);
//        class2.setName("周三34");
//        classes.add(class2);
//        seminarClasses.setClasses(classes);
        return null;
    }

    @GetMapping("/course/{cousrId}/grade")
    @ResponseStatus(HttpStatus.OK)
    public List<SeminarGradeDetail> getAllSeminarGrade(@PathVariable("courseId") int courseId) {
        List<SeminarGradeDetail> seminarGradeDetails = new ArrayList<>();
        SeminarGradeDetail seminarGradeDetail = new SeminarGradeDetail();
        seminarGradeDetail.setGrade(4);
        seminarGradeDetail.setGroupName("3A2");
        seminarGradeDetail.setSeminarName("需求分析");
        seminarGradeDetail.setLeaderName("张三");
        seminarGradeDetail.setPresentationGrade(3);
        seminarGradeDetail.setReportGrade(4);
        seminarGradeDetail.setGrade(4);
        seminarGradeDetails.add(seminarGradeDetail);

        SeminarGradeDetail seminarGradeDetail1 = new SeminarGradeDetail();
        seminarGradeDetail1.setGrade(4);
        seminarGradeDetail1.setGroupName("3A3");
        seminarGradeDetail1.setSeminarName("界面原型设计");
        seminarGradeDetail1.setLeaderName("张三");
        seminarGradeDetail1.setPresentationGrade(4);
        seminarGradeDetail1.setReportGrade(4);
        seminarGradeDetail1.setGrade(4);
        seminarGradeDetails.add(seminarGradeDetail1);

        return seminarGradeDetails;
    }
}

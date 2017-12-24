package xmu.crms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.User;
import xmu.crms.vo.CourseClassVO;
import xmu.crms.vo.Response;
import xmu.crms.vo.SelectClassVO;


import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author badcode
 * @date 2017/11/29
 */

@RestController
public class ClassController {

    @GetMapping("/class")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseClassVO> getClassList() {
        List<CourseClassVO> courseClasses = new ArrayList<>();
        CourseClassVO courseClass1 = new CourseClassVO();
        courseClass1.setId(23L);
        courseClass1.setName("周三1-2节");
        courseClass1.setNumStudent(60);
        courseClass1.setTime("周三1-2、周五1-2");
        courseClass1.setSite("公寓405");
        courseClass1.setCourseName("OOAD");
        courseClass1.setCourseTeacher("邱明");

        CourseClassVO courseClass2 = new CourseClassVO();
        courseClass2.setId(42L);
        courseClass2.setName("一班");
        courseClass2.setNumStudent(60);
        courseClass2.setTime("周三34节 周五12节");
        courseClass2.setSite("海韵202");
        courseClass2.setCourseName(".Net 平台开发");
        courseClass2.setCourseTeacher("杨律青");

        courseClasses.add(courseClass1);
        courseClasses.add(courseClass2);

        return courseClasses;
    }

    @GetMapping("/class/{classId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Class getClass(@PathVariable("classId") int id) {
//        Class class1 = new Class();
//        class1.setId(23L);
//        class1.setName("周三1-2节");
//        class1.setNumStudent(120);
//        class1.setTime("周三一二节");
//        class1.setSite("海韵201");
//        class1.setCalling(-1);
//        class1.setRoster("/roster/周三12班.xlsx");
//        Proportions proportions = new Proportions();
//        proportions.setA(20);
//        proportions.setB(60);
//        proportions.setC(20);
//        proportions.setReport(50);
//        proportions.setPresentation(50);
//        class1.setProportions(proportions);
        return null;
    }

    @PutMapping("/class/{classId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClass(@PathVariable("classId") int classId,
                            @RequestBody Class newClass) {
    }

    @DeleteMapping("/class/{classId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable("classId") int classId) {

    }

    @GetMapping("/class/{classId}/student")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getStudentList(@PathVariable int classId) {
//        List<User> students = new ArrayList();
//        User student1 = new User();
//        student1.setId(233L);
//        student1.setName("张三");
//        student1.setNumber("24320152202333");
//        students.add(student1);
//        User student2 = new User();
//        student2.setId(245L);
//        student2.setName("张八");
//        student2.setNumber("24320152202334");
//        students.add(student2);
        return null;
    }

    @PostMapping("/class/{classId}/student")
    @ResponseStatus(HttpStatus.CREATED)
    public SelectClassVO chooseClass(@RequestBody User student, @PathVariable("classId") int classId) {
        SelectClassVO ret = new SelectClassVO();
        ret.setUrl("/class/34/student/2757");
        return ret;
    }

    @DeleteMapping("/class/{classId}/student/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelClass(@PathVariable("classId") int classId,
                            @PathVariable("studentId") int studentId) {
    }

    @GetMapping("/class/{classId}/classgroup")
    @ResponseStatus(HttpStatus.OK)
    public SeminarGroup getClassGroup(@PathVariable("classId") int classId) {
//        ClassGroup classGroup = new ClassGroup();
//        User leader = new User();
//        leader.setId(2757L);
//        leader.setName("张三");
//        leader.setNumber("23320152202333");
//        classGroup.setLeader(leader);
//        List<User> members = new ArrayList<>();
//        User member1 = new User();
//        member1.setId(2756L);
//        member1.setName("李四");
//        member1.setNumber("23320152202443");
//        User member2 = new User();
//        member2.setId(2777L);
//        member2.setName("王五");
//        member2.setNumber("23320152202433");
//        members.add(member1);
//        members.add(member2);
//        classGroup.setMembers(members);
        return null;
    }

    @PutMapping("/class/{classId}/classgroup/resign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resignLeader(@PathVariable("classId") int classId,
                                 @RequestBody User student) {

    }

    @PutMapping("/class/{classId}/classgroup/assign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignLeader(@PathVariable("classId") int classId,
                                 @RequestBody User student) {

    }

    @PutMapping("/class/{classId}/classgroup/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMember(@PathVariable("classId") int classId,
                              @RequestBody User student) {

    }

    @PutMapping("/class/{classId}/classgroup/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember(@PathVariable("classId") int classId,
                                 @RequestBody User student) {

    }
}

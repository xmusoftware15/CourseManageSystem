package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.service.ClassService;
import xmu.crms.service.CourseService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.SeminarService;
import xmu.crms.vo.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author heqi
 * @date 2017/12/24
 */
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassService classService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private SeminarGroupService seminarGroupService;

    @GetMapping("/course")
    @ResponseStatus(HttpStatus.OK)
    public Object getCourses(@RequestAttribute("userId") BigInteger userId, @RequestAttribute("type") Integer type) throws CourseNotFoundException, ClassesNotFoundException {
        System.out.println(userId.toString() + type);
        if (type.equals(0)) {
            List<ClassInfo> classes = classService.listClassByUserId(userId);
//            List<ClassVo> classVos = new ArrayList<>();
//            for (ClassInfo classInfo : classes) {
//                ClassVo classVo = new ClassVo();
//                classVo.setId(classInfo.getId());
//                classVo.setName(classInfo.getName());
//                classVo.setSite(classInfo.getSite());
//                classVo.setTime(classInfo.getClassTime());
//                classVos.add(classVo);
//            }
            return classes;
        } else {
            List<Course> courses = courseService.listCourseByUserId(userId);
            List<CourseVohuhui> list=new ArrayList<CourseVohuhui>();
            for(Course c:courses){
                CourseVohuhui courseVohuhui=new CourseVohuhui();
                courseVohuhui.setDescription(c.getDescription());
                courseVohuhui.setEndDate(c.getEndDate());
                courseVohuhui.setFourPointPercentage(c.getFourPointPercentage());
                courseVohuhui.setFivePointPercentage(c.getFivePointPercentage());
                courseVohuhui.setId(c.getId());
                courseVohuhui.setName(c.getName());
                courseVohuhui.setPresentationPercentage(c.getPresentationPercentage());
                courseVohuhui.setReportPercentage(c.getReportPercentage());
                courseVohuhui.setStartDate(c.getStartDate());
                courseVohuhui.setTeacher(c.getTeacher());
                courseVohuhui.setThreePointPercentage(c.getThreePointPercentage());
                CurrentSeminarVO currentSeminarVO=this.getCurrentSeminar(c.getId().toString());
                if(currentSeminarVO==null){
                courseVohuhui.setFlag(false);}
                else{courseVohuhui.setFlag(true);}
                list.add(courseVohuhui);
            }

            return list;
        }
    }

    @PostMapping("/course")
    @ResponseStatus(HttpStatus.CREATED)
    public IdVo createCourse(@RequestAttribute("userId") String userId, @RequestBody CourseVo courseVo) {
        Course course = new Course();
        course.setName(courseVo.getName());
        course.setStartDate(courseVo.getStartTime());
        course.setEndDate(courseVo.getEndTime());
        course.setPresentationPercentage(courseVo.getProportions().getPresentation());
        course.setReportPercentage(courseVo.getProportions().getReport());
        course.setFivePointPercentage(courseVo.getProportions().getA());
        course.setFourPointPercentage(courseVo.getProportions().getB());
        course.setThreePointPercentage(courseVo.getProportions().getC());
        BigInteger courseId = courseService.insertCourseByUserId(new BigInteger(userId), course);
        IdVo idVo;
        idVo = new IdVo();
        idVo.setId(courseId);
        return idVo;
    }

    @GetMapping("/course/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDetail getCourseInfo(@PathVariable("courseId") String courseId) throws CourseNotFoundException {
        Course course = courseService.getCourseByCourseId(new BigInteger(courseId));
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setId(new BigInteger(courseId));
        courseDetail.setName(course.getName());
        courseDetail.setDescription(course.getDescription());
        courseDetail.setTeacherName(course.getTeacher().getName());
        courseDetail.setTeacherEmail(course.getTeacher().getEmail());
        return courseDetail;
    }

    @PutMapping("/course/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourse(@PathVariable("courseId") String courseId, @RequestBody CourseVo courseVo) {
        Course course = new Course();
        course.setName(courseVo.getName());
        course.setDescription(courseVo.getDescription());
        course.setStartDate(courseVo.getStartTime());
        course.setEndDate(courseVo.getEndTime());
        course.setPresentationPercentage(courseVo.getProportions().getPresentation());
        course.setReportPercentage(courseVo.getProportions().getReport());
        course.setFivePointPercentage(courseVo.getProportions().getA());
        course.setFourPointPercentage(courseVo.getProportions().getB());
        course.setThreePointPercentage(courseVo.getProportions().getC());
        courseService.updateCourseByCourseId(new BigInteger(courseId), course);
    }

    @DeleteMapping("/course/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable("courseId") String courseId) {
        System.out.print(courseId);
        courseService.deleteCourseByCourseId(new BigInteger(courseId));
    }

    @GetMapping("/course/{courseId}/class")
    @ResponseStatus(HttpStatus.OK)
    public List<SimpleClassInfoVo> getClassesByCourse(@PathVariable("courseId") String courseId) throws CourseNotFoundException {
        SimpleClassInfoVo classInfoVo;
        List<SimpleClassInfoVo> classInfoVos = new ArrayList<SimpleClassInfoVo>();
        List<ClassInfo> classInfos = classService.listClassByCourseId(new BigInteger(courseId));
        for (ClassInfo classInfo : classInfos) {
            classInfoVo = new SimpleClassInfoVo();
            classInfoVo.setId(classInfo.getId());
            classInfoVo.setName(classInfo.getName());
            classInfoVos.add(classInfoVo);
        }
        return classInfoVos;
    }

    @PostMapping("/course/{courseId}/class")
    @ResponseStatus(HttpStatus.CREATED)
    public IdVo createClassForCourse(@RequestBody ClassVo classVo, @PathVariable("courseId") String courseId)
            throws CourseNotFoundException {

        ClassInfo classInfo = new ClassInfo();
        Course course = new Course();
        course.setId(new BigInteger(courseId));
        classInfo.setCourse(course);
        classInfo.setName(classVo.getName());
        classInfo.setSite(classVo.getSite());
        classInfo.setClassTime(classVo.getTime());
        classInfo.setPresentationPercentage(classVo.getProportions().getPresentation());
        classInfo.setReportPercentage(classVo.getProportions().getReport());
        classInfo.setFivePointPercentage(classVo.getProportions().getA());
        classInfo.setFourPointPercentage(classVo.getProportions().getB());
        classInfo.setThreePointPercentage(classVo.getProportions().getC());
        classService.insertClassById(new BigInteger(courseId), classInfo);
        IdVo idVo;
        idVo = new IdVo();
        idVo.setId(classInfo.getId());
        return idVo;
    }

    @GetMapping("/course/{courseId}/seminar")
    @ResponseStatus(HttpStatus.OK)
    public List getSeminarsByCourse(@PathVariable("courseId") String courseId,
                                    @RequestParam(value = "embedGrade", required = false) Boolean embedGrade,
                                    @RequestAttribute("userId") String userId) throws CourseNotFoundException,
            GroupNotFoundException {
        List<SeminarWithGradeVO> seminarWithGradeVOS = new ArrayList<>();
        List<Seminar> seminars = seminarService.listSeminarByCourseId(new BigInteger(courseId));
        for (Seminar seminar : seminars) {
            SeminarWithGradeVO seminarWithGradeVO = new SeminarWithGradeVO(seminar);
            if (embedGrade != null) {
                SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(seminar.getId(), new BigInteger(userId));
                if (seminarGroup != null) {
                    seminarWithGradeVO.setGrade(seminarGroup.getFinalGrade());
                }
            }
            seminarWithGradeVOS.add(seminarWithGradeVO);
        }
        return seminarWithGradeVOS;
    }

    @PostMapping("/course/{courseId}/seminar")
    @ResponseStatus(HttpStatus.CREATED)
    public IdVo createSeminarForCourse(@PathVariable("courseId") String courseId, @RequestBody HqSeminarVo seminarVo) throws CourseNotFoundException {
        String fixed = "fixed";
        Seminar seminar = new Seminar();
        Course course = new Course();
        course.setId(new BigInteger(courseId));
        seminar.setCourse(course);
        seminar.setName(seminarVo.getName());
        if(fixed.equals(seminarVo.getGroupingMethod())){
            seminar.setFixed(true);
        }
        else{
            seminar.setFixed(false);
        }
        seminar.setDescription(seminarVo.getDescription());
        seminar.setStartTime(seminarVo.getStartTime());
        seminar.setEndTime(seminarVo.getEndTime());
        seminarService.insertSeminarByCourseId(new BigInteger(courseId), seminar);
        IdVo idVo;
        idVo = new IdVo();
        idVo.setId(seminar.getId());
        return idVo;
    }

    @GetMapping("/course/{courseId}/seminar/current")
    @ResponseStatus(HttpStatus.OK)
    public CurrentSeminarVO getCurrentSeminar(@PathVariable("courseId") String courseId) throws CourseNotFoundException {
        List<Seminar> seminars = seminarService.listSeminarByCourseId(new BigInteger(courseId));
        Calendar calendar = Calendar.getInstance();
        Date current = calendar.getTime();
        for (Seminar seminar : seminars) {
            if (current.after(seminar.getStartTime()) && current.before(seminar.getEndTime())) {
                CurrentSeminarVO currentSeminarVO = new CurrentSeminarVO(seminar);
                currentSeminarVO.setClasses(classService.listClassByCourseId(new BigInteger(courseId)));
//                System.out.println(courseId);
//                System.out.println(classService.listClassByCourseId(new BigInteger(courseId)));
//                System.out.println(currentSeminarVO.getClasses());
                return currentSeminarVO;
            }
        }
        return null;
    }

    @GetMapping("/course/{courseId}/grade")
    @ResponseStatus(HttpStatus.OK)
    public List<SeminarGradeDetail> getAllSeminarGrade(@PathVariable("courseId") String courseId,
                                                       @RequestAttribute("userId") String userId) throws CourseNotFoundException, UserNotFoundException, SeminarNotFoundException, GroupNotFoundException {
        List<SeminarGradeDetail> seminarGradeDetails = new ArrayList<>();
        List<Seminar> seminars = seminarService.listSeminarByCourseId(new BigInteger(courseId));
        for (Seminar seminar : seminars) {
            SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(seminar.getId(), new BigInteger(userId));
            seminarGradeDetails.add(new SeminarGradeDetail(seminarGroup));
        }
        return seminarGradeDetails;
    }
}

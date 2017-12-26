package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
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
    public List<SimpleCourseVo> getCourses(@RequestAttribute("userId") String userId) throws CourseNotFoundException{
        List<Course> courses = courseService.listCourseByUserId(new BigInteger(userId));
        List<SimpleCourseVo> simpleCourseVos = new ArrayList<SimpleCourseVo>();
        for(Course course:courses){
            SimpleCourseVo simpleCourseVo = new SimpleCourseVo();
            simpleCourseVo.setId(course.getId());
            simpleCourseVo.setName(course.getName());
            simpleCourseVo.setNumClass(3);
            simpleCourseVo.setNumStudent(60);
            simpleCourseVo.setStartTime(course.getStartDate());
            simpleCourseVo.setEndTime(course.getEndDate());
            simpleCourseVos.add(simpleCourseVo);
        }
        return simpleCourseVos;
    }

    @PostMapping("/course")
    @ResponseStatus(HttpStatus.CREATED)
    public IdVo createCourse(@RequestAttribute("userId") String userId,@RequestBody CourseVo courseVo) {
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
    public CourseDetail getCourseInfo(@PathVariable("courseId") String courseId) throws CourseNotFoundException{
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
    public void updateCourse(@PathVariable("courseId") String courseId,@RequestBody CourseVo courseVo) {
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
        courseService.updateCourseByCourseId(new BigInteger(courseId),course);
    }

    @DeleteMapping("/course/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable("courseId") String courseId) {
        courseService.deleteCourseByCourseId(new BigInteger(courseId));
    }

    @GetMapping("/course/{courseId}/class")
    @ResponseStatus(HttpStatus.OK)
    public List<SimpleClassInfoVo> getClassesByCourse(@PathVariable("courseId") String courseId) throws  CourseNotFoundException{
        SimpleClassInfoVo classInfoVo;
        List<SimpleClassInfoVo> classInfoVos = new ArrayList<SimpleClassInfoVo>();
        List<ClassInfo> classInfos = classService.listClassByCourseId(new BigInteger(courseId));
        for(ClassInfo classInfo:classInfos){
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
        classService.insertClassById(new BigInteger(courseId),classInfo);
        IdVo idVo;
        idVo = new IdVo();
        idVo.setId(new BigInteger(courseId));
        return idVo;
    }

    @GetMapping("/course/{courseId}/seminar")
    @ResponseStatus(HttpStatus.OK)
    public List getSeminarsByCourse(@PathVariable("courseId") String courseId,
                                    @RequestParam(value = "embedGrade", required = false) Boolean embedGrade,
                                    @RequestAttribute("userId") String userId) throws CourseNotFoundException,
            GroupNotFoundException{
        List<SeminarWithGradeVO> seminarWithGradeVOS = new ArrayList<>();
        List<Seminar> seminars = seminarService.listSeminarByCourseId(new BigInteger(courseId));
        for (Seminar seminar : seminars) {
            SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(seminar.getId(), new BigInteger(userId));
            SeminarWithGradeVO seminarWithGradeVO = new SeminarWithGradeVO(seminar);
            seminarWithGradeVO.setGrade(seminarGroup.getFinalGrade());
            seminarWithGradeVOS.add(seminarWithGradeVO);
        }
        return seminarWithGradeVOS;
    }

    @PostMapping("/course/{courseId}/seminar")
    @ResponseStatus(HttpStatus.CREATED)
    public IdVo createSeminarForCourse(@PathVariable("courseId") String courseId,@RequestBody HQSeminarVo seminarVo) throws CourseNotFoundException {
        Seminar seminar = new Seminar();
        Course course = new Course();
        course.setId(new BigInteger(courseId));
        seminar.setCourse(course);
        seminar.setName(seminarVo.getName());
        seminar.setDescription(seminarVo.getDescription());
        seminar.setStartTime(seminarVo.getStartTime());
        seminar.setEndTime(seminarVo.getEndTime());
        seminarService.insertSeminarByCourseId(new BigInteger(courseId),seminar);
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
                currentSeminarVO.setClasses(courseService.listClassByCourseName(seminar.getCourse().getName()));
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

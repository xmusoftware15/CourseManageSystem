package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.service.*;
import xmu.crms.vo.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author badcode
 * @date 2017/11/29
 */
@RestController
@RequestMapping("/seminar")
public class SeminarController {

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SeminarGroupService seminarGroupService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassService classService;

    @GetMapping("/{seminarId}")
    @ResponseStatus(HttpStatus.OK)
    public Seminar getSeminarInfo(@PathVariable("seminarId") String seminarId) throws SeminarNotFoundException {
        Seminar seminar = seminarService.getSeminarBySeminarId(new BigInteger(seminarId));
        return seminar;
    }

    @PutMapping("/{seminarId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifySeminar(@PathVariable("seminarId") String seminarId, @RequestBody Seminar seminar)
            throws SeminarNotFoundException {
        seminarService.updateSeminarBySeminarId(new BigInteger(seminarId), seminar);
    }

    @DeleteMapping("/{seminarId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeminar(@PathVariable("seminarId") String seminarId) throws SeminarNotFoundException {
        seminarService.deleteSeminarBySeminarId(new BigInteger(seminarId));
    }

    @GetMapping("/{seminarId}/my")
    @ResponseStatus(HttpStatus.OK)
    public StudentSeminar getRelatedSeminar(@PathVariable("seminarId") String seminarId)
            throws SeminarNotFoundException, CourseNotFoundException, ClassesNotFoundException, GroupNotFoundException {

        StudentSeminar studentSeminar = new StudentSeminar();
        Seminar seminar = seminarService.getSeminarBySeminarId(new BigInteger(seminarId));
        studentSeminar.setId(seminar.getId().longValue());
        studentSeminar.setName(seminar.getName());
        if (seminar.getFixed() == true) {
            studentSeminar.setGroupingMethond("fixed");
        } else {
            studentSeminar.setGroupingMethond("random");
        }

        Course course = courseService.getCourseByCourseId(seminar.getCourse().getId());
        studentSeminar.setCourseName(course.getName());

        studentSeminar.setStartTime(seminar.getStartTime());
        studentSeminar.setEndTime(seminar.getEndTime());

        //todo
        BigInteger userId = new BigInteger("1");

        BigInteger classId = new BigInteger("-1");
        List<ClassInfo> classInfos = classService.listClassByUserId(userId);
        for (ClassInfo classInfo : classInfos
                ) {
            if (classInfo.getCourse().getId().equals(course.getId())) {
                classId = classInfo.getId();
                studentSeminar.setClassCalling(classId.intValue());
                break;
            }
        }

        BigInteger leaderId = seminarGroupService.getSeminarGroupLeaderById(userId, new BigInteger(seminarId));
        if (userId.equals(leaderId)) {
            studentSeminar.setLeader(true);
        } else {
            studentSeminar.setLeader(false);
        }
        List<Topic> topics = topicService.listTopicBySeminarId(new BigInteger(seminarId));
        SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(new BigInteger(seminarId), userId);
        BigInteger grouopId = seminarGroup.getId();
        Boolean areTopicsSelected = false;
        for (Topic topic : topics
                ) {
            SeminarGroupTopic seminarGroupTopic = topicService.getSeminarGroupTopicById(topic.getId(), grouopId);
            if (seminarGroupTopic != null) {
                areTopicsSelected = true;
                break;
            }
        }
        studentSeminar.setAreTopicsSelected(areTopicsSelected);

        return studentSeminar;
    }

    @GetMapping("/{seminarId}/detail")
    @ResponseStatus(HttpStatus.OK)
    public SeminarDetail getSeminarDetail(@PathVariable("seminarId") String seminarId)
            throws SeminarNotFoundException, CourseNotFoundException,
            ClassNotFoundException, ClassesNotFoundException, UserNotFoundException {
        SeminarDetail seminarDetail = new SeminarDetail();

        //todo
        BigInteger userId = new BigInteger("8");

        Seminar seminar = seminarService.getSeminarBySeminarId(new BigInteger(seminarId));
        seminarDetail.setId(seminar.getId().longValue());
        seminarDetail.setName(seminar.getName());
        seminarDetail.setStartTime(seminar.getStartTime());
        seminarDetail.setEndTime(seminar.getEndTime());

        Course course = courseService.getCourseByCourseId(seminar.getCourse().getId());
        List<ClassInfo> classInfos = classService.listClassByUserId(userId);
        ClassInfo classInfo = new ClassInfo();
        for (ClassInfo classInfo1 : classInfos
                ) {
            if (classInfo1.getCourse().getId().equals(course.getId())) {
                classInfo = classInfo1;
                break;
            }
        }
        if (classInfo == null) {
            throw new ClassesNotFoundException();
        } else {
            seminarDetail.setSite(classInfo.getSite());
        }

        User teacher = userService.getUserByUserId(course.getTeacher().getId());
        seminarDetail.setTeacherName(teacher.getName());
        seminarDetail.setTeacherEmail(teacher.getEmail());

        return seminarDetail;
    }

    @GetMapping("/{seminarId}/topic")
    @ResponseStatus(HttpStatus.OK)
    public List<Topic> getTopics(@PathVariable("seminarId") String seminarId) throws IllegalArgumentException {
        return topicService.listTopicBySeminarId(new BigInteger(seminarId));
    }

    @PostMapping("/{seminarId}/topic")
    @ResponseStatus(HttpStatus.CREATED)
    public Topic addTopic(@PathVariable("seminarId") String seminarId, @RequestBody Topic topic) {
        BigInteger id = topicService.insertTopicBySeminarId(new BigInteger(seminarId), topic);
        Topic newTopic = new Topic();
        newTopic.setId(id);
        return newTopic;
    }

    @GetMapping("/{seminarId}/group")
    @ResponseStatus(HttpStatus.OK)
    public List<SeminarGroupVO> getGroups(@PathVariable("seminarId") String seminarId)
            throws SeminarNotFoundException, CourseNotFoundException {

        List<SeminarGroupVO> seminarGroupVOS = new ArrayList<>();

        List<SeminarGroup> seminarGroups = seminarGroupService.listSeminarGroupBySeminarId(new BigInteger(seminarId));
        List<Topic> topics = topicService.listTopicBySeminarId(new BigInteger(seminarId));
        List<ClassInfo> classInfos = classService.listClassByCourseId(seminarGroups.get(0).getSeminar().getId());
        for (ClassInfo classInfo : classInfos
                ) {
            for (Topic topic : topics
                    ) {
                for (SeminarGroup seminarGroup : seminarGroups
                        ) {
                    SeminarGroupTopic isChosen = topicService.getSeminarGroupTopicById(topic.getId(), seminarGroup.getId());
                    if (isChosen != null) {
                        SeminarGroupVO seminarGroupVO = new SeminarGroupVO();
                        seminarGroupVO.setId(isChosen.getId().longValue());
                        seminarGroupVO.setName(classInfos.indexOf(classInfo) + topics.indexOf(topic) + seminarGroups.indexOf(seminarGroup) + "");
                        seminarGroupVO.setTopicId(topic.getId().longValue());
                        seminarGroupVO.setTopicName(topic.getName());
                        seminarGroupVOS.add(seminarGroupVO);
                    }
                }
            }
        }

        return seminarGroupVOS;
    }

    @GetMapping("/{seminarId}/group/my")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getMyGroup(@PathVariable("seminarId") String seminarId) throws GroupNotFoundException {
        List<User> seminarGroupMembers;

        //todo
        BigInteger userId = new BigInteger("8");
        SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(new BigInteger(seminarId), userId);
        seminarGroupMembers = seminarGroupService.listSeminarGroupMemberByGroupId(seminarGroup.getId());
        return seminarGroupMembers;
    }

    @GetMapping("/{seminarId}/class/{classId}/attendance")
    @ResponseStatus(HttpStatus.OK)
    public AttendanceStatus getAttendanceStatus(@PathVariable("seminarId") String seminarId,
                                                @PathVariable("classId") String classId)
            throws ClassesNotFoundException,SeminarNotFoundException,UserNotFoundException {
        AttendanceStatus attendanceStatus = new AttendanceStatus();

        List<Attendance> attendances=userService.listAttendanceById(new BigInteger(classId),new BigInteger(seminarId));
        attendanceStatus.setNumPresent(attendances.size());

        List<User> students=userService.listUserByClassId(new BigInteger(classId),"","");
        attendanceStatus.setNumStudent(students.size());

        Location location=classService.getCallStatusById(new BigInteger(classId),new BigInteger(seminarId));
        if(location.getStatus()==0){
            attendanceStatus.setStatus("签到结束");
            attendanceStatus.setGroup("已分组");
        }
        else{
            attendanceStatus.setStatus("正在签到");
            attendanceStatus.setGroup("未完成分组");
        }

        return attendanceStatus;
    }

    @GetMapping("seminar/{seminarId}/class/{classId}/attendance/present")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getPresent(@PathVariable("seminarId") String seminarId,
                                 @PathVariable("classId") String classId)
            throws ClassesNotFoundException,SeminarNotFoundException,UserNotFoundException {
        List<User> present = new ArrayList<>();

        List<Attendance> attendances=userService.listAttendanceById(new BigInteger(classId),new BigInteger(seminarId));
        for (Attendance attendance:attendances
             ) {
            User user=userService.getUserByUserId(attendance.getStudent().getId());
            present.add(user);
        }

        return present;
    }

    @GetMapping("/{seminarId}/class/{classId}/attendance/late")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getLate(@PathVariable("seminarId") String seminarId,
                              @PathVariable("classId") String classId)
            throws ClassesNotFoundException,SeminarNotFoundException {
        List<User> late = userService.listLateStudent(new BigInteger(seminarId),new BigInteger(classId));
        return late;
    }

    @GetMapping("/{seminarId}/class/{classId}/attendance/absent")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAbsent(@PathVariable("seminarId") String seminarId,
                                @PathVariable("classId") String classId)
            throws ClassesNotFoundException,SeminarNotFoundException {
        List<User> absent = userService.listAbsenceStudent(new BigInteger(seminarId),new BigInteger(classId));
        return absent;
    }

    @PutMapping("/{seminarId}/class/{classId}/attendance/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Status signIn(@PathVariable("seminarId") String seminarId,
                         @PathVariable("classId") String classId,
                         @PathVariable("studentId") String studentId,
                         @RequestBody SiteVO site)
            throws SeminarNotFoundException,ClassesNotFoundException,UserNotFoundException {
        Status status = new Status();

        Location location=classService.getCallStatusById(new BigInteger(classId),new BigInteger(seminarId));
        userService.insertAttendanceById(new BigInteger(classId),new BigInteger(seminarId),new BigInteger(studentId)
                ,site.getLongitude(),site.getLatitude());
        if(location.getStatus()==1){
            status.setStatus("ontime准点");
        }
        else{
            status.setStatus("late迟到");
        }

        return status;
    }
}

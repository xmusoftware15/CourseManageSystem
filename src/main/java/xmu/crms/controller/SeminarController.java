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
    public SeminarVO getSeminarInfo(@PathVariable("seminarId") String seminarId) throws SeminarNotFoundException {
        SeminarVO seminarVO=new SeminarVO();
        Seminar seminar = seminarService.getSeminarBySeminarId(new BigInteger(seminarId));
        seminarVO.setId(seminar.getId().longValue());
        seminarVO.setName(seminar.getName());
        seminarVO.setDescription(seminar.getDescription());
        seminarVO.setGroupingMethod(seminar.getFixed()?"fixed":"random");

        List<TopicBasicVO> topicBasicVOS=new ArrayList<>();
        List<Topic> topics=topicService.listTopicBySeminarId(new BigInteger(seminarId));
        for (Topic topic:topics
                ) {
            TopicBasicVO topicBasicVO=new TopicBasicVO();
            topicBasicVO.setId(topic.getId().longValue());
            topicBasicVO.setName(topic.getName());
            topicBasicVOS.add(topicBasicVO);
        }
        seminarVO.setTopics(topicBasicVOS);

        return seminarVO;
    }

    @PutMapping("/{seminarId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifySeminar(@PathVariable("seminarId") String seminarId, @RequestBody SeminarProportionsVO seminarVO)
            throws SeminarNotFoundException {
        Seminar seminar=new Seminar();
        seminar.setId(new BigInteger(seminarId));
        seminar.setName(seminarVO.getName());
        seminar.setDescription(seminarVO.getDescription());
        seminar.setFixed(seminarVO.getGroupingMethod()=="fixed"?true:false);
        seminar.setStartTime(seminarVO.getStartTime());
        seminar.setEndTime(seminarVO.getEndTime());
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
        BigInteger userId = new BigInteger("5");

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
            studentSeminar.setIsLeader("true");
        } else {
            studentSeminar.setIsLeader("false");
        }
        List<Topic> topics = topicService.listTopicBySeminarId(new BigInteger(seminarId));
        SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(new BigInteger(seminarId), userId);
        if(seminarGroup!=null) {
            BigInteger groupId = seminarGroup.getId();
            List<SeminarGroupTopic> seminarGroupTopics = topicService.listSeminarGroupTopicByGroupId(groupId);
            if (seminarGroupTopics != null) {
                studentSeminar.setAreTopicsSelected("true");
            } else {
                studentSeminar.setAreTopicsSelected("false");
            }
        }
        else{
            studentSeminar.setAreTopicsSelected("false");
        }
        return studentSeminar;
    }

    @GetMapping("/{seminarId}/detail")
    @ResponseStatus(HttpStatus.OK)
    public SeminarDetail getSeminarDetail(@PathVariable("seminarId") String seminarId)
            throws SeminarNotFoundException, CourseNotFoundException, ClassesNotFoundException, UserNotFoundException {
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
    public List<TopicVO> getTopics(@PathVariable("seminarId") String seminarId) throws IllegalArgumentException {
        List<TopicVO> topicVOS=new ArrayList<>();
        List<Topic> topics=topicService.listTopicBySeminarId(new BigInteger(seminarId));

        for (Topic topic:topics) {
            List<SeminarGroup> groups=seminarGroupService.listGroupByTopicId(topic.getId());
            TopicVO topicVO=new TopicVO();
            topicVO.setGroupLeft(topic.getGroupNumberLimit()-groups.size());
            topicVO.setId(topic.getId());
            topicVO.setSerial(topic.getSerial());
            topicVO.setName(topic.getName());
            topicVO.setDescription(topic.getDescription());
            topicVO.setGroupLimit(topic.getGroupNumberLimit());
            topicVO.setGroupMemberLimit(topic.getGroupStudentLimit());

            topicVOS.add(topicVO);
        }
        return topicVOS;
    }

    @PostMapping("/{seminarId}/topic")
    @ResponseStatus(HttpStatus.CREATED)
    public Topic addTopic(@PathVariable("seminarId") String seminarId, @RequestBody TopicVO topicVO) {
        Topic topic=new Topic();
        topic.setSerial(topicVO.getSerial());
        topic.setName(topicVO.getSerial());
        topic.setDescription(topicVO.getDescription());
        topic.setGroupNumberLimit(topicVO.getGroupLimit());
        topic.setGroupStudentLimit(topicVO.getGroupLimit());
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
                        TopicBasicVO topicBasicVO=new TopicBasicVO();
                        topicBasicVO.setId(topic.getId().longValue());
                        topicBasicVO.setName(topic.getName());
                        seminarGroupVO.setTopics(topicBasicVO);
                        seminarGroupVOS.add(seminarGroupVO);
                    }
                }
            }
        }

        return seminarGroupVOS;
    }

    @GetMapping("/{seminarId}/class/{classId}/group")
    @ResponseStatus(HttpStatus.OK)
    public List<SeminarGroupVO> getGroups(@RequestAttribute("userId") BigInteger userId,@PathVariable("seminarId") String seminarId,@PathVariable("classId") String classId)
            throws SeminarNotFoundException, CourseNotFoundException,GroupNotFoundException {

        List<SeminarGroupVO> seminarGroupVOS = new ArrayList<>();
        List<SeminarGroup> seminarGroups = seminarGroupService.listSeminarGroupById(new BigInteger(seminarId),new BigInteger(classId));
        List<Topic> topics = topicService.listTopicBySeminarId(new BigInteger(seminarId));
        SeminarGroup seminarGroup=seminarGroupService.getSeminarGroupById(new BigInteger(seminarId),userId);
        List<SeminarGroupTopic> mytopics=topicService.listSeminarGroupTopicByGroupId(seminarGroup.getId());
        for(SeminarGroupTopic s:mytopics){
            if(topics.contains(s.getTopic())){topics.remove(s.getTopic());}
        }
        for(Topic t:topics){
            List<SeminarGroup> seminarGroups1=seminarGroupService.listGroupByTopicId(t.getId());
            for(SeminarGroup s:seminarGroups1){
                SeminarGroupVO seminarGroupVO=new SeminarGroupVO();
                seminarGroupVO.setId(s.getId().longValue());
                TopicBasicVO topicBasicVO=new TopicBasicVO();
                topicBasicVO.setId(t.getId().longValue());
                topicBasicVO.setName(t.getName());
                seminarGroupVO.setTopics(topicBasicVO);
                seminarGroupVOS.add(seminarGroupVO);
            }
        }
        return seminarGroupVOS;
    }

    @GetMapping("/{seminarId}/group/my")
    @ResponseStatus(HttpStatus.OK)
    public SeminarGroupDetail getMyGroup(@RequestAttribute("userId")BigInteger userId,@PathVariable("seminarId") String seminarId)
            throws GroupNotFoundException,UserNotFoundException {
        SeminarGroupDetail seminarGroupDetail=new SeminarGroupDetail();
        //todo
        SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(new BigInteger(seminarId), userId);
        seminarGroupDetail.setId(seminarGroup.getId());
        seminarGroupDetail.setName(seminarGroup.getId().toString());
        BigInteger leaderId=seminarGroupService.getSeminarGroupLeaderByGroupId(seminarGroup.getId());
        User leader;
        if(leaderId!=null) {
             leader = userService.getUserByUserId(leaderId);

            UserIdNameVO leaderVO = new UserIdNameVO(leader);
            seminarGroupDetail.setLeader(leaderVO);
        }
        else{leader=null;}
        List<UserIdNameVO> members=new ArrayList<>();
        List<User> seminarGroupMembers;
        seminarGroupMembers = seminarGroupService.listSeminarGroupMemberByGroupId(seminarGroup.getId());
        for (User user:seminarGroupMembers
             ) {
            UserIdNameVO userIdNameVO=new UserIdNameVO(user);

            if(leaderId!=null&&user.getId().equals(leader.getId())){

            }
            else{
            members.add(userIdNameVO);}
        }
        seminarGroupDetail.setMembers(members);
        List<SeminarGroupTopic> seminarGroupTopics=topicService.listSeminarGroupTopicByGroupId(seminarGroup.getId());
        List<Topic> topics=new ArrayList<>();
        for(SeminarGroupTopic s:seminarGroupTopics){
            topics.add(s.getTopic());
        }
seminarGroupDetail.setTopics(topics);
        return seminarGroupDetail;
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

    @GetMapping("/{seminarId}/class/{classId}/attendance/present")
    @ResponseStatus(HttpStatus.OK)
    public List<UserIdNameVO> getPresent(@PathVariable("seminarId") String seminarId,
                                 @PathVariable("classId") String classId)
            throws ClassesNotFoundException,SeminarNotFoundException {
        List<UserIdNameVO> userIdNameVOS=new ArrayList<>();
        List<User> presents;

        presents=userService.listPresentStudent(new BigInteger(seminarId),new BigInteger(classId));
        for (User present:presents
             ) {
            userIdNameVOS.add(new UserIdNameVO(present));
        }

        return userIdNameVOS;
    }

    @GetMapping("/{seminarId}/class/{classId}/attendance/late")
    @ResponseStatus(HttpStatus.OK)
    public List<UserIdNameVO> getLate(@PathVariable("seminarId") String seminarId,
                              @PathVariable("classId") String classId)
            throws ClassesNotFoundException,SeminarNotFoundException {
        List<UserIdNameVO> lateStudents=new ArrayList<>();
        List<User> lates = userService.listLateStudent(new BigInteger(seminarId),new BigInteger(classId));
        for (User late:lates
             ) {
            lateStudents.add(new UserIdNameVO(late));
        }
        return lateStudents;
    }

    @GetMapping("/{seminarId}/class/{classId}/attendance/absent")
    @ResponseStatus(HttpStatus.OK)
    public List<UserIdNameVO> getAbsent(@PathVariable("seminarId") String seminarId,
                                @PathVariable("classId") String classId)
            throws ClassesNotFoundException,SeminarNotFoundException {
        List<UserIdNameVO> userIdNameVOS=new ArrayList<>();
        List<User> absents = userService.listAbsenceStudent(new BigInteger(seminarId),new BigInteger(classId));
        for (User absent:absents
             ) {
            userIdNameVOS.add(new UserIdNameVO(absent));
        }
        return userIdNameVOS;
    }

    @PutMapping("/{seminarId}/class/{classId}/attendance/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public Status signIn(@PathVariable("seminarId") String seminarId,
                         @PathVariable("classId") String classId,
                         @PathVariable("studentId") String studentId,
                         @RequestBody SiteVO site)
            throws SeminarNotFoundException,ClassesNotFoundException,UserNotFoundException {
        Status status = new Status();

        Location location=classService.getCallStatusById(new BigInteger(classId),new BigInteger(seminarId));
        System.out.println(site);
        userService.insertAttendanceById(new BigInteger(classId),new BigInteger(seminarId),new BigInteger(studentId)
                ,site.getLongitude(),site.getLatitude());
        if(location.getStatus()==1){
            status.setStatus("ontime");
        } else {
            status.setStatus("late");
        }

        return status;
    }
}

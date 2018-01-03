package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupMember;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.mapper.ClassMapper;
import xmu.crms.service.ClassService;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.vo.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author badcode
 * @date 2017/11/29
 */

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;
    @Autowired
    private ClassMapper classMapper;
@Autowired
private SeminarGroupService seminarGroupService;
    @Autowired
    private FixGroupService fixGroupService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseClassVO> getClassList(@RequestAttribute("userId") BigInteger userId, @RequestParam(value = "courseName", required = false)String courseName, @RequestParam(value = "courseTeacher", required = false)String courseTeacher)
            throws ClassesNotFoundException, UserNotFoundException, CourseNotFoundException {
        List <ClassInfo> list=classService.listClassByUserId(userId);
        List<CourseClassVO> courseClassVOS=new ArrayList<CourseClassVO>();
        for(ClassInfo c:list){
            CourseClassVO d=new CourseClassVO();
            d.setCourseName(c.getCourse().getName());
            d.setCourseTeacher(c.getCourse().getTeacher().getName());
            d.setId(c.getId());
            d.setName(c.getName());
            d.setSite(c.getSite());
            d.setTime(c.getCourse().getStartDate().toString());
            d.setCourseId(c.getCourse().getId());
            courseClassVOS.add(d);
        }
//        List<CourseClassVO> courseClasses = new ArrayList<>();
//        List<ClassInfo> classInfos = classService.listClassByName(courseName, courseTeacher);
//        for (ClassInfo classInfo : classInfos) {
//            CourseClassVO courseClassVO = new CourseClassVO(classInfo);
//            courseClasses.add(courseClassVO);
//        }

        return courseClassVOS;
    }

    @GetMapping("/{classId}")
    @ResponseStatus(HttpStatus.OK)
    public ClassInfoVO getClass(@PathVariable("classId") String id)
            throws ClassesNotFoundException {
        ClassInfo classInfo = classService.getClassByClassId(new BigInteger(id));
        ClassInfoVO classInfoVO = new ClassInfoVO(classInfo);
        return classInfoVO;
    }

    @PutMapping("/{classId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClass(@PathVariable("classId") String classId,
                            @RequestBody ClassInfoVO newClass) throws ClassesNotFoundException {
        ClassInfo classInfo = newClass.transferToClassInfo();
        classService.updateClassByClassId(new BigInteger(classId), classInfo);
    }

    @PutMapping("/{classId}/seminar/{seminarId}/location")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLocation(@PathVariable("classId") BigInteger classId,
                               @PathVariable("seminarId") BigInteger seminarId,@RequestParam(value="status",required = false) BigInteger status) throws ClassesNotFoundException
    ,GroupNotFoundException,UserNotFoundException,SeminarNotFoundException,InvalidOperationException{

        classMapper.updateLocationById(classId,seminarId,status);
        if(status.equals(new BigInteger("0"))){
        seminarGroupService.automaticallyGrouping(seminarId,classId);}
    }

    @DeleteMapping("/{classId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable("classId") String classId) throws ClassesNotFoundException {
        classService.deleteClassByClassId(new BigInteger(classId));
    }

    @GetMapping("/{classId}/student")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentVO> getStudentList(@PathVariable String classId) {
        return null;
    }

    @PostMapping("/{classId}/student")
    @ResponseStatus(HttpStatus.CREATED)
    public UrlVO chooseClass(@RequestBody User student,
                             @PathVariable("classId") String classId) throws UserNotFoundException, ClassesNotFoundException {
        BigInteger id = classService.insertCourseSelectionById(student.getId(), new BigInteger(classId));
        UrlVO urlVO = new UrlVO();
        urlVO.setUrl("/class/" + classId + "/student/" + id.toString());
        return urlVO;
    }

    @DeleteMapping("/{classId}/student/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelClass(@PathVariable("classId") String classId,
                            @PathVariable("studentId") String studentId) throws UserNotFoundException, ClassesNotFoundException {
        classService.deleteCourseSelectionById(new BigInteger(studentId), new BigInteger(classId));
    }

    @GetMapping("/{classId}/classgroup")
    @ResponseStatus(HttpStatus.OK)
    public GroupMemberVO getClassGroup(@PathVariable("classId") String classId,
                                       @RequestAttribute("userId") String userId) throws UserNotFoundException, ClassesNotFoundException, FixGroupNotFoundException {
        FixGroup fixGroup = fixGroupService.getFixedGroupById(new BigInteger(userId), new BigInteger(classId));
        GroupMemberVO groupMemberVO = new GroupMemberVO();
        groupMemberVO.setLeader(new StudentVO(fixGroup.getLeader()));
        List<StudentVO> members = new ArrayList<>();
        List<FixGroupMember> fixGroupMembers = fixGroupService.listFixGroupByGroupId(fixGroup.getId());
        for (FixGroupMember fixGroupMember : fixGroupMembers) {
            if (fixGroupMember.getId().compareTo(fixGroup.getLeader().getId()) != 0) {
                members.add(new StudentVO(fixGroupMember.getStudent()));
            }
        }
        groupMemberVO.setMembers(members);
        return groupMemberVO;
    }

    @PutMapping("/{classId}/classgroup/resign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resignLeader(@PathVariable("classId") int classId,
                             @RequestBody User student) {
    }

    @PutMapping("/{classId}/classgroup/assign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignLeader(@PathVariable("classId") int classId,
                             @RequestBody User student) {
    }

    @PutMapping("/{classId}/classgroup/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMember(@PathVariable("classId") String classId,
                          @RequestBody User student, @RequestAttribute("userId") String userId) throws UserNotFoundException,
            InvalidOperationException, FixGroupNotFoundException, ClassesNotFoundException {
        FixGroup fixGroup = fixGroupService.getFixedGroupById(new BigInteger(userId), new BigInteger(classId));
        if (fixGroup.getLeader().getId().toString().equals(userId)) {
            throw new InvalidOperationException("权限不足");
        }
        fixGroupService.insertStudentIntoGroup(student.getId(), fixGroup.getId());
    }

    @PutMapping("/{classId}/classgroup/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember(@PathVariable("classId") String classId,
                             @RequestBody User student) throws UserNotFoundException, ClassesNotFoundException, FixGroupNotFoundException {
        FixGroup fixGroup = fixGroupService.getFixedGroupById(student.getId(), new BigInteger(classId));
        fixGroupService.deleteFixGroupUserById(fixGroup.getId(), student.getId());
    }
}

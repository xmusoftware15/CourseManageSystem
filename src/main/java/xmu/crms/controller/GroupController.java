package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.Topic;
import xmu.crms.entity.User;
import xmu.crms.exception.FixGroupNotFoundException;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.InvalidOperationException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.*;
import xmu.crms.vo.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * @author badcode
 * @date 2017/11/30
 */
@RestController
@RequestMapping("/group")
public class GroupController {
@Autowired
private GradeService gradeService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private SeminarGroupService seminarGroupService;
    @Autowired
    private UserService userService;
    @Autowired
    private FixGroupService fixGroupService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupVO getGroupInfo(@PathVariable("id") BigInteger id) throws GroupNotFoundException,UserNotFoundException,FixGroupNotFoundException {
        GroupVO groupVO = new GroupVO();

        //队长
        Member leader = new Member();

        //选题
        List<TopicHxr> topics = new ArrayList<>();
        List<Member> members = new ArrayList<>();

        //获得所有成员
        List<User> users = fixGroupService.listFixGroupMemberByGroupId(id);
        Member member = new Member();
        for (int i = 0; i <users.size() ; i++) {
            member.setId(users.get(i).getId());
            member.setName(users.get(i).getName());
            members.add(member);
        }

        //获得所有选题
        List<SeminarGroupTopic> listTopics= topicService.listSeminarGroupTopicByGroupId(id);
        TopicHxr topic = new TopicHxr();
        for (int i = 0; i <listTopics.size() ; i++) {
            topic.setId(listTopics.get(i).getId());
            topic.setName(listTopics.get(i).getTopic().getName());
            topics.add(topic);
        }

        SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupByGroupId(id);
        BigInteger leaderId = seminarGroupService.getSeminarGroupLeaderByGroupId(id);
        User user = userService.getUserByUserId(leaderId);

        leader.setId(user.getId());
        leader.setName(user.getName());

        groupVO.setId(seminarGroup.getId());
        groupVO.setLeader(leader);
        groupVO.setReport(seminarGroup.getReport());
        groupVO.setTopics(topics);
        groupVO.setMembers(members);

        return groupVO;
    }


    @PutMapping("/{groupId}/resign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void leaderResign(@PathVariable("groupId") BigInteger groupId,
                             @RequestAttribute("userId") BigInteger leaderId) throws GroupNotFoundException,
            UserNotFoundException,
            InvalidOperationException
    {

        seminarGroupService.resignLeaderById(groupId,leaderId);
    }


    @PutMapping("/{groupId}/assign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void leaderAssign(@PathVariable("groupId") BigInteger groupId,
                             @RequestAttribute("userId") BigInteger leaderId) throws UserNotFoundException,GroupNotFoundException,InvalidOperationException {
seminarGroupService.assignLeaderById(groupId,leaderId);
    }

    @PutMapping("/{groupId}/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember(@PathVariable("groupId") BigInteger groupId,
                             @RequestBody User student) {
         seminarGroupService.deleteSeminarGroupMemberById(groupId,student.getId());
    }

    @PutMapping("/{groupId}/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMember(@PathVariable("groupId") BigInteger groupId,
                          @RequestBody User student) throws GroupNotFoundException,UserNotFoundException,InvalidOperationException {
          seminarGroupService.insertSeminarGroupMemberById(student.getId(),groupId);
    }

    @PostMapping("/{groupId}/topic")
    @ResponseStatus(HttpStatus.CREATED)
    public SelectTopicVO chooseTopic(@PathVariable("groupId") BigInteger groupId, @RequestBody Topic topic) throws GroupNotFoundException {
        seminarGroupService.insertTopicByGroupId(groupId,topic.getId());
        String url="group/"+groupId+"topic/"+topic.getId();
        SelectTopicVO ret=new SelectTopicVO();
        ret.setUrl(url);
        return ret;
    }

    @DeleteMapping("/{groupId}/topic/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelTopicSelection(@PathVariable("groupId") String groupId,
                                          @PathVariable("topicId") String topicId) {
        topicService.deleteSeminarGroupTopicById(new BigInteger(groupId), new BigInteger(topicId));
    }

    @GetMapping("/{groupId}/grade")
    @ResponseStatus(HttpStatus.OK)
    public SeminarGroup getGrade(@PathVariable("groupId") BigInteger groupId) throws GroupNotFoundException {
        SeminarGroup seminarGroup=seminarGroupService.getSeminarGroupByGroupId(groupId);
        return seminarGroup;
    }

    @PutMapping("/{groupId}/grade/report")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setGrade(@RequestBody SeminarGradeDetail seminarGradeDetail, @PathVariable("groupId") BigInteger groupId) throws GroupNotFoundException,IllegalArgumentException{
        gradeService.updateGroupByGroupId(groupId,seminarGradeDetail.getReportGrade());
    }

    @PutMapping("/{groupId}/grade/presentation/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void studentSetGrade(@RequestBody GradeVO grade, @PathVariable("groupId") BigInteger groupId, @PathVariable("studentId") BigInteger studentId) throws IllegalArgumentException {

        for(PresentationGrade p:grade.getList()){
        gradeService.insertGroupGradeByUserId(p.getTopicId(),studentId,groupId,p.getGrade());
    }}
}

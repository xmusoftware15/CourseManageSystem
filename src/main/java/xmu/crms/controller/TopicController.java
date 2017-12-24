package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.Topic;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.TopicService;
import xmu.crms.vo.GroupOfTopicVO;
import xmu.crms.vo.TopicVO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/01
 *
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private SeminarGroupService seminarGroupService;

    @GetMapping("/{topicId}")
    @ResponseStatus(HttpStatus.OK)
    public TopicVO getTopic(@PathVariable("topicId") BigInteger topicId) throws TopicNotFoundException, IllegalArgumentException {
        Topic topic = topicService.getTopicByTopicId(topicId);
        return new TopicVO(topic);
    }

    @PutMapping("/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyTopic(@PathVariable("topicId") BigInteger topicId, @RequestBody TopicVO topicVO) throws TopicNotFoundException, IllegalArgumentException {
        Topic topic = topicVO.transferToTopic();
        topicService.updateTopicByTopicId(topicId, topic);
    }

    @DeleteMapping("/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable("topicId") String topicId) throws TopicNotFoundException {
        topicService.deleteTopicByTopicId(new BigInteger(topicId));
    }

    @GetMapping("/{topicId}/group")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupOfTopicVO> getGroups(@PathVariable("topicId") String topicId) throws GroupNotFoundException{
        List<GroupOfTopicVO> groupOfTopicVOs = new ArrayList<>();
        List<SeminarGroup> seminarGroups = seminarGroupService.listGroupByTopicId(new BigInteger(topicId));
        for (SeminarGroup seminarGroup : seminarGroups) {
            groupOfTopicVOs.add(new GroupOfTopicVO(seminarGroup));
        }
        return groupOfTopicVOs;
    }
}

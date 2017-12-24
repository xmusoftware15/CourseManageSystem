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
    public Topic getTopic(@PathVariable("topicId") String topicId) throws TopicNotFoundException, IllegalArgumentException {
        BigInteger id = new BigInteger(topicId);
        return topicService.getTopicByTopicId(id);
    }

    @PutMapping("/{topicId}")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyTopic(@PathVariable("topicId") String topicId, @RequestBody Topic topic) throws TopicNotFoundException, IllegalArgumentException {
        BigInteger id = new BigInteger(topicId);
        topicService.updateTopicByTopicId(id, topic);
    }

    @DeleteMapping("/{topicId}")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable("topicId") String topicId) {
        topicService.deleteTopicByTopicId(new BigInteger(topicId));
    }

    @GetMapping("/{topicId}/group")
    @ResponseStatus(HttpStatus.OK)
    public List<SeminarGroup> getGroups(@PathVariable("topicId") String topicId) throws GroupNotFoundException{
        return seminarGroupService.listGroupByTopicId(new BigInteger(topicId));
    }
}

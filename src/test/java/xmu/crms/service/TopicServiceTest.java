package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.Topic;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicServiceTest {

    @Autowired
    private TopicService topicService;

    @Test
    public void testDeleteTopicBySeminarId() {
        topicService.deleteTopicBySeminarId(new BigInteger("1"));
    }

    @Test
    public void testGetSeminarGroupTopicById() {
        System.out.println(topicService.getSeminarGroupTopicById(BigInteger.valueOf(1L), BigInteger.valueOf(1L)));
    }
}

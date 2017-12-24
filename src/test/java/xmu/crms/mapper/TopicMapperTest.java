package xmu.crms.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.Topic;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicMapperTest {

    @Autowired
    private TopicMapper topicMapper;

    @Test
    public void testGetTopicByTopicId() {
        Topic topic = new Topic();
        topic.setId(BigInteger.valueOf(1L));
        System.out.println(topicMapper.getTopicByTopicId(topic));
    }

    @Test
    public void testInsertTopicBySeminarId() {
        Topic topic = new Topic();
        topic.setName("话题9");
        topic.setDescription("话题说明9");
        topic.setGroupNumberLimit(5);
        topic.setGroupStudentLimit(5);
        Seminar seminar = new Seminar();
        seminar.setId(BigInteger.valueOf(1));
        topic.setSeminar(seminar);
        Integer ret = topicMapper.insertTopicBySeminarId(topic);
        if (ret > 0) {
            //说明插入成功，通过getId获取新纪录自增id
            System.out.println(topic.getId());
        }
    }
}

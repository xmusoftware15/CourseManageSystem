package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.Topic;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.mapper.TopicMapper;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/19
 *
 */
@Component
public class TopicDAO {

    @Autowired
    TopicMapper topicMapper;

    public Topic getTopicByTopicId(Topic topic) throws TopicNotFoundException {
        Topic result = topicMapper.getTopicByTopicId(topic);
        if (null == result) {
            throw new TopicNotFoundException();
        }
        return result;
    }

    public Boolean updateTopicByTopicId(Topic topic) throws TopicNotFoundException {
        Integer res = topicMapper.updateTopicByTopicId(topic);
        if (res > 0) {
            return true;
        } else {
            throw new TopicNotFoundException();
        }
    }

    public Boolean deleteTopicByTopicId(Topic topic) {
        Integer res = topicMapper.deleteTopicByTopicId(topic);
        return res > 0;
    }

    public List<Topic> listTopicBySeminarId(Seminar seminar) {
        return topicMapper.listTopicBySeminarId(seminar);
    }

    public BigInteger insertTopicBySeminarId(Topic topic) {
        if (topicMapper.insertTopicBySeminarId(topic) > 0) {
            return topic.getId();
        } else {
            return new BigInteger("-1");
        }
    }
}

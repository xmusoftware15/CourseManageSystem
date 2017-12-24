package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.Topic;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/18
 *
 */
@Mapper
@Component
public interface TopicMapper {

    /**
     *
     * get topic by topic id
     *
     * @param topic 存储了topicId
     * @return Topic
     *
     */
    Topic getTopicByTopicId(Topic topic);

    /**
     *
     * update topic by topicId
     *
     * @param topic 话题信息
     * @return Integer 影响行数
     *
     */
    Integer updateTopicByTopicId(Topic topic);

    /**
     *
     * 根据id删除topic
     *
     * @param topic 存储话题id
     * @return Integer 影响行数
     *
     */
    Integer deleteTopicByTopicId(Topic topic);

    /**
     *
     * 找到某节seminar下的所有topic
     *
     * @param seminar 讨论课
     * @return List<Topic> topic列表
     *
     */
    List<Topic> listTopicBySeminarId(Seminar seminar);

    /**
     *
     * 在某个seminar下创建topic
     *
     * @param topic topic信息
     * @return Integer 插入记录的条数
     *
     */
    Integer insertTopicBySeminarId(Topic topic);
}

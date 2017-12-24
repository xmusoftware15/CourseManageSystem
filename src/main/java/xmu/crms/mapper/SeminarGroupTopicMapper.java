package xmu.crms.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.Topic;

import java.math.BigInteger;

@Component
public interface SeminarGroupTopicMapper {
   Integer insertTopicByGroupId(@Param("seminarGroupTopicId") BigInteger seminarGroupTopicId, @Param("groupId") BigInteger groupId,@Param("topicId") BigInteger topicId);
    /**
     *
     * 根据seminar和topic获取SeminarGroupTopic
     *
     * @param seminarGroupTopic 存放了seminar和topic信息
     * @return SeminarGroupTopic
     */
    SeminarGroupTopic getBySeminarAndTopic(SeminarGroupTopic seminarGroupTopic);

    /**
     *
     * 根据id删除记录
     *
     * @param seminarGroupTopic
     * @return
     */
    int deleteById(SeminarGroupTopic seminarGroupTopic);

    /**
     *
     * 根据groupId和topicId删除记录
     *
     * @param seminarGroupTopic 存储了group和topic信息
     * @return 删除操作影响行数
     *
     */
    Integer deleteByGroupAndTopic(SeminarGroupTopic seminarGroupTopic);

    /**
     *
     * 根据topicId删除记录
     *
     * @param topic 存储了topic信息
     * @return 删除影响行数
     *
     */
    Integer deleteByTopic(Topic topic);

    /**
     *
     * 根据Id查找记录
     *
     * @param seminarGroupTopic 存储了Id
     * @return 查询结果
     *
     */
    SeminarGroupTopic getById(SeminarGroupTopic seminarGroupTopic);
}

package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.Topic;
import xmu.crms.mapper.SeminarGroupTopicMapper;

/**
 *
 * @author badcode
 * @date 2017/12/19
 *
 */
@Component
public class SeminarGroupTopicDAO {

    @Autowired
    private SeminarGroupTopicMapper seminarGroupTopicMapper;

    public Boolean deleteByGroupAndTopic(SeminarGroupTopic seminarGroupTopic) {
        int result = seminarGroupTopicMapper.deleteByGroupAndTopic(seminarGroupTopic);
        return result > 0;
    }

    public Boolean deleteByTopic(Topic topic) {
        int result = seminarGroupTopicMapper.deleteByTopic(topic);
        return result > 0;
    }

    public SeminarGroupTopic getSeminarGroupTopicById(SeminarGroupTopic seminarGroupTopic) {
        return seminarGroupTopicMapper.getBySeminarAndTopic(seminarGroupTopic);
    }
}

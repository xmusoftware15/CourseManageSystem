package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupTopic;
import xmu.crms.mapper.FixGroupTopicMapper;

import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/23
 *
 */
@Component
public class FixGroupTopicDAO {

    @Autowired
    private FixGroupTopicMapper fixGroupTopicMapper;

    public List<FixGroupTopic> listFixGroupTopicByFixGroup(FixGroup fixGroup) {
        return fixGroupTopicMapper.listFixGroupTopicByFixGroup(fixGroup);
    }

    public void deleteFixGroupTopicById(FixGroupTopic fixGroupTopic) {
        fixGroupTopicMapper.deleteFixGroupTopicById(fixGroupTopic);
    }
}

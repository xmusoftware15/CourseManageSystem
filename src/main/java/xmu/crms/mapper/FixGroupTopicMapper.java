package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupTopic;

import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/23
 *
 */
@Mapper
@Component
public interface FixGroupTopicMapper {

    /**
     * 根据固定分组查找固定分组的选题信息
     *
     * @param fixGroup 固定分组信息
     * @return 改固定分组的所有选题
     */
    List<FixGroupTopic> listFixGroupTopicByFixGroup(FixGroup fixGroup);

    /**
     * 根据id删除选题信息
     *
     * @param fixGroupTopic 存储了ID
     * @return 影响行数
     */
    Integer deleteFixGroupTopicById(FixGroupTopic fixGroupTopic);
}

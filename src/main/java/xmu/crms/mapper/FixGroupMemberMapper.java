package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupMember;

import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/23
 *
 */
@Mapper
@Component
public interface FixGroupMemberMapper {

    /**
     * 查找固定分组下的所有成员信息
     *
     * @param fixGroup fixGroup
     * @return List<FixGroupMember>
     */
    List<FixGroupMember> listFixGroupMemberByFixGroup(FixGroup fixGroup);

    /**
     * 根据固定小组id和学生id删除记录，即将该学生移出该组
     *
     * @param fixGroupMember FixGroupMember
     * @return 影响行数
     */
    Integer deleteFixGroupMemberByFixGroupAndStudent(FixGroupMember fixGroupMember);
}

package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author: guiyu
 * @time: 2017/12/19 8:15
 * @description:
 */
@Mapper
@Component
public interface FixGroupMapper {

    /**
     * 按照id获取固定分组信息
     *
     * @param id
     * @return
     */
    FixGroup getFixGroupById(BigInteger id);

    /**
     * 按classId查找FixGroup信息.
     *
     * @param classId
     * @return
     */
    List<FixGroup> listFixGroupByClassId(BigInteger classId);

    /**
     * 通过学生id和班级id获取学生所在的班级固定小组
     *
     * @param userId
     * @param classId
     * @return
     */
    FixGroup getFixedGroupById(@Param("userId") BigInteger userId, @Param("classId") BigInteger classId);

    /**
     * 按FixGroupId删除FixGroupMember.
     *
     * @param fixGroupId
     */
    void deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId);

    /**
     * 查询固定小组成员. ＜p＞按照固定小组id查询该小组的成员
     *
     * @param groupId
     * @return
     */
    List<User> listFixGroupMemberByGroupId(BigInteger groupId);

    /**
     * 根据班级id和学生id查询学生，用于判断学生是否已经有小组
     *
     * @param classId
     * @param userId
     * @return
     */
    BigInteger getFixGroupMemberById(@Param("classId") BigInteger classId, @Param("userId") BigInteger userId);

    /**
     * 判断学生是否选了某个班级的课程，返回选课表id或者null
     *
     * @param classId
     * @param userId
     * @return
     */
    BigInteger getCourseSelectionId(@Param("classId") BigInteger classId, @Param("userId") BigInteger userId);

    /**
     * 在fixGroupMember表中插入一条新纪录,返回插入的记录条数
     *
<<<<<<< HEAD
     * @param fixGroupMember userId
=======
     * @param groupId userId
>>>>>>> yexiaona-branch
     * @return 插入位置的主键
     */
    int insertFixGroupMember(FixGroupMember fixGroupMember);


    /**
     * 根据id查找对应的班级信息，用于判断班级是否存在
     *
     * @param classInfoId
     * @return Class or null
     */
    ClassInfo getClassInfoById(BigInteger classInfoId);

    /**
     * 删除固定小组
     *
     * @param groupId
     */
    void deleteFixGroupByGroupId(BigInteger groupId);

    /**
     * 修改固定小组
     *
     * @param fixGroup
     * @return
     */
    int updateFixGroupByGroupId(FixGroup fixGroup);

    /**
     * 查询某学生是否存在,类中只返回id
     *
     * @param id
     * @return
     */
    User getUserById(BigInteger id);


    /**
     * 修改group
     * @param seminarGroup
     * @return
     */
    int updateSeminarGroup(SeminarGroup seminarGroup);

    /**
     * 根据id查找SeminarGroup,类中只返回id
     * @param id
     * @return
     */
    SeminarGroup getSeminarGroupById(BigInteger id);


    /**
     * 添加FixGroup表中的记录
     * @param fixGroup
     * @return
     */
    int insertFixGroup(FixGroup fixGroup);

}

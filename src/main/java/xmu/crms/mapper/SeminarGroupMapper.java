package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.User;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Huhui
 */
@Component
@Mapper
public interface SeminarGroupMapper {
 /**
  * 通过小组id查询讨论课小组.
  * @param seminarGroupId
  * @return SeminarGroup
  */
 SeminarGroup selectSeminarGroupByGroupId(BigInteger seminarGroupId);

 /**
  * 通过小组id查询讨论课小组组长.
  * @param groupId
  * @return SeminarGroup
  */
 SeminarGroup getSeminarGroupLeaderByGroupId(BigInteger groupId);

 /**
  * 通过讨论课id列出讨论课小组.
  * @param seminarId
  * @return List<SeminarGroup>
  */
 List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId);

 /**
  * 通过讨论课id和班级id列出讨论课小组.
  * @param seminarId
  * @param classId
  * @return List<SeminarGroup>
  */
 List<SeminarGroup> listSeminarGroupById(BigInteger seminarId, BigInteger classId);

 /**
  * 通过讨论课id删除讨论课小组.
  * @param seminarId
  */
 void deleteSeminarGroupBySeminarId(BigInteger seminarId);

 /**
  * 通过讨论课id和班级id新建小组.
  * @param seminarId
  * @param classId
  * @param seminarGroup
  * @return Integer
  */
 Integer insertSeminarGroupBySeminarId(@Param("seminarId") BigInteger seminarId, @Param("classId") BigInteger classId, @Param("seminarGroup") SeminarGroup seminarGroup);

 /**
  * 删除讨论课小组.
  * <p>按照id删除讨论课小组<br>
  * @author YeHongjie
  * @param seminarGroupId 讨论课小组的id
  * @exception IllegalArgumentException 信息不合法，id格式错误
  */

 void deleteSeminarGroupByGroupId(BigInteger seminarGroupId);

 /**
  * 通过讨论课id和用户id获取讨论课小组.
  * @param seminarId
  * @param userId
  * @return SeminarGroup
  */
 SeminarGroup getSeminarGroupById(@Param("seminarId") BigInteger seminarId, @Param("userId") BigInteger userId);

 /**
  * 通过话题id列出讨论课小组列表.
  * @param topicId
  * @return List<SeminarGroup>
  */
 List<SeminarGroup> selectSeminarGroupByTopicId(BigInteger topicId);

 /**
  * 根据小组id和用户id修改讨论课小组信息.
  * @param groupId
  * @param userId
  */
 void updateSeminarGroupById(@Param("groupId") BigInteger groupId, @Param("userId") BigInteger userId);

 /**
  * 新增获取未选择话题的小组方法.
  *
  * @param seminarId
  * @return List 未选择话题小组列表
  */
 List<SeminarGroup> selectSeminarGroupNotHaveTopic(BigInteger seminarId);

 /**
  * 组长辞职.
  * @param groupId
  * @param userId
  */
 void resignLeaderById(BigInteger groupId, BigInteger userId);
}

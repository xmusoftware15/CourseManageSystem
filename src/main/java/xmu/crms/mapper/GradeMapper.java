package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: zhangyuping
 * @Description:
 * @Data: 2017/12/19 17:09
 */
@Mapper
@Component
public interface GradeMapper {

    /**
     * 按seminarGroupTopicId删除学生打分表.
     * @param seminarGroupTopicId
     * @return Integer 影响行数
     */
    Integer deleteStudentScoreGroupBySeminarGroupId(BigInteger seminarGroupTopicId);

    /**
     * 获取某学生一堂讨论课信息.
     * <p>获取某学生一堂讨论课的详细信息（包括成绩）<br>
     * @param seminarGroup
     * @return SeminarGroup
     */
    SeminarGroup getSeminarGroupBySeminarGroupId(SeminarGroup seminarGroup);

    /**
     * 根据学生id获取该生的所有讨论课.
     * @param userId
     * @return list 讨论课列表
     */
    List<SeminarGroup> getSeminarGroupIdByUserId(BigInteger userId);

    /**
     * 提交对其他小组的打分.
     * @param studentScoreGroup
     * @return Integer 影响行数
     */
    Integer insertGroupGradeByUserId(StudentScoreGroup studentScoreGroup);

    /**
     * 按ID设置小组报告分.
     * @param seminarGroup
     * @return Integer 影响行数
     */
    Integer updateGroupByGroupId(SeminarGroup seminarGroup);

    /**
     * 根据seminarGroupId获得该小组所有seminarGroupTopic
     * @param seminarGroupId
     * @return list SeminarGroupTopic
     */
    List<SeminarGroupTopic> getSeminarGroupTopicBySeminarGroupId(BigInteger seminarGroupId);

    /**
     * 根据 seminarGroupTopicId获得所有其他小组给其的展示评分
     * @param seminarGroupTopicId
     * @return List StudentScoreGroup
     */
    List<StudentScoreGroup> getGroupPresentationGradeBySeminarGroupTopicId(BigInteger seminarGroupTopicId);

    /**
     * 根据讨论课id获得讨论课信息（包括课程id）
     * @param seminarId
     * @return Seminar
     */
    Seminar getSeminarCourseIdBySeminarId(BigInteger seminarId);

    /**
     * 根据课程id获得该课程评分标准
     * @param courseId
     * @return List BigInteger
     */
    Course getPercentageByCourseId(BigInteger courseId);

    /**
     * 提交讨论组某topic的最终展示成绩
     * @param seminarGroupTopic
     * @return Integer 影响行数
     */
    Integer updatePresentationGrade(SeminarGroupTopic seminarGroupTopic);

    /**
     * 提交讨论组最终展示成绩
     * @param seminarGroup
     * @return Integer 影响行数
     */
    Integer updateFinalPresentationGrade(SeminarGroup seminarGroup);

    /**
     * 提交讨论组最终成绩
     * @param seminarGroup
     * @return Integer 影响行数
     */
    Integer updateFinalGrade(SeminarGroup seminarGroup);

    /**
     * 根据seminarId获得所有seminarGroup(报告成绩，展示成绩)
     * @param seminarId
     * @return List SeminarGroup
     */
    List<SeminarGroup> getAllSeminarGroupBySeminarId(BigInteger seminarId);

//    /**
//     * 获得报告和展示成绩
//     * @param seminarGroup
//     * @return BigInteger
//     */
//    SeminarGroup getReportAndPresentationGrade(SeminarGroup seminarGroup);
}

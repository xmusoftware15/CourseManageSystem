package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.mapper.GradeMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: zhangyuping
 * @Description:
 * @Data: 2017/12/21 15:00
 */
@Component
public class GradeDAO {
    @Autowired
    private GradeMapper gradeMapper;

    public Boolean deleteStudentScoreGroupByTopicId(BigInteger seminarGroupTopicId){
        Integer res = gradeMapper.deleteStudentScoreGroupBySeminarGroupId(seminarGroupTopicId);
        return res > 0;
    }

    public Boolean updateGroupByGroupId(SeminarGroup seminarGroup) throws GroupNotFoundException{
        Integer res = gradeMapper.updateGroupByGroupId(seminarGroup);
        if (res > 0) {
            return true;
        } else {
            throw new GroupNotFoundException();
        }
    }

    public Boolean insertGroupGradeByUserId(StudentScoreGroup studentScoreGroup){
        if (gradeMapper.insertGroupGradeByUserId(studentScoreGroup) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public SeminarGroup getSeminarGroupBySeminarGroupId(SeminarGroup seminarGroup) {
        SeminarGroup seminarGroup1=gradeMapper.getSeminarGroupBySeminarGroupId(seminarGroup);
        return  seminarGroup1;
    }

    public List<SeminarGroup> getSeminarGroupIdByUserId(BigInteger userId)
    {
        List<SeminarGroup> seminarGroups=gradeMapper.getSeminarGroupIdByUserId(userId);
        return seminarGroups;
    }

    public List<SeminarGroupTopic> getSeminarGroupTopicBySeminarGroupId(BigInteger seminarGroupId){
        List<SeminarGroupTopic> seminarGroupTopics=gradeMapper.getSeminarGroupTopicBySeminarGroupId(seminarGroupId);
        return seminarGroupTopics;
    }

    public List<StudentScoreGroup> getGroupPresentationGradeBySeminarGroupTopicId(BigInteger seminarGroupTopicId){
        List<StudentScoreGroup> studentScoreGroups=gradeMapper
                .getGroupPresentationGradeBySeminarGroupTopicId(seminarGroupTopicId);
        return studentScoreGroups;
    }

    public Seminar getSeminarCourseIdBySeminarId(BigInteger seminarId){
        Seminar seminar=gradeMapper.getSeminarCourseIdBySeminarId(seminarId);
        return seminar;
    }

    public Course getPercentageByCourseId(BigInteger courseId){
        Course course=gradeMapper.getPercentageByCourseId(courseId);
        return course;
    }

    public Boolean updatePresentationGrade(SeminarGroupTopic seminarGroupTopic){
        Integer res = gradeMapper.updatePresentationGrade(seminarGroupTopic);
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateFinalPresentationGrade(SeminarGroup seminarGroup){
        Integer res = gradeMapper.updateFinalPresentationGrade(seminarGroup);
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateFinalGrade(SeminarGroup seminarGroup){
        Integer res = gradeMapper.updateFinalGrade(seminarGroup);
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<SeminarGroup> getAllSeminarGroupBySeminarId(BigInteger seminarId){
        List<SeminarGroup> seminarGroups=gradeMapper.getAllSeminarGroupBySeminarId(seminarId);
        return seminarGroups;
    }
}

package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.GradeDAO;
import xmu.crms.entity.*;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.service.GradeService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.SeminarService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zhangyuping
 * @Description:
 * @Data: 2017/12/21 15:43
 */
@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    private GradeDAO gradeDAO;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private SeminarGroupService seminarGroupService;

    @Override
    public void deleteStudentScoreGroupByTopicId(BigInteger seminarGroupTopicId) throws IllegalArgumentException
    {
        if (seminarGroupTopicId.intValue() <= 0) {
            throw new IllegalArgumentException("topicId");
        }
        //删除所有与该seminarGroupTopicId相关的学生打分记录
        gradeDAO.deleteStudentScoreGroupByTopicId(seminarGroupTopicId);
    }

    @Override
    public void insertGroupGradeByUserId(BigInteger topicId, BigInteger userId, BigInteger groupId, BigInteger grade)
            throws IllegalArgumentException {
        {
            if (groupId.intValue() < 0) {
                throw new IllegalArgumentException("groupId");
            }
            if (topicId.intValue() < 0) {
                throw new IllegalArgumentException("topicId");
            }
            StudentScoreGroup studentScoreGroup = new StudentScoreGroup();
            SeminarGroupTopic seminarGroupTopic=new SeminarGroupTopic();
            Topic topic=new Topic();
            topic.setId(topicId);
            seminarGroupTopic.setTopic(topic);
            User user=new User();
            user.setId(userId);
            studentScoreGroup.setSeminarGroupTopic(seminarGroupTopic);
            studentScoreGroup.setStudent(user);
            studentScoreGroup.setGrade(grade.intValue());
            gradeDAO.insertGroupGradeByUserId(studentScoreGroup);
        }
    }

    @Override
    public void updateGroupByGroupId(BigInteger seminar_group_id, BigInteger grade)
            throws GroupNotFoundException, IllegalArgumentException
    {
        if (seminar_group_id.intValue() <= 0) {
            IllegalArgumentException exception = new IllegalArgumentException("seminar_group_id");
            throw exception;
        }
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setReportGrade(grade.intValue());
        seminarGroup.setId(seminar_group_id);
        gradeDAO.updateGroupByGroupId(seminarGroup);
    }

    @Override
    public SeminarGroup getSeminarGroupBySeminarGroupId(BigInteger userId, BigInteger seminarGroupId)
            throws GroupNotFoundException, IllegalArgumentException
    {
        if (seminarGroupId.intValue() <= 0) {
            IllegalArgumentException exception = new IllegalArgumentException("seminarGroupId");
            throw exception;
        }
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(seminarGroupId);
        return gradeDAO.getSeminarGroupBySeminarGroupId(seminarGroup);
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByUserId(BigInteger userId)
            throws IllegalArgumentException
    {
        if (userId.intValue() < 0) {
            throw new IllegalArgumentException("userId");
        }
        List<SeminarGroup> seminarGroups= gradeDAO.getSeminarGroupIdByUserId(userId);
        List<SeminarGroup> seminarGroupList=new ArrayList<>();
        for(SeminarGroup seminarGroup:seminarGroups)
        {
            seminarGroupList.add(gradeDAO.getSeminarGroupBySeminarGroupId(seminarGroup));
        }
        return seminarGroupList;
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByCourseId(BigInteger userId,BigInteger courseId)
            throws IllegalArgumentException
    {
        if (userId.intValue() < 0) {
            throw new IllegalArgumentException("userId");
        }
        if (courseId.intValue() < 0) {
            throw new IllegalArgumentException("courseId");
        }
        List<Seminar> seminars= null;
        try {
            //该课程的所有讨论课
            seminars = seminarService.listSeminarByCourseId(courseId);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
        //所有讨论课的所有讨论组集合
        List<List<SeminarGroup>>listList=new ArrayList<List<SeminarGroup>>();
        for(Seminar seminar:seminars) {
            List<SeminarGroup> seminarGroups = null;
            try {
                //该讨论课的所有讨论小组
                seminarGroups = seminarGroupService.listSeminarGroupBySeminarId(seminar.getId());
            } catch (SeminarNotFoundException e) {
                e.printStackTrace();
            }
            listList.add(seminarGroups);
        }
        //该学生的所有讨论小组
        List<SeminarGroup> seminarGroupList=gradeDAO.getSeminarGroupIdByUserId(userId);
        List<SeminarGroup> list=new ArrayList<>();
        //讨论课
        for(int i=0;i<listList.size();i++)
        {
            //讨论小组
            for(int j=0;j<listList.get(i).size();j++)
            {
                for(int k=0;k<seminarGroupList.size();k++)
                {
                    if((listList.get(i).get(j).getSeminar().getId()).equals(seminarGroupList.get(k)
                            .getSeminar().getId())) {
                        //该课程该学生所有讨论小组
                        list.add(seminarGroupList.get(k));
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void countPresentationGrade(BigInteger seminarId, BigInteger seminarGroupId)
            throws IllegalArgumentException
    {
        if (seminarId.intValue() < 0) {
            throw new IllegalArgumentException("seminarId");
        }
        if (seminarGroupId.intValue() < 0) {
            throw new IllegalArgumentException("seminarGroupId");
        }
        List<SeminarGroupTopic> seminarGroupTopics=gradeDAO.getSeminarGroupTopicBySeminarGroupId(seminarGroupId);
        Integer topicsCount=seminarGroupTopics.size();
        Integer allSum=0;
        Integer allAver;
        for(SeminarGroupTopic seminarGroupTopic:seminarGroupTopics){
            Integer gradeSum=0;
            Integer gradeAver;
            List<StudentScoreGroup> studentScoreGroups=
                    gradeDAO.getGroupPresentationGradeBySeminarGroupTopicId(seminarGroupTopic.getId());
            for(StudentScoreGroup studentScoreGroup:studentScoreGroups){
                gradeSum+=studentScoreGroup.getGrade();
            }
            //该topic的展示平均成绩
            gradeAver=gradeSum/studentScoreGroups.size();
            allSum+=gradeAver;

            SeminarGroupTopic seminarGroupTopic1=new SeminarGroupTopic();
            seminarGroupTopic1.setId(seminarGroupTopic.getId());
            seminarGroupTopic1.setPresentationGrade(gradeAver);
            gradeDAO.updatePresentationGrade(seminarGroupTopic1);
        }
         //该讨论组的展示平均成绩
         allAver=allSum/topicsCount;
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(seminarGroupId);
        seminarGroup.setPresentationGrade(allAver);
        //更新数据库
        gradeDAO.updateFinalPresentationGrade(seminarGroup);
    }

    @Override
    public void countGroupGradeBySerminarId(BigInteger seminarId, BigInteger seminarGroupId) throws IllegalArgumentException {
        if (seminarId.intValue() < 0) {
            throw new IllegalArgumentException("seminarId");
        }
        if (seminarGroupId.intValue() < 0) {
            throw new IllegalArgumentException("seminarGroupId");
        }
        //获得报告和展示最终成绩
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(seminarGroupId);
        SeminarGroup seminarGroup1=gradeDAO.getSeminarGroupBySeminarGroupId(seminarGroup);
        //获得报告成绩
        Integer reportGrade=seminarGroup1.getReportGrade();
        //获得展示成绩
        Integer presentationGrade=seminarGroup1.getPresentationGrade();
        //获得课程
        Seminar seminar=gradeDAO.getSeminarCourseIdBySeminarId(new BigInteger("1"));
        //获得成绩计算比例
        Course course=gradeDAO.getPercentageByCourseId(seminar.getCourse().getId());
        Integer reportGradePercentage=course.getReportPercentage();
        Integer presentationGradePercentage=course.getPresentationPercentage();
        //获得该组最终成绩
        Double finalGrade=reportGrade*reportGradePercentage/100.0+presentationGrade*presentationGradePercentage/100.0;

        //遍历Seminargroup表计算同一seminar其他组的最终成绩，再根据打分比例决定最终成绩
        List<Double> finalGrades=new ArrayList<Double>();
        List<SeminarGroup> seminarGroups=gradeDAO.getAllSeminarGroupBySeminarId(new BigInteger("1"));
        for(SeminarGroup seminarGroup2:seminarGroups){
            //获得报告成绩
            Integer reportGrade1=seminarGroup2.getReportGrade();
            //获得展示成绩
            Integer presentationGrade1=seminarGroup2.getPresentationGrade();
            //获得课程
            Seminar seminar1=gradeDAO.getSeminarCourseIdBySeminarId(new BigInteger("1"));
            //获得成绩计算比例
            Course course1=gradeDAO.getPercentageByCourseId(seminar1.getCourse().getId());
            Integer reportGradePercentage1=course1.getReportPercentage();
            Integer presentationGradePercentage1=course1.getPresentationPercentage();
            //获得该组最终成绩
            Double finalGrade1=reportGrade1*reportGradePercentage1/100.0
                    +presentationGrade1*presentationGradePercentage1/100.0;
            finalGrades.add(finalGrade1);
        }
        //将成绩从高到低排序
        for(int i=0;i<finalGrades.size()-2;i++)
        {
            for(int j=i+1;j<finalGrades.size();j++){
                if(finalGrades.get(i).compareTo(finalGrades.get(j))<0)
                {
                    //交换两数
                    Collections.swap(finalGrades,i,j);
                }
            }
        }
        //根据比例得出最终分数
        Integer fivePointPercentage=course.getFivePointPercentage();
        Integer fourPointPercentage=course.getFourPointPercentage();
        Integer threePointPercentage=course.getThreePointPercentage();
        Integer index=0;
        for(int i=0;i<finalGrades.size();i++){
            if(finalGrade.equals(finalGrades.get(i))){
                index=i+1;
                break;
            }
        }
        Integer percentage=index*100/finalGrades.size();
        BigInteger grade;
        if(percentage<fivePointPercentage){
            grade=new BigInteger("5");
        }
        else if(percentage<fivePointPercentage+fourPointPercentage){
            grade=new BigInteger("4");
        }
        else {
            grade=new BigInteger("3");
        }
        //更新数据库
        seminarGroup.setFinalGrade(grade.intValue());
        gradeDAO.updateFinalGrade(seminarGroup);
    }
}

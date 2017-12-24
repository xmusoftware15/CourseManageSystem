package xmu.crms.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.*;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zhangyuping
 * @Description:
 * @Data: 2017/12/19 18:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeMapperTest {
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private SeminarMapper seminarMapper;

    @Test
    public void testDeleteStudentScoreGroupBySeminarGroupId() {
        Integer t=gradeMapper.deleteStudentScoreGroupBySeminarGroupId(new BigInteger("100"));
        System.out.println(t);
    }
    @Test
    public void testInsertGroupGradeByUserId(){//学生8给100组打5分
        User user=new User();
        user.setId(new BigInteger("8"));
        SeminarGroupTopic seminarGroupTopic=new SeminarGroupTopic();
        seminarGroupTopic.setId(new BigInteger("100"));
        StudentScoreGroup studentScoreGroup=new StudentScoreGroup();
        studentScoreGroup.setGrade(5);
        studentScoreGroup.setStudent(user);
        studentScoreGroup.setSeminarGroupTopic(seminarGroupTopic);
        Integer t=gradeMapper.insertGroupGradeByUserId(studentScoreGroup);
        System.out.println(t);
    }
    @Test
    public void testGetSeminarGroupBySeminarGroupId(){//获取1组的小组信息
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(new BigInteger("1"));
        SeminarGroup s=gradeMapper.getSeminarGroupBySeminarGroupId(seminarGroup);
        System.out.println(s.getId()+" "+s.getSeminar().getId()+" "+s.getReportGrade()+" "+s.getPresentationGrade()
                +" "+s.getFinalGrade()+" "+s.getReport()+" "+s.getClassInfo().getId()+" "+s.getLeader().getId());
    }
    @Test
    public void testUpdateGroupByGroupId(){//给1组报告打4分
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(new BigInteger("1"));
        seminarGroup.setReportGrade(4);
        Integer t=gradeMapper.updateGroupByGroupId(seminarGroup);
        System.out.println(t);
    }
    @Test
    public void testGetSeminarGroupIdByUserId(){//获取学生3的全部讨论组id
        List<SeminarGroup> seminarGroups=gradeMapper.getSeminarGroupIdByUserId(new BigInteger("3"));
        for (SeminarGroup seminarGroup : seminarGroups
                ) {
            System.out.println(seminarGroup.getId());
        }
    }

    @Test
    public void testGetSeminarGroupTopicBySeminarGroupId(){//获得31组选择的全部topicid
        List<SeminarGroupTopic>seminarGroupTopics=gradeMapper
                .getSeminarGroupTopicBySeminarGroupId(new BigInteger("31"));
        for(SeminarGroupTopic seminarGroupTopic:seminarGroupTopics){
            System.out.println(seminarGroupTopic.getId());
        }
    }

    @Test
    public void testGetSeminarCourseIdBySeminarId(){
        Seminar seminar=gradeMapper.getSeminarCourseIdBySeminarId(new BigInteger("1"));
        System.out.println(seminar.getCourse().getId());
    }

    @Test
    public void testGetPercentageByCourseId(){
        Course course=gradeMapper.getPercentageByCourseId(new BigInteger("1"));
        System.out.println(course.getId()+" "+course.getReportPercentage()+" "+course.getPresentationPercentage()+
        " "+course.getFivePointPercentage()+" "+course.getFourPointPercentage()+" "+course.getThreePointPercentage());
    }

    @Test
    public void testGetGroupPresentationGradeBySeminarGroupTopicId(){
        List<StudentScoreGroup> studentScoreGroups=gradeMapper.getGroupPresentationGradeBySeminarGroupTopicId
                (new BigInteger("1"));
        for(StudentScoreGroup studentScoreGroup:studentScoreGroups){
            System.out.println(studentScoreGroup.getGrade());
        }
    }

    @Test
    public void testUpdatePresentationGrade(){
        SeminarGroupTopic seminarGroupTopic=new SeminarGroupTopic();
        seminarGroupTopic.setPresentationGrade(1);
        seminarGroupTopic.setId(new BigInteger("1"));
        gradeMapper.updatePresentationGrade(seminarGroupTopic);
    }

    @Test
    public void testUpdateFinalPresentationGrade(){
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(new BigInteger("1"));
        seminarGroup.setPresentationGrade(3);
        gradeMapper.updateFinalPresentationGrade(seminarGroup);
    }

    @Test
    public void testFinalGradeCount(){

        //获得报告和展示最终成绩
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(new BigInteger("1"));
        SeminarGroup seminarGroup1=gradeMapper.getSeminarGroupBySeminarGroupId(seminarGroup);
        System.out.println(seminarGroup1.getReportGrade()+" "+seminarGroup1.getPresentationGrade());
        //获得报告成绩
        Integer reportGrade=seminarGroup1.getReportGrade();
        //获得展示成绩
        Integer presentationGrade=seminarGroup1.getPresentationGrade();
        //获得课程
        Seminar seminar=gradeMapper.getSeminarCourseIdBySeminarId(new BigInteger("1"));
        //获得成绩计算比例
        Course course=gradeMapper.getPercentageByCourseId(seminar.getCourse().getId());
        Integer reportGradePercentage=course.getReportPercentage();
        Integer presentationGradePercentage=course.getPresentationPercentage();
        //获得该组最终成绩
        Double finalGrade=reportGrade*reportGradePercentage/100.0+presentationGrade*presentationGradePercentage/100.0;
        System.out.println(finalGrade);
        //遍历Seminargroup表计算同一seminar其他组的最终成绩，再根据打分比例决定最终成绩
        List<Double> finalGrades=new ArrayList<Double>();
        List<SeminarGroup> seminarGroups=gradeMapper.getAllSeminarGroupBySeminarId(new BigInteger("1"));
        for(SeminarGroup seminarGroup2:seminarGroups){
            //获得报告成绩
            Integer reportGrade1=seminarGroup2.getReportGrade();
            //获得展示成绩
            Integer presentationGrade1=seminarGroup2.getPresentationGrade();
            //获得课程
            Seminar seminar1=gradeMapper.getSeminarCourseIdBySeminarId(new BigInteger("1"));
            //获得成绩计算比例
            Course course1=gradeMapper.getPercentageByCourseId(seminar1.getCourse().getId());
            Integer reportGradePercentage1=course1.getReportPercentage();
            Integer presentationGradePercentage1=course1.getPresentationPercentage();
            //获得该组最终成绩
            Double finalGrade1=reportGrade1*reportGradePercentage1/100.0
                    +presentationGrade1*presentationGradePercentage1/100.0;
            finalGrades.add(finalGrade1);
            System.out.println("！！"+finalGrade1);
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
        System.out.println(fivePointPercentage+" "+fourPointPercentage+" "+threePointPercentage);
        Integer index=0;
        for(int i=0;i<finalGrades.size();i++){
            if(finalGrade.equals(finalGrades.get(i))){
                index=i+1;
                break;
            }
        }
        Integer percentage=index*100/finalGrades.size();
        BigInteger grade;
        if(percentage <fivePointPercentage){
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
        System.out.println(gradeMapper.updateFinalGrade(seminarGroup));
        System.out.println(percentage+" "+grade);
    }

    @Test
    public void testPresentationGradeCount(){
        //获得小组选择的全部SeminarGroupTopic
        List<SeminarGroupTopic> seminarGroupTopics=
                gradeMapper.getSeminarGroupTopicBySeminarGroupId(new BigInteger("1"));
        Integer topicsCount=seminarGroupTopics.size();
        System.out.println("topic num:"+topicsCount);
        Integer allSum=0;
        Integer allAver;
        for(SeminarGroupTopic seminarGroupTopic:seminarGroupTopics){
            Integer gradeSum=0;
            Integer gradeAver;
            //获得其他小组的打分
            List<StudentScoreGroup> studentScoreGroups=
                    gradeMapper.getGroupPresentationGradeBySeminarGroupTopicId(seminarGroupTopic.getId());
            for(StudentScoreGroup studentScoreGroup:studentScoreGroups){
                gradeSum+=studentScoreGroup.getGrade();
            }
            //该topic的展示平均成绩
            gradeAver=gradeSum/studentScoreGroups.size();
            System.out.println("topic aver:"+gradeAver);
            allSum+=gradeAver;
            SeminarGroupTopic seminarGroupTopic1=new SeminarGroupTopic();
            seminarGroupTopic1.setId(seminarGroupTopic.getId());
            seminarGroupTopic1.setPresentationGrade(gradeAver);
            gradeMapper.updatePresentationGrade(seminarGroupTopic1);
        }
        System.out.println("topic sum:"+allSum);
        //该讨论组的展示平均成绩
        allAver=allSum/topicsCount;
        System.out.println("group aver:"+allAver);
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(new BigInteger("1"));
        seminarGroup.setPresentationGrade(allAver);
        //更新数据库
        System.out.println("数据库"+gradeMapper.updateFinalPresentationGrade(seminarGroup));
    }

    @Test
    public void testUpdateFinalGrade()
    {
        SeminarGroup seminarGroup=new SeminarGroup();
        seminarGroup.setId(new BigInteger("1"));
        seminarGroup.setFinalGrade(3);
        gradeMapper.updateFinalGrade(seminarGroup);
    }

    @Test
    public void testGetAllSeminarGroupBySeminarId(){
       List<SeminarGroup> seminarGroups=gradeMapper.getAllSeminarGroupBySeminarId(new BigInteger("1"));
       for(SeminarGroup seminarGroup:seminarGroups){
           System.out.println(seminarGroup.getReportGrade()+" "+seminarGroup.getPresentationGrade());
       }
    }

//    @Test
//    public void testListSeminarGradeByUserId(){
//        //该课程的所有讨论课
//        List<Seminar> seminars = seminarMapper.listSeminarByCourseId(new BigInteger("1"));
//        //所有讨论课的所有小组
//        List<List<SeminarGroup>>listList=new ArrayList<List<SeminarGroup>>();
//        for(Seminar seminar:seminars) {
//            List<SeminarGroup> seminarGroups = null;
//            //该讨论课的所有讨论小组
//            seminarGroups = seminarGroupService.listSeminarGroupBySeminarId(seminar.getId());
//            listList.add(seminarGroups);
//        }
//        //该学生的所有讨论小组
//        List<SeminarGroup> seminarGroupList=gradeMapper.getSeminarGroupIdByUserId(new BigInteger("1"));
//        List<SeminarGroup> list=new ArrayList<>();
//        for(int i=0 ; i<listList.size();i++)
//        {
//            for(int j=0;j<listList.get(i).size();j++)
//            {
//                for(int k=0;k<seminarGroupList.size();k++)
//                {
//                    if((listList.get(i).get(j).getSeminar().getId()).equals(seminarGroupList.get(k)
//                            .getSeminar().getId())) {
//                        //该课程该学生所有讨论小组
//                        list.add(seminarGroupList.get(k));
//                    }
//                }
//            }
//        }
//    }
}

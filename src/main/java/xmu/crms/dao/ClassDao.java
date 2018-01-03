package xmu.crms.dao;

import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Location;
import xmu.crms.exception.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author hxr
 */
public interface ClassDao {

    /**
     * 按classId删除CourseSelection表的一条记录.
     * @param classId
     * @return Boolean
     */
    Boolean deleteClassSelectionByClassId(BigInteger classId);

    /**
     * 按课程名称和教师名称获取班级列表.
     * <p>根据课程名和教师名获取课程ID，通过课程ID获取班级列表;若课程名和班级名均不为空，取交集<br>
     * @param courseName
     * @param teacherName
     * @return List<ClassInfo>
     * @throws UserNotFoundException
     * @throws CourseNotFoundException
     * @throws ClassesNotFoundException
     */
    List<ClassInfo> listClassByName(String courseName, String teacherName) throws
            UserNotFoundException,CourseNotFoundException,ClassesNotFoundException;

    /**
     * 根据课程ID获得班级列表.
     * @param courseId
     * @return List<ClassInfo>
     * @throws CourseNotFoundException
     */
    List<ClassInfo> findClassByCourseId(BigInteger courseId) throws
            CourseNotFoundException;

    /**
     * 按班级id获取班级详情.
     * <p>根据班级id获取班级<br>
     * @param classId
     * @return ClassInfo
     * @throws ClassesNotFoundException
     */
    ClassInfo findClassByClassId(BigInteger classId) throws
            ClassesNotFoundException;

    /**
     * 按班级id和班级修改班级信息.
     * <p>根据班级id修改班级信息<br>
     * @param classId
     * @param newClass
     * @return Boolean
     * @throws ClassesNotFoundException
     */
    Boolean updateClassByClassId(BigInteger classId, ClassInfo newClass) throws
            ClassesNotFoundException;

    /**
     * 按班级id删除班级.
     * <p>根据班级id删除班级<br>
     * @param classId
     * @return Boolean
     * @throws ClassesNotFoundException
     */
    Boolean deleteClassByClassId(BigInteger classId)
            throws ClassesNotFoundException;

    /**
     * 学生按班级id选择班级.
     * <p>根据班级id和用户id新增选课记录<br>
     * @param userId
     * @param classId
     * @return BigInteger
     * @throws UserNotFoundException
     * @throws ClassesNotFoundException
     */
    BigInteger insertCourseSelectionById(BigInteger userId, BigInteger classId) throws
            UserNotFoundException,ClassesNotFoundException;

    /**
     * 学生按班级id取消选择班级.
     * <p>
     * 根据班级id和用户id删除选课记录及与该班级相关的信息<br>
     * @param userId
     * @param classId
     * @return Boolean
     * @throws UserNotFoundException
     * @throws ClassesNotFoundException
     */
    Boolean deleteCourseSelectionById(BigInteger userId, BigInteger classId) throws
            UserNotFoundException,ClassesNotFoundException;


    /**
     * 通过学生id和班级id找到对应的课程id.
     * @param userId
     * @param classId
     * @return BigInteger
     */
    BigInteger findCourseIdByUserIdAndClassId(BigInteger userId, BigInteger classId);

    /**
     * 老师获取该班级签到状态.
     * <p>
     * 根据讨论课id及班级id，获得老师所在位置经纬度和该班级的签到状态<br>
     * @param classId
     * @param seminarId
     * @return Location
     * @throws SeminarNotFoundException
     */
    Location getCallStatusById(BigInteger classId, BigInteger seminarId) throws SeminarNotFoundException;

    /**
     * 新建班级.
     * <p>
     * 根据课程id新建班级<br>
     * @param courseId
     * @param classInfo
     * @return BigInteger
     * @throws CourseNotFoundException
     */
    BigInteger insertClassById(BigInteger courseId, ClassInfo classInfo) throws
            CourseNotFoundException;

    /**
     * 按courseId删除Class.
     * <p>
     * 先根据CourseId获得所有的class的信息，然后根据class信息删除courseSelection表的记录，然后再根据courseId和classId删除ScoureRule表记录，再调用根据classId删除固定分组，最后再将班级的信息删除<br>
     * @param courseId
     * @throws CourseNotFoundException
     */
    void deleteClassByCourseId(BigInteger courseId) throws
            CourseNotFoundException;

    /**
     * 按classId删除ScoreRule.
     * @param classId
     * @throws ClassesNotFoundException
     */
    void deleteScoreRuleById(BigInteger classId) throws ClassesNotFoundException;

    /**
     * 查询评分规则.
     * <p>
     * 按id查询指定的评分规则<br>
     * @param classId
     * @return ClassInfo
     * @throws ClassesNotFoundException
     */
    ClassInfo getScoreRule(BigInteger classId) throws ClassesNotFoundException;

    /**
     * 新增评分规则.
     * <p>
     * 新增评分规则<br>
     * @param classId
     * @param proportions
     * @return BigInteger
     * @throws InvalidOperationException
     * @throws ClassesNotFoundException
     */
    BigInteger insertScoreRule(BigInteger classId, ClassInfo proportions)
            throws InvalidOperationException,ClassesNotFoundException;

    /**
     * 老师发起签到.
     * <p>往location表插入一条当前讨论课班级的签到状态<br>
     * @param location
     * @return BigInteger
     * @throws SeminarNotFoundException
     * @throws ClassesNotFoundException
     */
    BigInteger callInRollById(Location location)
            throws SeminarNotFoundException,ClassesNotFoundException;

    /**
     * 根据学生ID获取班级列表.
     * <p>根据学生ID获取班级列表<br>
     * @param userId
     * @return List<ClassInfo>
     * @throws IllegalArgumentException
     * @throws ClassesNotFoundException
     */
    List<ClassInfo> listClassByUserId(BigInteger userId)
            throws IllegalArgumentException, ClassesNotFoundException;

    /**
     * 新增老师结束签到.
     * <p>老师结束签到,修改当前讨论课班级的签到状态为已结束<br>
     * @param location
     * @throws SeminarNotFoundException
     * @throws ClassesNotFoundException
     */
    void endCallRollById(Location location)
            throws SeminarNotFoundException, ClassesNotFoundException;

    /**
     * 通过班级id删除选课记录.
     * @param classId
     * @throws ClassesNotFoundException
     */
    void deleteCoueseSelectionByClassId(BigInteger classId) throws ClassesNotFoundException;
}

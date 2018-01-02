package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author hxr
 */
@Component
@Mapper
public interface ClassMapper {

    /**
     * 按classId删除CourseSelection表的一条记录.
     * @param classId
     * @return Boolean
     */
    Boolean deleteClassSelectionByClassId(BigInteger classId);

    /**
     * 通过老师的姓名查找该老师.
     * @param teacherName
     * @return User
     */
    User findTeacherIdByteacherName(String teacherName);

    /**
     * 通过教师id和课程名称查找课程.
     * @param teacherId
     * @param courseName
     * @return Course
     */
    Course findCourseIdByteacherIdAndCourseName(@Param("teacherId") BigInteger teacherId, @Param("courseName") String courseName);

    /**
     * 通过课程id列出课程下的班级列表.
     * @param courseId
     * @return List<ClassInfo>
     */
    List<ClassInfo> findClassByCourseId(BigInteger courseId);

    /**
     * 通过班级id查找班级.
     * @param classId
     * @return ClassInfo
     */
    ClassInfo findClassByClassId(BigInteger classId);

    /**
     * 通过班级id修改班级信息.
     * @param newClass
     * @return Boolean
     */
    Boolean updateClassByClassId(ClassInfo newClass);

    /**
     * 通过班级id删除班级.
     * @param classId
     * @return Boolean
     */
    Boolean deleteClassByClassId(BigInteger classId);

    /**
     * 通过用户id插入选课记录.
     * @param courseSelection
     * @return Integer
     */
    Integer insertCourseSelectionById(CourseSelection courseSelection);

    /**
     * 通过学生id查找学生.
     * @param studentId
     * @return User
     */
    User findStudentdByStudentId(BigInteger studentId);

    /**
     * 通过老师的id查找老师.
     * @param teacherId
     * @return User
     */
    User findTeacherByTeacherId(BigInteger teacherId);

    /**
     * 通过课程id查找课程.
     * @param courseId
     * @return Course
     */
    Course findCourseByCourseId(BigInteger courseId);

    /**
     * 通过用户id和班级id查找课程id.
     * @param userId
     * @param classId
     * @return BigInteger
     */
    BigInteger findCourseIdByUserIdAndClassId(@Param("userId") BigInteger userId, @Param("classId") BigInteger classId);

    /**
     * 通过用户id删除选课记录.
     * @param userId
     * @param classId
     * @return Boolean
     */
    Boolean deleteCourseSelectionById(@Param("userId") BigInteger userId, @Param("classId") BigInteger classId);

    /**
     * 老师获取该班级签到状态.
     * <p>
     * 根据讨论课id及班级id，获得老师所在位置经纬度和该班级的签到状态<br>
     * @param classId
     * @param seminarId
     * @return Location
     */
    Location getCallStatusById(@Param("classId") BigInteger classId, @Param("seminarId") BigInteger seminarId);

    /**
     * 通过班级id和讨论课id修改位置信息.
     * @param classId
     * @param seminarId
     * @param status
     * @return int
     */
    int updateLocationById(@Param("classId") BigInteger classId,@Param("seminarId") BigInteger seminarId,@Param("status") BigInteger status);

    /**
     * 通过讨论课id查找讨论课.
     * @param semiarId
     * @return Seminar
     */
    Seminar findSeminarById(BigInteger semiarId);

    /**
     * 新增班级.
     * @param classInfo
     * @return Integer
     */
    Integer insertClassById(ClassInfo classInfo);

    /**
     * 通过课程id删除班级.
     * @param courseId
     */
    void deleteClassByCourseId(BigInteger courseId);

    /**
     * 通过班级id删除打分规则.
     * @param classId
     */
    void deleteScoreRuleById(BigInteger classId);

    /**
     * 获取打分规则.
     * @param classId
     * @return ClassInfo
     */
    ClassInfo getScoreRule(BigInteger classId);

    /**
     * 新增打分规则.
     * @param classInfo
     * @return Integer
     */
    Integer insertScoreRule(ClassInfo classInfo);

    /**
     * 点名.
     * @param location
     * @return Integer
     */
    Integer callInRollById(Location location);

    /**
     * 通过用户id查找课程.
     * @param userId
     * @return List<CourseSelection>
     */
    List<CourseSelection> findClassByUserId(BigInteger userId);

    /**
     * 结束签到.
     * @param location
     */
    void endCallRollById(Location location);

    /**
     * 通过班级id删除选课记录.
     * @param classId
     */
    void deleteCoueseSelectionByClassId(BigInteger classId);

}

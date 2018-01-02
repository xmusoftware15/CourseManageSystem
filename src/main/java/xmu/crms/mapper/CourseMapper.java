package xmu.crms.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Course;

import java.math.BigInteger;
import java.util.List;

/**
 * @author: heqi
 * @time: 2017/12/24
 */

@Component
public interface CourseMapper {

    /**
     * 通过教师id查找课程列表.
     * @param userId
     * @return List<Course>
     */
    List<Course> listCourseByUserId(BigInteger userId);

    /**
     * 教师通过教师id新增选课记录
     * @param userId
     * @param course
     * @return Integer
     */
    Integer insertCourseByUserId(@Param("userId") BigInteger userId, @Param("course") Course course);

    /**
     * 通过课程id选课.
     * @param courseId
     * @return Course
     */
    Course selectCourseByCourseId(BigInteger courseId);

    /**
     * 通过课程id修改课程信息.
     * @param courseId
     * @param course
     */
    void updateCourseByCourseId(@Param("courseId") BigInteger courseId, @Param("course") Course course);

    /**
     * 通过课程id删除课程.
     * @param courseId
     */
    void deleteCourseByCourseId(BigInteger courseId);

    /**
     * 通过课程名称列出课程列表.
     * @param courseName
     * @return List<Course>
     */
    List<Course> listCourseByCourseName(String courseName);

}

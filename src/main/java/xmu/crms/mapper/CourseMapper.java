package xmu.crms.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.ClassesNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author: heqi
 * @time: 2017/12/24
 */

@Component
public interface CourseMapper {

    List<Course> listCourseByUserId(BigInteger userId);

    Integer insertCourseByUserId(@Param("userId") BigInteger userId, @Param("course") Course course);

    Course selectCourseByCourseId(BigInteger courseId);

    void updateCourseByCourseId(@Param("courseId") BigInteger courseId, @Param("course") Course course);

    void deleteCourseByCourseId(BigInteger courseId);

    List<Course> listCourseByCourseName(String courseName);

}

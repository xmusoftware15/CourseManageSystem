package xmu.crms.dao;

import xmu.crms.entity.Course;
import xmu.crms.exception.CourseNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author: heqi
 * @time: 2017/12/24
 */

public interface CourseDao {

    List<Course> listCourseByUserId(BigInteger userId)throws IllegalArgumentException,CourseNotFoundException;

    BigInteger insertCourseByUserId(BigInteger userId, Course course)throws IllegalArgumentException;

    Course getCourseByCourseId(BigInteger courseId)throws IllegalArgumentException,CourseNotFoundException;

    void updateCourseByCourseId(BigInteger courseId, Course course);

    void deleteCourseByCourseId(BigInteger courseId)throws IllegalArgumentException;

    List<Course> listCourseByCourseName(String courseName);

}

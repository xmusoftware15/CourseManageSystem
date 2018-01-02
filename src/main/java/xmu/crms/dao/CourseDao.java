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

    /**
     * 按userId获取与当前用户相关联的课程列表.
     * <p>老师与他相关联的课程列表<br>
     * @param userId
     * @return List<Course>
     * @throws IllegalArgumentException
     * @throws CourseNotFoundException
     */
    List<Course> listCourseByUserId(BigInteger userId)throws IllegalArgumentException,CourseNotFoundException;

    /**
     * 按userId创建课程.
     * <p>按userId创建课程<br>
     * @param userId
     * @param course
     * @return
     * @throws IllegalArgumentException
     */
    BigInteger insertCourseByUserId(BigInteger userId, Course course)throws IllegalArgumentException;

    /**
     * 按courseId获取课程 .
     * <p>按courseId获取课程 <br>
     * @param courseId
     * @return Course
     * @throws IllegalArgumentException
     * @throws CourseNotFoundException
     */
    Course getCourseByCourseId(BigInteger courseId)throws IllegalArgumentException,CourseNotFoundException;

    /**
     * 传入courseId和course信息修改course信息.
     * <p>传入courseId和course信息修改course信息 <br>
     * @param courseId
     * @param course
     */
    void updateCourseByCourseId(BigInteger courseId, Course course);

    /**
     * 按courseId删除课程.
     * <p>先根据courseID删除Seminar 和 class,然后再将course的信息删除<br>
     * @param courseId
     * @throws IllegalArgumentException
     */
    void deleteCourseByCourseId(BigInteger courseId)throws IllegalArgumentException;

    /**
     * 根据课程名称获取课程列表.
     * <p>根据课程名称获取课程列表<br>
     * @param courseName
     * @return List<Course>
     */
    List<Course> listCourseByCourseName(String courseName);

}

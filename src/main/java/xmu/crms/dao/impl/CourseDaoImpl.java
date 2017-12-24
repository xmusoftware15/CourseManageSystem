package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.dao.CourseDao;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.mapper.CourseMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author: heqi
 * @time: 2017/12/24
 */
@Component
public class CourseDaoImpl implements CourseDao{

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> listCourseByUserId(BigInteger userId) throws IllegalArgumentException, CourseNotFoundException {
        List<Course> courses = courseMapper.listCourseByUserId(userId);
        if(courses == null){
            throw new CourseNotFoundException();
        }
        else{
            return courses;
        }
    }

    @Override
    public BigInteger insertCourseByUserId(BigInteger userId, Course course) throws IllegalArgumentException {
        Integer res = courseMapper.insertCourseByUserId(userId, course);
        if (res > 0) {
            return course.getId();
        }
        return new BigInteger("-1");
    }

    @Override
    public Course getCourseByCourseId(BigInteger courseId) throws IllegalArgumentException, CourseNotFoundException {
        Course course = courseMapper.selectCourseByCourseId(courseId);
        if(course == null){
            throw new CourseNotFoundException();
        }
        else{
            return course;
        }

    }

    @Override
    public void updateCourseByCourseId(BigInteger courseId, Course course) {
        courseMapper.updateCourseByCourseId(courseId, course);
    }

    @Override
    public void deleteCourseByCourseId(BigInteger courseId) throws IllegalArgumentException {
        courseMapper.deleteCourseByCourseId(courseId);
    }

    @Override
    public List<Course> listCourseByCourseName(String courseName) {
        return courseMapper.listCourseByCourseName(courseName);
    }



}

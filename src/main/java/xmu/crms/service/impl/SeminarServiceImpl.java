package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.SeminarDao;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.service.SeminarService;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yexiaona
 * @Description:
 * @Data: 2017/12/19 21:49
 */
@Service
public class SeminarServiceImpl implements SeminarService {

    @Autowired
    private SeminarDao seminarDao;

    @Override
    public Boolean deleteSeminarByCourseId(BigInteger courseId) throws IllegalArgumentException {
        if (courseId.intValue() <= 0) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("courseId");
            throw illegalArgumentException;
        }
        Boolean isDeleted = true;
        List<Seminar> seminars = seminarDao.listSeminarByCourseId(courseId);
        for (Seminar seminar : seminars
                ) {
            isDeleted = deleteSeminarBySeminarId(seminar.getId());
            //isDeleted = topicMapper.deleteTopicBySeminarId(seminar.getId());
            //isDeleted = seminarGroupMapper.deleteSeminarGroupBySeminarId(seminar.getId());
        }
        return isDeleted;
    }

    @Override
    public Boolean deleteSeminarBySeminarId(BigInteger seminarId) throws IllegalArgumentException {
        if (seminarId.intValue() < 0) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("seminarId");
            throw illegalArgumentException;
        }
        return seminarDao.deleteSeminarBySeminarId(seminarId);
    }

    @Override
    public Seminar getSeminarBySeminarId(BigInteger seminarId) throws SeminarNotFoundException, IllegalArgumentException {
        if (seminarId.intValue() <= 0) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("seminarId");
            throw illegalArgumentException;
        }
        Seminar seminar = seminarDao.getSeminarBySeminarId(seminarId);
        return seminar;
    }

    @Override
    public BigInteger insertSeminarByCourseId(BigInteger courseId, Seminar seminar) throws IllegalArgumentException {
        if (courseId.intValue() <= 0) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("courseId");
            throw illegalArgumentException;
        }

        Course course = new Course();
        course.setId(courseId);
        seminar.setCourse(course);

        seminarDao.insertSeminarByCourseId(seminar);
        return seminar.getId();
    }

    @Override
    public List<Seminar> listSeminarByCourseId(BigInteger courseId) throws IllegalArgumentException {
        if (courseId.intValue() <= 0) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("courseId");
            throw illegalArgumentException;
        }
        List<Seminar> list = seminarDao.listSeminarByCourseId(courseId);
        return list;
    }

    @Override
    public Boolean updateSeminarBySeminarId(BigInteger seminarId, Seminar seminar) throws SeminarNotFoundException, IllegalArgumentException {
        if (seminarId.intValue() <= 0) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("seminarId");
            throw illegalArgumentException;
        }
        seminar.setId(seminarId);
        Boolean isUpdated = seminarDao.updateSeminarBySeminarId(seminar);
        return isUpdated;
    }
}

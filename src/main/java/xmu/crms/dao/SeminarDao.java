package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Seminar;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.mapper.SeminarMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yexiaona
 * @Description:
 * @Data: 2017/12/21 13:58
 */
@Component
public class SeminarDao {

    @Autowired
    SeminarMapper seminarMapper;

    public List<Seminar> listSeminarByCourseId(BigInteger courseId) {
        List<Seminar> list = seminarMapper.listSeminarByCourseId(courseId);
        return list;
    }

    public Boolean deleteSeminarBySeminarId(BigInteger seminarId) {
        Integer res = seminarMapper.deleteSeminarBySeminarId(seminarId);
        return res > 0;
    }

    public Seminar getSeminarBySeminarId(BigInteger seminarId) throws SeminarNotFoundException {
        Seminar seminar = seminarMapper.getSeminarBySeminarId(seminarId);
        if (null == seminar) {
            throw new SeminarNotFoundException();
        }
        return seminar;
    }

    public BigInteger insertSeminarByCourseId(Seminar seminar) {
        Integer res = seminarMapper.insertSeminarByCourseId(seminar);
        if (res > 0) {
            return seminar.getId();
        } else {
            return new BigInteger("-1");
        }
    }

    public Boolean updateSeminarBySeminarId(Seminar seminar) throws SeminarNotFoundException {
        Integer res = seminarMapper.updateSeminarBySeminarId(seminar);
        if (res > 0) {
            return true;
        } else {
            throw  new SeminarNotFoundException();
        }
    }

}

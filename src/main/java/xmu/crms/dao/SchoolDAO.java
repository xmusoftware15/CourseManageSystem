package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.School;
import xmu.crms.mapper.SchoolMapper;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/19
 *
 */
@Component
public class SchoolDAO {

    @Autowired
    private SchoolMapper schoolMapper;

    public List<School> listSchoolByCity(String city) {
        return schoolMapper.listSchoolByCity(city);
    }

    public BigInteger insertSchool(School school) {
        int result = schoolMapper.insertSchool(school);
        if (result > 0) {
            return school.getId();
        } else {
            return new BigInteger("-1");
        }
    }

    public School getSchoolBySchoolId(School school) {
        return schoolMapper.getSchoolBySchoolId(school);
    }
}

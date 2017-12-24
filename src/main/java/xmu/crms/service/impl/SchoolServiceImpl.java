package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.SchoolDAO;
import xmu.crms.entity.School;
import xmu.crms.service.SchoolService;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/19
 *
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDAO schoolDAO;

    @Override
    public List<School> listSchoolByCity(String city) {
        return schoolDAO.listSchoolByCity(city);
    }

    @Override
    public BigInteger insertSchool(School school) {
        return schoolDAO.insertSchool(school);
    }

    @Override
    public List<String> listProvince() {
        return null;
    }

    @Override
    public List<String> listCity(String province) {
        return null;
    }

    @Override
    public School getSchoolBySchoolId(BigInteger schoolId) {
        if (schoolId.intValue() <= 0) {
            throw new IllegalArgumentException("schoolId");
        }
        School school = new School();
        school.setId(schoolId);
        return schoolDAO.getSchoolBySchoolId(school);
    }
}

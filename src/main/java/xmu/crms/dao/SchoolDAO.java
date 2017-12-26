package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.entity.School;
import xmu.crms.mapper.SchoolMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 3-4
 */
@Repository
public class SchoolDAO {
    @Autowired
    private SchoolMapper schoolMapper;

    public List<School> getSchoolListByCity(String city) {
        List<School> schoolList = new ArrayList<>(16);
        try {
            schoolList = schoolMapper.getSchoolListByCity(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schoolList;
    }

    public BigInteger insertSchool(School school) {
        Integer id = null;
        try {
            id = schoolMapper.insertSchool(school);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(id > 0){
          return school.getId();
        }
       else{
          return BigInteger.valueOf(0);
        }
    }

    /**
     * 学校查重
     *
     * @param school
     * @return
     */
    public int rechecking(School school) {
        return schoolMapper.rechecking(school);
    }

    public List<String> listProvince() {
        return null;
    }

    public List<String> listCity(String province) {
        return null;
    }

    public School getSchoolBySchoolId(BigInteger schoolId) {
        School school;
        school = schoolMapper.getSchoolBySchoolId(schoolId);
        return school;
    }
}

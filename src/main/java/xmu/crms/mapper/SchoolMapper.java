package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Component;

import xmu.crms.entity.School;

/**
 * @author 3-4
 */
@Component
public interface SchoolMapper {

    /**
     * 获取学校列表
     * @param city
     * @return
     */
    List<School> getSchoolListByCity(String city);

    /**
     * 插入学校
     * @param school
     */
    Integer insertSchool(School school);

    /**
     * 学校查重
     * @param school
     * @return
     */
    int rechecking(School school);

    /**
     * 显示省份
     * @return
     */
    List<String> listProvince();

    /**
     * 通过省份查找城市
     * @param province
     * @return
     */
    List<String> listCity(String province);

    /**
     * 通过学校的id获取学校信息
     * @param schoolId
     * @return
     */
    School getSchoolBySchoolId(BigInteger schoolId);
}

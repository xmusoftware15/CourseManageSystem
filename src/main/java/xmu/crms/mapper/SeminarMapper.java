package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Seminar;

import java.math.BigInteger;
import java.util.List;

/**
 * @author yexiaona
 * @date 2017/12/19
 */
@Mapper
@Component
public interface SeminarMapper {

    /**
     * 按课程id删除讨论课.
     *
     * @param courseId
     * @return true删除成功 false删除失败
     */
    Boolean deleteSeminarByCourseId(BigInteger courseId);

    /**
     * 按讨论课id删除讨论课.
     *
     * @param seminarId
     * @return true删除成功 false删除失败
     */
    Integer deleteSeminarBySeminarId(BigInteger seminarId);

    /**
     * 获得学生当前讨论课信息.
     *
     * @param seminarId
     * @param userId
     * @return seminar 当前讨论课信息
     */
    Seminar getMySeminarBySeminarId(BigInteger seminarId, BigInteger userId);

    /**
     * 用户通过讨论课id获得讨论课信息.
     *
     * @param seminarId
     * @return seminar 相应的讨论课信息
     */
    Seminar getSeminarBySeminarId(BigInteger seminarId);

    /**
     * 获得学生相关的某个讨论课信息.
     *
     * @param seminarId
     * @param userId
     * @return seminar 相应的讨论课的详细信息
     */
    Seminar getSeminarDetailBySeminarId(BigInteger seminarId, BigInteger userId);

    /**
     * 用户（老师）在指定的课程下创建讨论课.
     *
     * @param seminar
     * @return seminarId 若创建成功返回创建的讨论课id，失败则返回-1
     */
    Integer insertSeminarByCourseId(Seminar seminar);

    /**
     * 按课程id获取讨论课.
     *
     * @param courseId
     * @return list 讨论课列表
     */
    List<Seminar> listSeminarByCourseId(BigInteger courseId);

    /**
     * 按讨论课id修改讨论课
     *
     * @param seminar
     * @return true修改成功 false修改失败
     */
    Integer updateSeminarBySeminarId(Seminar seminar);
}

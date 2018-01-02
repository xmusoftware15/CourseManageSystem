package xmu.crms.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Attendance;
import xmu.crms.entity.User;

import java.math.BigInteger;
import java.util.List;

/**
 * UserMapper
 * 仅获取登录所需要的用户信息
 *
 * @author YeHongjie
 * @date 2017-12-19
 */
@Component
public interface UserMapper {

	/**
	 * 通过用户id查找用户
	 * @param userId
	 * @return User
	 */
	User getUserByUserId(BigInteger userId);

	/**
	 *通过用户姓名查找用户.
	 * @param userName
	 * @return List<User>
	 */
	List<User> getUserByUserName(String userName);

	/**
	 * 添加出勤信息.
	 * @param attendance
	 * @return int
	 */
	int insertAttendanceById(Attendance attendance);

	/**
	 * 根据用户id修改用户信息.
	 * @param userId
	 * @param user
	 * @return int
	 */
	int updateUserByUserId(@Param("arg0")BigInteger userId,@Param("arg1") User user);

	/**
	 * 通过班级id获取班级学生列表.
	 * @param classId
	 * @param numBeginWith
	 * @param nameBeginWith
	 * @return List<User>
	 */
	List<User> getUserByClassId(BigInteger classId, String numBeginWith, String nameBeginWith);

	/**
	 * 获取班级出勤学生列表.
	 * @param classId
	 * @param seminarId
	 * @param status
	 * @return List<User>
	 */
	List<User> getAttendanceByIdAndStatus(BigInteger classId, BigInteger seminarId, Integer status);

	/**
	 * 获取出勤情况.
	 * @param classId
	 * @param seminarId
	 * @return List<Attendance>
	 */
	List<Attendance> getAttendanceById(BigInteger classId, BigInteger seminarId);

	/**
	 * 通过学工号和学校id获取用户信息.
	 * @param userNumber
	 * @param schoolId
	 * @return User
	 */
	User getUserByUserNumberAndSchool(String userNumber,BigInteger schoolId);

	/**
	 * 通过学工号获取用户.
	 * @param userNumber
	 * @return User
	 */
	User getUserByUserNumber(String userNumber);

	/**
	 * 通过openid获取用户.
	 * @param openid
	 * @return User
	 */
	User getUserByOpenId(String openid);

	/**
	 * 创建学生账户.
	 * @param user
	 * @return int
	 */
	int createStudentAccountByNumber(User user);
    
}

package xmu.crms.service;

import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;

import java.math.BigInteger;

/**
 * @author Huhui
 * @version 2.10
 */
public interface LoginService {
	
	/**
	 * 微信登录.
	 * <p>微信登录<br>
	 * @return user 该用户信息
	 * @exception UserNotFoundException 登录失败时抛出
	 */
	 User signInWeChat(User user) throws UserNotFoundException;
	
	
	/**
	 * 手机号登录.
	 * <p>手机号登录 (.Net使用),User中只有phone和password，用于判断用户名密码是否正确<br>
	 * @author qinlingyun 
	 * @param user 用户信息(手机号Phone和密码Password)
	 * @return user 该用户信息
	 * @exception UserNotFoundException 登录失败时抛出
	 */
	 User signInPhone(User user) throws UserNotFoundException;
	
	/**
	 * 手机号注册.
	 * <p>手机号注册 (.Net使用),User中只有phone和password，userId是注册后才有并且在数据库自增<br> 
	 * @author qinlingyun
	 * @param user 用户信息(手机号Phone和密码Password)
	 * @return user 该用户信息
	 */
	User signUpPhone(User user);
	
	/**
	 * 用户解绑.
	 * <p>教师解绑账号(j2ee使用)<br>
	 * @author qinlingyun
	 * @param userId 用户id
	 * @see CourseService#listCourseByUserId(BigInteger userId)
	 * @see CourseService#deleteCourseByCourseId(BigInteger courseId)
	 * @exception IllegalArgumentException 信息不合法，id格式错误
	 * @exception UserNotFoundException 未找到对应用户
	 */
	void deleteTeacherAccount(BigInteger userId) throws IllegalArgumentException,
            UserNotFoundException;
	
	 /**
	 * 用户解绑.
	 * <p>学生解绑账号(j2ee使用)<br>
	 * @author qinlingyun
	 * @param userId 用户id
	 * @see ClassService#deleteCourseSelectionById(BigInteger userId,BigInteger classId)
	 * @exception IllegalArgumentException 信息不合法，id格式错误
	 * @exception UserNotFoundException 未找到对应用户
	 */
	void deleteStudentAccount(BigInteger userId) throws IllegalArgumentException,
            UserNotFoundException;
	
	
}

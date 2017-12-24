package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import xmu.crms.entity.Attendance;
import xmu.crms.entity.User;

/**
 * UserMapper
 * 仅获取登录所需要的用户信息
 *
 * @author YeHongjie
 * @date 2017-12-19
 */
@Component
@Mapper
public interface UserMapper {

	User getUserByUserId(BigInteger userId);

	List<User> getUserByUserName(String userName);
	
	int insertAttendanceById(Attendance attendance);
	
	void updateUserByUserId(BigInteger userId, User user);
	
	List<User> getUserByClassId(BigInteger classId, String numBeginWith, String nameBeginWith);
	
	List<User> getAttendanceByIdAndStatus(BigInteger classId, BigInteger seminarId, Integer status);

	List<Attendance> getAttendanceById(BigInteger classId, BigInteger seminarId);
    
}

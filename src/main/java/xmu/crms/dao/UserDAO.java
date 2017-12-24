package xmu.crms.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xmu.crms.entity.Attendance;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.User;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.UserMapper;

/**
 * @author YeHongjie
 */
@Repository
public class UserDAO {
    @Autowired
    private UserMapper userMapper;

	public User getUserByUserId(BigInteger userId) throws UserNotFoundException {
		User user = null;
        try{
        	user = userMapper.getUserByUserId(userId);
        }catch (Exception e){
        	e.printStackTrace();
        }
        if(user==null)
        	throw new UserNotFoundException("No such user");
        return user;
	}

	public List<User> listUserByUserName(String userName) throws UserNotFoundException {
		List<User> users = null;
        try {
            users = userMapper.getUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(users==null||users.isEmpty())
        	throw new UserNotFoundException("No such user");
        return users;
	}
	
	public BigInteger insertAttendanceById(BigInteger classId, BigInteger seminarId, BigInteger userId,int attendanceStatus)
			throws ClassesNotFoundException, SeminarNotFoundException{
		Attendance attendance=new Attendance();
		ClassInfo classInfo=new ClassInfo();
		classInfo.setId(classId);
		Seminar seminar=new Seminar();
		seminar.setId(seminarId);
		User user=new User();
		user.setId(userId);
		
		attendance.setClassInfo(classInfo);
		attendance.setSeminar(seminar);
		attendance.setStudent(user);
		attendance.setAttendanceStatus(attendanceStatus);
		int ret=0;
		try{
			ret=userMapper.insertAttendanceById(attendance);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(ret!=1)
			throw new ClassesNotFoundException("Class not found");

		return attendance.getId();
	}
	
	public List<User> listUserByClassId(BigInteger classId, String numBeginWith, String nameBeginWith) throws ClassesNotFoundException{
 
		List<User> users=null;
		try{
			users=userMapper.getUserByClassId(classId, numBeginWith, nameBeginWith);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(users==null||users.isEmpty())
			throw new ClassesNotFoundException("Class not found");
		return users;
	}
	
	public void updateUserByUserId(BigInteger userId, User user) throws UserNotFoundException {
		int ret=0;
		try{
			ret=userMapper.updateUserByUserId(userId,user);
		}catch (Exception e){
			e.printStackTrace();
		}
		if(ret==0)
			throw new UserNotFoundException("No such user");
	}
	
	public List<Attendance> listAttendanceById(BigInteger classId, BigInteger seminarId){
		List<Attendance> attendances=null;
		attendances=userMapper.getAttendanceById(classId, seminarId);
		return attendances;
	}

	public List<User> listAttendanceByIdAndStatus(BigInteger classId, BigInteger seminarId, Integer status) {
		List<User> users=null;
		users=userMapper.getAttendanceByIdAndStatus(classId, seminarId,status);
		return users;
	}

}

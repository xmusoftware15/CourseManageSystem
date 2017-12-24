package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xmu.crms.dao.UserDAO;
import xmu.crms.entity.Attendance;
import xmu.crms.entity.Course;
import xmu.crms.entity.User;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.ClassService;
import xmu.crms.service.CourseService;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YeHongjie
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    private CourseService courseService;
    private ClassService classService;

    @Value("${wechat.mp.appid}")
    private String appid;

    @Value("${wechat.mp.secret-key}")
    private String secret;

    private static final String KEY_OPEN_ID = "openid";
    // private static final String KEY_SESSION_KEY = "session_key";

    private static final String KEY_ERR_CODE = "errcode";


	@Override
	public void insertAttendanceById(BigInteger classId, BigInteger seminarId, BigInteger userId, double longitude,
			double latitude) throws IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException {
		BigInteger recordId=null;
		recordId=userDAO.insertAttendanceById(classId, seminarId, userId, 0);
	}

	@Override
	public List<Attendance> listAttendanceById(BigInteger classId, BigInteger seminarId)
			throws IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException {
		List<Attendance> attendances=null;
		attendances=userDAO.listAttendanceById(classId, seminarId);
		if(attendances==null) {
			throw new ClassesNotFoundException();
		}
		for(Attendance attendance:attendances)
		{
			BigInteger studentId=attendance.getStudent().getId();
			//System.out.println(studentId);
			User student=null;
			try {
				student = getUserByUserId(studentId);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}
			//System.out.println(student);
			attendance.setStudent(student);
		}
		return attendances;
	}

	@Override
	public User getUserByUserId(BigInteger userId) throws IllegalArgumentException, UserNotFoundException {
		
		User val=null;
		try{
			val = userDAO.getUserByUserId(userId);
		}catch (Exception e){
			e.printStackTrace();
		}
		if(val==null) {
			throw new UserNotFoundException();
		}
		return val;
	}

	@Override
	public List<BigInteger> listUserIdByUserName(String userName)
			throws IllegalArgumentException, UserNotFoundException {
		
		List<User> users=null;
		List<BigInteger> val=new ArrayList<>();
		users = userDAO.listUserByUserName(userName);
		if(users==null) {
			throw new UserNotFoundException();
		}
		for(User u:users) {
			val.add(u.getId());
		}
		return val;
	}

	@Override
	public List<Course> listCourseByTeacherName(String teacherName) {
		
		List<Course> val=new ArrayList<Course>();
		List<BigInteger> teacherIds=null;
		try {
			teacherIds = listUserIdByUserName(teacherName);
		} catch (IllegalArgumentException | UserNotFoundException e) {
			e.printStackTrace();
		}
		for(BigInteger id:teacherIds)
			try {
				val.addAll(courseService.listCourseByUserId(id));
			} catch (IllegalArgumentException | CourseNotFoundException e) {
				e.printStackTrace();
			}
		return val;
	}

	@Override
	public List<User> listUserByUserName(String userName) throws UserNotFoundException {
		
		List<User> users=null;
		users = userDAO.listUserByUserName(userName);
		if(users==null) {
			throw new UserNotFoundException();
		}
		return users;
	}
	
	@Override
	public void updateUserByUserId(BigInteger userId, User user) throws UserNotFoundException {
		
		userDAO.updateUserByUserId(userId,user);
		
	}

	@Override
	public List<User> listUserByClassId(BigInteger classId, String numBeginWith, String nameBeginWith)
			throws IllegalArgumentException, ClassesNotFoundException {
		List<User> users=null;
		if(numBeginWith==null) {
			numBeginWith = "";
		}
		if(nameBeginWith==null) {
			nameBeginWith = "";
		}
		users=userDAO.listUserByClassId(classId, numBeginWith, nameBeginWith);
		return users;
	}

	@Override
	public List<User> listPresentStudent(BigInteger seminarId, BigInteger classId) 
			throws IllegalArgumentException{
		final Integer statusPresent=0;
		List<User> students=userDAO.listAttendanceByIdAndStatus(classId, seminarId,statusPresent);
		return students;
	}

	@Override
	public List<User> listAbsenceStudent(BigInteger seminarId, BigInteger classId) 
			throws IllegalArgumentException{
		final Integer statusAbsence=2;
		List<User> students=userDAO.listAttendanceByIdAndStatus(classId, seminarId,statusAbsence);
		return students;
	}

	@Override
	public List<User> listLateStudent(BigInteger seminarId, BigInteger classId)
			throws IllegalArgumentException{
		
		final Integer statusLate=1;
		List<User> students=userDAO.listAttendanceByIdAndStatus(classId, seminarId,statusLate);
		return students;
	}

    
}

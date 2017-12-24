package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.School;
import xmu.crms.entity.User;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.SchoolService;
import xmu.crms.service.UserService;
import xmu.crms.vo.UserIdNameVO;
import xmu.crms.vo.UserVO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 登录控制器。微信小程序登录与 Web 端账号密码登录
 *
 * @author YeHongjie
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SchoolService schoolService;


    @GetMapping("/me")
    public UserVO getUserByUserId(@RequestAttribute("userId") BigInteger id1) throws UserNotFoundException {
        User user=null;
        School school = null;
        UserVO userVO=null;
        user=userService.getUserByUserId(id1);
        school=schoolService.getSchoolBySchoolId(user.getSchool().getId());
        user.setSchool(school);
        if(user==null)
        	throw new UserNotFoundException();
        else
        	userVO=new UserVO(user);
        return userVO;
    }
    
    @GetMapping("/list")
    public List<User> listUserByName() throws IllegalArgumentException, UserNotFoundException
    {
    	List<User> list=null;
    	list=userService.listUserByUserName("学生1");
    	return list;
    }
    
    @PutMapping("/me")
    public void updateUserByUserId(@RequestAttribute("userId") BigInteger id1, @RequestBody Map<String, String> map) throws IllegalArgumentException,UserNotFoundException
    {
    	User user=new User();
    	user.setName(map.get("name"));
    	user.setNumber(map.get("number"));
    	user.setEmail(map.get("email"));
    	//user.setTitle(map.get("title"));
    	String gender=map.get("gender");
    	if(gender.equals("male"))
    		user.setGender(0);
    	else
    		user.setGender(1);
    	user.setAvatar(map.get("avatar"));
    	userService.updateUserByUserId(id1, user);
    }

    
   /* @GetMapping("/attendance")
    public List<AttendanceVO> listAttendanceById() throws 
    	IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException, UserNotFoundException
    {
    	List<Attendance> attendances=null;
    	List<AttendanceVO> attendanceVOs=new ArrayList<AttendanceVO>();
    	attendances=userService.listAttendanceById(BigInteger.valueOf(1), BigInteger.valueOf(3));
    	if(attendances==null)
    		throw new ClassesNotFoundException();
    	for(Attendance attendance:attendances)
    	{
    		AttendanceVO temp=new AttendanceVO();
    		temp.setId(attendance.getId());
    		temp.setStudent(attendance.getStudent());
    		temp.setAttendanceStatus(attendance.getAttendanceStatus());
    		attendanceVOs.add(temp);
    	}
    	return attendanceVOs;
    }*/
    
    @GetMapping("/attendance/late")
    public List<UserIdNameVO> listLateStudent() throws IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException, UserNotFoundException
    {
    	List<User> users=null;
    	List<UserIdNameVO> userVOs=new ArrayList<UserIdNameVO>();
    	users=userService.listLateStudent(BigInteger.valueOf(3), BigInteger.valueOf(1));
    	for(User user:users)
		{
			UserIdNameVO userVO=new UserIdNameVO(user);	
	        userVOs.add(userVO);
		}
    	return userVOs;
    }
    
    @GetMapping("/attendance/present")
    public List<UserIdNameVO> listPresentStudent() throws IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException, UserNotFoundException
    {
    	List<User> users=null;
    	List<UserIdNameVO> userVOs=new ArrayList<UserIdNameVO>();
    	users=userService.listPresentStudent(BigInteger.valueOf(3), BigInteger.valueOf(1));
    	for(User user:users)
		{
			UserIdNameVO userVO=new UserIdNameVO(user);	
	        userVOs.add(userVO);
		}
    	return userVOs;
    }
    
    @GetMapping("/attendance/absence")
    public List<UserIdNameVO> listAbsenceStudent() throws IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException, UserNotFoundException
    {
    	List<User> users=null;
    	List<UserIdNameVO> userVOs=new ArrayList<UserIdNameVO>();
    	users=userService.listAbsenceStudent(BigInteger.valueOf(3), BigInteger.valueOf(1));
    	for(User user:users)
		{
			UserIdNameVO userVO=new UserIdNameVO(user);	
	        userVOs.add(userVO);
		}
    	return userVOs;
    }
    
    @PostMapping("attendance")
    public BigInteger insertAttendanceById(@RequestBody Map<String, String> map) throws NumberFormatException, IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException, UserNotFoundException
    {
    	String latitude=map.get("latitude");
    	String longtitude=map.get("longitude");
    	double la=Double.parseDouble(latitude);
    	double lo=Double.parseDouble(longtitude);
    	BigInteger recordId=null;
    	recordId=userService.insertAttendanceById(BigInteger.valueOf(1), BigInteger.valueOf(3), BigInteger.valueOf(76), lo, la);
    	return recordId;
    }

    @ExceptionHandler(ClassesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object classesNotFound() {
        return null;
    }
    
    @ExceptionHandler(SeminarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object seminarNotFound() {
        return null;
    }
    
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object courseNotFound() {
        return null;
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object userNotFound() {
        return null;
    }
    
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object numberForamt() {
        return null;
    }
}

package xmu.crms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xmu.crms.entity.User;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.impl.UserServiceImpl;
import xmu.crms.vo.UserIdNameVO;

/**
 * @author 3-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CrmsApplication.class)
public class UserTests {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void getUser(){
    	System.out.println("------getUserByUserId------");
        User user=null;
        for(int i = 1; i < 10; i++){
            try {
				user = userService.getUserByUserId(BigInteger.valueOf(i));
			} catch (IllegalArgumentException | UserNotFoundException e) {
				e.printStackTrace();
			}
            System.out.println(user);
        }
    }
    
    @Test
    public void getUserByNum(){
    	System.out.println("------getUserByUserNumber------");
        User user=null;
        try {
        	user = userService.getUserByUserNumber("20170315");
    		System.out.println(user);
		} catch (IllegalArgumentException | UserNotFoundException e) {
			e.printStackTrace();
        }
    }
    
    /*@Test
    public void setUser(){
    	System.out.println("------updateUserByUserId------");
    	User user=new User();
        user.setName("邱明");
        user.setNumber("20170315");
        user.setEmail("20170315@xmu.edu.cn");
        user.setGender(0);
        user.setTitle(1);
        user.setAvatar(null);
        try {
			userService.updateUserByUserId(BigInteger.valueOf(1), user);
			User user2=userService.getUserByUserId(BigInteger.valueOf(1));
			System.out.println(user2);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
    }*/
    
    @Test
    public void getUserByName(){
    	System.out.println("------listUserByUserName------");
    	List<User> users=new ArrayList<User>();
    	try {
			users=userService.listUserByUserName("学生1");
			System.out.println(users);
		} catch (UserNotFoundException e) {
			//e.printStackTrace();
		}
    }

    @Test
    public void getUserAttendance(){
    	System.out.println("------listAbsence------");
    	List<User> users=new ArrayList<User>();
    	users=userService.listAbsenceStudent(BigInteger.valueOf(3), BigInteger.valueOf(1));
		for(User u:users)
			System.out.println(new UserIdNameVO(u));
    	
		System.out.println("------listLate------");
		users=userService.listLateStudent(BigInteger.valueOf(3), BigInteger.valueOf(1));
		for(User u:users)
			System.out.println(new UserIdNameVO(u));
    	
		System.out.println("------listPresent------");
		users=userService.listPresentStudent(BigInteger.valueOf(3), BigInteger.valueOf(1));
		for(User u:users)
			System.out.println(new UserIdNameVO(u));
    }
    
    @Test
    public void getStudentList()
    {
    	System.out.println("------listUserByClassId------");
    	List<User> users=new ArrayList<User>();
    	try {
			users=userService.listUserByClassId(BigInteger.valueOf(1), "2432015220271", null);
			if(users!=null)
				for(User u:users)
					System.out.println(u.getId()+" "+u.getName()+" "+u.getNumber());
			else
				System.out.println("null");
    	} catch (IllegalArgumentException | ClassesNotFoundException e) {
			e.printStackTrace();
		}
    }
}

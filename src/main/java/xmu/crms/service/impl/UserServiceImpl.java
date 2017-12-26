package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.UserDAO;
import xmu.crms.entity.*;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.ClassService;
import xmu.crms.service.CourseService;
import xmu.crms.service.SchoolService;
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
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassService classService;
@Autowired
private SchoolService schoolService;
    @Override
    public BigInteger insertAttendanceById(BigInteger classId, BigInteger seminarId, BigInteger userId, double longitude,
                                           double latitude) throws IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException {
        BigInteger recordId = null;
        double eps = 0.05;
        try {
            Location tmp = classService.getCallStatusById(seminarId, classId);

            if (Math.abs(tmp.getLatitude() - latitude) > eps
                    || Math.abs(tmp.getLongitude() - longitude) > eps) {
                throw new ClassesNotFoundException("Calling failed.");
            } else if (tmp.getStatus() == 0) {
                //正常签到
                recordId = userDAO.insertAttendanceById(classId, seminarId, userId, 0);
            } else {//迟到
                recordId = userDAO.insertAttendanceById(classId, seminarId, userId, 1);
            }
        } catch (ClassesNotFoundException | SeminarNotFoundException e) {
            throw e;
        }
        return recordId;
    }

    @Override
    public List<Attendance> listAttendanceById(BigInteger classId, BigInteger seminarId)
            throws IllegalArgumentException, ClassesNotFoundException, SeminarNotFoundException {
        List<Attendance> attendances = null;
        attendances = userDAO.listAttendanceById(classId, seminarId);
        if (attendances == null) {
            throw new ClassesNotFoundException();
        }
        for (Attendance attendance : attendances) {
            BigInteger studentId = attendance.getStudent().getId();
            //System.out.println(studentId);
            User student = null;
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

        User val = null;
        try {
            val = userDAO.getUserByUserId(userId);
        } catch (UserNotFoundException e) {
            throw e;
        }
        return val;
    }

    @Override
    public List<BigInteger> listUserIdByUserName(String userName)
            throws IllegalArgumentException, UserNotFoundException {

        List<User> users = null;
        List<BigInteger> val = new ArrayList<>();
        try {
            users = userDAO.listUserByUserName(userName);
        } catch (UserNotFoundException e) {
            throw e;
        }
        for (User u : users) {
            val.add(u.getId());
        }
        return val;
    }

    @Override
    public List<Course> listCourseByTeacherName(String teacherName) {

        List<Course> val = new ArrayList<Course>();
        List<BigInteger> teacherIds = null;
        try {
            teacherIds = listUserIdByUserName(teacherName);
        } catch (IllegalArgumentException | UserNotFoundException e) {
            e.printStackTrace();
        }
        for (BigInteger id : teacherIds) {
            try {
                val.addAll(courseService.listCourseByUserId(id));
            } catch (IllegalArgumentException | CourseNotFoundException e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    @Override
    public void createStudentAccountByNumber(User user) {
        userDAO.createStudentAccountByNumber(user);
    }

    @Override
    public List<User> listUserByUserName(String userName) throws UserNotFoundException {

        List<User> users = null;
        try {
            users = userDAO.listUserByUserName(userName);
        } catch (UserNotFoundException e) {
            throw e;
        }
        if (users == null){
            throw new UserNotFoundException();}
        return users;
    }

    @Override
    public void updateUserByUserId(BigInteger userId, User user) throws UserNotFoundException {

        try {
            userDAO.updateUserByUserId(userId, user);
        } catch (UserNotFoundException e) {
            throw e;
        }
    }

    @Override
    public List<User> listUserByClassId(BigInteger classId, String numBeginWith, String nameBeginWith)
            throws IllegalArgumentException, ClassesNotFoundException {
        List<User> users = null;
        if (numBeginWith == null) {
            numBeginWith = "";
        }
        if (nameBeginWith == null) {
            nameBeginWith = "";
        }
        try {
            users = userDAO.listUserByClassId(classId, numBeginWith, nameBeginWith);
        } catch (ClassesNotFoundException e) {
            throw e;
        }
        return users;
    }

    @Override
    public List<User> listPresentStudent(BigInteger seminarId, BigInteger classId)
            throws IllegalArgumentException {
        final Integer statusPresent = 0;
        List<User> students = userDAO.listAttendanceByIdAndStatus(classId, seminarId, statusPresent);
        return students;
    }

    @Override
    public List<User> listAbsenceStudent(BigInteger seminarId, BigInteger classId)
            throws IllegalArgumentException {
        final Integer statusAbsence = 2;
        List<User> students = userDAO.listAttendanceByIdAndStatus(classId, seminarId, statusAbsence);
        return students;
    }

    @Override
    public List<User> listLateStudent(BigInteger seminarId, BigInteger classId)
            throws IllegalArgumentException {

        final Integer statusLate = 1;
        List<User> students = userDAO.listAttendanceByIdAndStatus(classId, seminarId, statusLate);
        return students;
    }

	@Override
	public User getUserByUserNumber(String userNumber) throws IllegalArgumentException, UserNotFoundException {
        User val = null;
        try {
            val = userDAO.getUserByUserNumber(userNumber);
        } catch (UserNotFoundException e) {
            throw e;
        }
        return val;
	}
    @Override
    public User getUserByUserNumberAndSchool(String userNumber,BigInteger schoolId) throws IllegalArgumentException, UserNotFoundException {
        User val = null;
        try {
            val = userDAO.getUserByUserNumberAndSchool(userNumber,schoolId);
        } catch (UserNotFoundException e) {
            throw e;
        }
        return val;
    }

}

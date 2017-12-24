package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.FixGroupDao;
import xmu.crms.dao.FixGroupMemberDAO;
import xmu.crms.dao.FixGroupTopicDAO;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.SeminarService;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Data: Created in 2017/12/21
 */
@Service
public class FixGroupServiceImpl implements FixGroupService {
    @Autowired
    private FixGroupDao fixGroupDao;

    @Autowired
    private SeminarGroupService seminarGroupService;

    @Autowired
    private FixGroupMemberDAO fixGroupMemberDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private FixGroupTopicDAO fixGroupTopicDAO;

    @Autowired
    private SeminarService seminarService;

    @Override
    public BigInteger insertFixGroupByClassId(BigInteger classId, BigInteger userId) throws
            IllegalArgumentException,ClassesNotFoundException,UserNotFoundException {
        if (classId.intValue() <= 0) {
            throw new IllegalArgumentException("classId");
        }
        if (userId.intValue() <= 0) {
            throw new IllegalArgumentException("userId");
        }
        BigInteger addRow = fixGroupDao.insertFixGroupByClassId(classId,userId);
        return addRow;
    }

    @Override
    public void deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if(fixGroupId.intValue()<=0){
            throw new IllegalArgumentException();
        }
        fixGroupDao.deleteFixGroupMemberByFixGroupId(fixGroupId);
    }

    @Override
    public List<User> listFixGroupMemberByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if (groupId.intValue() <= 0) {
            throw new IllegalArgumentException();
        } else {
            List<User> users = fixGroupDao.listFixGroupMemberByGroupId(groupId);
            return users;
        }
    }

    @Override
    public List<FixGroup> listFixGroupByClassId(BigInteger classId) throws IllegalArgumentException {
        if (classId.intValue() <= 0) {
            throw new IllegalArgumentException();
        } else {
            List<FixGroup> fixGroups = fixGroupDao.listFixGroupByClassId(classId);
            return fixGroups;
        }
    }

    @Override
    public void deleteFixGroupByClassId(BigInteger classId) throws IllegalArgumentException, ClassesNotFoundException {
        if (classId.intValue() <= 0) {
            throw new IllegalArgumentException();
        } else {
            fixGroupDao.deleteFixGroupByClassId(classId);
        }
    }

    @Override
    public void deleteFixGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if (groupId.intValue() <= 0) {
            throw new IllegalArgumentException();
        } else {
            fixGroupDao.deleteFixGroupByGroupId(groupId);
        }
    }

    @Override
    public void updateFixGroupByGroupId(BigInteger groupId, FixGroup fixGroup) throws IllegalArgumentException, FixGroupNotFoundException {
        if (groupId.intValue() <= 0) {
            throw new IllegalArgumentException("groupId");
        } else {
            fixGroupDao.updateFixGroupByGroupId(groupId, fixGroup);
        }
    }

    @Override
    public BigInteger insertStudentIntoGroup(BigInteger userId, BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException, InvalidOperationException {
        if (userId.intValue() <= 0 || groupId.intValue() <= 0) {
            throw new IllegalArgumentException();
        } else {
            BigInteger addRow = fixGroupDao.insertStudnetIntoGroup(userId, groupId);
            return addRow;
        }
    }

    @Override
    public FixGroup getFixedGroupById(BigInteger userId, BigInteger classId) throws IllegalArgumentException, ClassesNotFoundException, UserNotFoundException {
        if (userId.intValue() <= 0 || classId.intValue() <= 0) {
            throw new IllegalArgumentException();
        } else {
            FixGroup fixGroup = fixGroupDao.getFixedGroupById(userId, classId);
            return fixGroup;
        }
    }

    @Override
    public void updateSeminarGroupById(BigInteger groupId, SeminarGroup group) throws IllegalArgumentException, FixGroupNotFoundException {
        if (groupId.intValue() <= 0) {
            throw new IllegalArgumentException();
        } else {
            fixGroupDao.updateSeminarGroupById(groupId, group);
        }
    }

    @Override
    public void deleteFixGroupUserById(BigInteger fixGroupId, BigInteger userId) throws IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException {
        if (fixGroupId.intValue() <= 0) {
            throw new IllegalArgumentException("fixGroupId");
        }
        if (userId.intValue() <= 0) {
            throw new IllegalArgumentException("userId");
        }
        FixGroup fixGroup = fixGroupDao.getFixGroupByGroupId(fixGroupId);
        if (null == fixGroup) {
            throw new FixGroupNotFoundException();
        }
        List<User> members = listFixGroupMemberByGroupId(fixGroupId);
        User student = userService.getUserByUserId(userId);
        boolean contains = false;
        Iterator<User> iterator = members.iterator();
        while (iterator.hasNext()) {
            User member = iterator.next();
            if (member.getId().equals(userId)) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            throw new UserNotFoundException();
        }
        FixGroupMember fixGroupMember = new FixGroupMember();
        fixGroupMember.setFixGroup(fixGroup);
        fixGroupMember.setStudent(student);
        fixGroupMemberDAO.deleteStudentFromFixGroup(fixGroupMember);
    }

    @Override
    public List<FixGroupMember> listFixGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if (groupId.intValue() <= 0) {
            throw new IllegalArgumentException("groupId");
        }
        FixGroup fixGroup = fixGroupDao.getFixGroupByGroupId(groupId);
        if (null == fixGroup) {
            throw new FixGroupNotFoundException();
        }
        return fixGroupMemberDAO.listFixGroupMemberByFixGroup(fixGroup);
    }

    @Override
    public void fixedGroupToSeminarGroup(BigInteger semianrId, BigInteger fixedGroupId) throws IllegalArgumentException, FixGroupNotFoundException, SeminarNotFoundException {
        if (semianrId.intValue() <= 0) {
            throw new IllegalArgumentException("seminarId");
        }
        if (fixedGroupId.intValue() <= 0) {
            throw new IllegalArgumentException("fixGroupId");
        }
        //判断当前时间与seminar时间是否相同
        //1. 获取seminar信息
        //2. 对比seminar时间与当前时间
        Seminar seminar = seminarService.getSeminarBySeminarId(semianrId);
        Calendar calendar = Calendar.getInstance();
        Date current = calendar.getTime();
        if (current.after(seminar.getStartTime())) {
            throw new IllegalArgumentException("seminar has started.");
        }
        //将fixGroup的信息复制一份到seminarGroup
        FixGroup fixGroup = fixGroupDao.getFixGroupByGroupId(fixedGroupId);
        SeminarGroup seminarGroup = new SeminarGroup();
        seminarGroup.setLeader(fixGroup.getLeader());
        seminarGroup.setClassInfo(fixGroup.getClassInfo());
        BigInteger seminarGroupId = seminarGroupService.insertSeminarGroupBySeminarId(semianrId, fixGroup.getClassInfo().getId(), seminarGroup);
        seminarGroup.setId(seminarGroupId);
        //将fixGroupMember的信息复制一份到seminarGroupMember
        List<User> members = listFixGroupMemberByGroupId(fixedGroupId);
        Iterator<User> iterator = members.iterator();
        while (iterator.hasNext()) {
            User member = iterator.next();
            try {
                seminarGroupService.insertSeminarGroupMemberById(member.getId(), seminarGroupId);
            } catch (Exception e) {

            }
        }
        //将fixGroupTopic信息复制到seminarGroupTopic中
        List<FixGroupTopic> fixGroupTopics = fixGroupTopicDAO.listFixGroupTopicByFixGroup(fixGroup);
        Iterator<FixGroupTopic> topicIterator = fixGroupTopics.iterator();
        while (topicIterator.hasNext()) {
            FixGroupTopic fixGroupTopic = topicIterator.next();
            try {
                seminarGroupService.insertTopicByGroupId(seminarGroupId, fixGroupTopic.getTopic().getId());
                fixGroupTopicDAO.deleteFixGroupTopicById(fixGroupTopic);
            } catch (Exception e) {

            }
        }
    }
}

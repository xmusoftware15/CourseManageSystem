package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.FixGroupNotFoundException;
import xmu.crms.exception.InvalidOperationException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.FixGroupMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author: guiyu
 * @time: 2017/12/19 19:44
 * @description:
 */
@Component
public class FixGroupDao {

    @Autowired
    private FixGroupMapper fixGroupMapper;


    /**
     * @param fixGroupId
     * @return
     */
    public boolean deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId) throws FixGroupNotFoundException {
        FixGroup fixGroup = getFixGroupByGroupId(fixGroupId);
        if(fixGroup==null){
            throw new FixGroupNotFoundException();
        }
        fixGroupMapper.deleteFixGroupMemberByFixGroupId(fixGroupId);
        return true;
    }

    /**
     * @param userId
     * @param groupId
     * @return
     */
    public BigInteger insertFixGroupMemberById(BigInteger userId, BigInteger groupId) throws FixGroupNotFoundException, InvalidOperationException, UserNotFoundException {

        //查询数据库中是否有该小组
        FixGroup fixGroup = fixGroupMapper.getFixGroupById(groupId);
        if (fixGroup == null) {
            throw new FixGroupNotFoundException();
        } else {
            BigInteger courseSelectionId = fixGroupMapper.getCourseSelectionId(fixGroup.getClassInfo().getId(), userId);
            if (courseSelectionId == null) {
                //不存在该学生
                throw new UserNotFoundException();
            } else {
                BigInteger studentId = fixGroupMapper.getFixGroupMemberById(fixGroup.getClassInfo().getId(), userId);
                if (studentId != null) {
                    //待添加学生已经在小组里了
                    throw new InvalidOperationException();
                } else {
                    //添加记录
                    FixGroupMember fixGroupMember = new FixGroupMember();
                    FixGroup nfixGroup = new FixGroup();
                    nfixGroup.setId(groupId);
                    User student = new User();
                    student.setId(userId);
                    fixGroupMember.setFixGroup(nfixGroup);
                    fixGroupMember.setStudent(student);
                    int addNum = fixGroupMapper.insertFixGroupMember(fixGroupMember);
                    if (addNum > 0) {
                        return fixGroupMember.getId();
                    }else{
                        return new BigInteger("-1");
                    }
                }
            }
        }
    }

    /**
     * @param groupId
     * @return
     */
    public List<User> listFixGroupMemberByGroupId(BigInteger groupId) {
        List<User> users = fixGroupMapper.listFixGroupMemberByGroupId(groupId);
        return users;
    }

    /**
     * @param classId
     * @return
     */
    public List<FixGroup> listFixGroupByClassId(BigInteger classId) {
        List<FixGroup> fixGroups = fixGroupMapper.listFixGroupByClassId(classId);
        return fixGroups;
    }


    /**
     * @param classId
     * @return
     * @throws ClassesNotFoundException
     */
    public boolean deleteFixGroupByClassId(BigInteger classId) throws ClassesNotFoundException {
        ClassInfo classInfo = fixGroupMapper.getClassInfoById(classId);
        if (classInfo == null) {
            //未找到班级
            throw new ClassesNotFoundException();
        } else {
            List<FixGroup> fixGroups = fixGroupMapper.listFixGroupByClassId(classId);
            for (FixGroup fixGroup : fixGroups) {
                fixGroupMapper.deleteFixGroupByGroupId(fixGroup.getId());
            }
            return true;
        }
    }

    /**
     * @param groupId
     * @return
     * @throws FixGroupNotFoundException
     */
    public boolean deleteFixGroupByGroupId(BigInteger groupId) throws FixGroupNotFoundException {
        FixGroup fixGroup = fixGroupMapper.getFixGroupById(groupId);
        if (fixGroup == null) {
            //未找到小组
            throw new FixGroupNotFoundException();
        } else {
            fixGroupMapper.deleteFixGroupByGroupId(groupId);
            return true;
        }
    }

    /**
     * @param groupId
     * @param fixGroup
     * @return
     * @throws FixGroupNotFoundException
     */
    public Boolean updateFixGroupByGroupId(BigInteger groupId, FixGroup fixGroup) throws FixGroupNotFoundException {
        fixGroup.setId(groupId);
        FixGroup group = fixGroupMapper.getFixGroupById(groupId);
        if (group == null) {
            //未找到小组
            throw new FixGroupNotFoundException();
        } else {
            fixGroupMapper.updateFixGroupByGroupId(fixGroup);
            return true;
        }
    }


    /**
     * @param groupId
     * @return
     */
    public FixGroup getFixGroupByGroupId(BigInteger groupId) {
        FixGroup fixGroup = fixGroupMapper.getFixGroupById(groupId);
        return fixGroup;
    }

    /**
     * @param userId
     * @param groupId
     * @return
     * @throws UserNotFoundException
     * @throws FixGroupNotFoundException
     * @throws InvalidOperationException
     */
    public BigInteger insertStudnetIntoGroup(BigInteger userId, BigInteger groupId) throws UserNotFoundException, FixGroupNotFoundException, InvalidOperationException {
        BigInteger addRow = this.insertFixGroupMemberById(userId, groupId);
        return addRow;
    }

    /**
     * @param userId
     * @param classId
     * @return
     * @throws ClassesNotFoundException
     * @throws UserNotFoundException
     */
    public FixGroup getFixedGroupById(BigInteger userId, BigInteger classId) throws ClassesNotFoundException, UserNotFoundException {
        ClassInfo classInfo = fixGroupMapper.getClassInfoById(classId);
        if (classInfo == null) {
            //班级不存在
            throw new ClassesNotFoundException();
        } else {
            User user = fixGroupMapper.getUserById(userId);
            if (user == null) {
                //学生不存在
                throw new UserNotFoundException();
            } else {
                FixGroup fixGroup = fixGroupMapper.getFixedGroupById(userId, classId);
                return fixGroup;
            }
        }
    }


    /**
     * @param groupId
     * @param group
     * @return
     * @throws FixGroupNotFoundException
     */
    public Boolean updateSeminarGroupById(BigInteger groupId, SeminarGroup group) throws FixGroupNotFoundException {
        SeminarGroup seminarGroup = fixGroupMapper.getSeminarGroupById(groupId);
        if (seminarGroup == null) {
            //该小组不存在
            throw new FixGroupNotFoundException();
        } else {
            group.setId(groupId);
            fixGroupMapper.updateSeminarGroup(group);
            return true;
        }
    }

    /**
     * @param classId
     * @param userId
     * @return
     * @throws ClassesNotFoundException
     */
    public BigInteger insertFixGroupByClassId(BigInteger classId, BigInteger userId) throws ClassesNotFoundException {
        ClassInfo classInfo = fixGroupMapper.getClassInfoById(classId);
        if (classInfo == null) {
            //未找到班级
            throw new ClassesNotFoundException();
        }
        ClassInfo addClass = new ClassInfo();
        addClass.setId(classId);
        User user = new User();
        user.setId(userId);
        FixGroup fixGroup = new FixGroup();
        int a = fixGroupMapper.insertFixGroup(fixGroup);
        if (a > 0) {
            return fixGroup.getId();
        } else {
            return new BigInteger("-1");
        }
    }
}

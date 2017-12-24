package xmu.crms.mapper;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Data: Created in 2017/12/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FixGroupMapperTest {
    @Autowired
    private FixGroupMapper fixGroupMapper;

    @Test
    @Ignore
    public void testGetFixGroupById() {
        FixGroup fixGroup = fixGroupMapper.getFixGroupById(new BigInteger("19"));
        System.out.println(fixGroup);
    }

    @Test
    @Ignore
    public void testListFixGroupByClassId() {
        List<FixGroup> fixGroups = fixGroupMapper.listFixGroupByClassId(new BigInteger("1"));
        for (FixGroup fixGroup : fixGroups) {
            System.out.println(fixGroup);
        }
    }

    @Test
    public void testGetFixedGroupById() {
        FixGroup fixGroup = fixGroupMapper.getFixedGroupById(new BigInteger("8"), new BigInteger("1"));
        System.out.println(fixGroup);
    }

    @Test
    public void testDeleteFixGroupMemberByFixGroupId() {
        fixGroupMapper.deleteFixGroupMemberByFixGroupId(new BigInteger("21"));
    }

    @Test
    @Ignore
    public void listFixGroupMemberByGroupIdTest() {
        List<User> users = fixGroupMapper.listFixGroupMemberByGroupId(new BigInteger("1"));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Ignore
    public void getFixGroupMemberByIdTest() {
        BigInteger bigInteger = fixGroupMapper.getFixGroupMemberById(new BigInteger("1"), new BigInteger("3"));
        System.out.println(bigInteger);
    }

    @Test
    @Ignore
    public void getCourseSelectionIdTest() {
        BigInteger bigInteger = fixGroupMapper.getCourseSelectionId(new BigInteger("1"), new BigInteger("90"));
        System.out.println(bigInteger);
    }

    @Test
    @Ignore
    public void insertFixGroupMemberTest() {
        FixGroupMember fixGroupMember = new FixGroupMember();
        FixGroup fixGroup = new FixGroup();
        fixGroup.setId(new BigInteger("32"));
        User student = new User();
        student.setId(new BigInteger("91"));
        fixGroupMember.setFixGroup(fixGroup);
        fixGroupMember.setStudent(student);
        int c = fixGroupMapper.insertFixGroupMember(fixGroupMember);
        System.out.println(fixGroupMember.getId());
    }

    @Test
    @Ignore
    public void getClassInfoByIdTest() {
        ClassInfo classInfo = fixGroupMapper.getClassInfoById(new BigInteger("3"));
        System.out.println(classInfo);
    }

    @Test
    public void deleteFixGroupByGroupIdTest() {
        fixGroupMapper.deleteFixGroupByGroupId(new BigInteger("1"));
    }

    @Test
    @Ignore
    public void updateFixGroupByGroupIdTest() {
        FixGroup fixGroup = new FixGroup();
        fixGroup.setId(new BigInteger("19"));
        ClassInfo classInfo = new ClassInfo();
        classInfo.setId(new BigInteger("3"));
        User user = new User();
        user.setId(new BigInteger("9990"));
        fixGroup.setClassInfo(classInfo);
        fixGroup.setLeader(user);
        fixGroupMapper.updateFixGroupByGroupId(fixGroup);
    }

    @Test
    @Ignore
    public void getUserByIdTest() {
        User user = fixGroupMapper.getUserById(new BigInteger("989"));
        System.out.println(user);
    }

    @Test
    public void updateSeminarGroupTest() {
        Seminar seminar = new Seminar();
        seminar.setId(new BigInteger("3"));
        ClassInfo classInfo = new ClassInfo();
        classInfo.setId(new BigInteger("2"));
        SeminarGroup seminarGroup = new SeminarGroup();
        seminarGroup.setId(new BigInteger("36"));
        seminarGroup.setClassInfo(classInfo);
        seminarGroup.setSeminar(seminar);
        int a = fixGroupMapper.updateSeminarGroup(seminarGroup);
        System.out.println(a);
    }

    @Test
    @Ignore
    public void getSeminarGroupByIdTest(){
        SeminarGroup seminarGroup = fixGroupMapper.getSeminarGroupById(new BigInteger("38"));
        System.out.println(seminarGroup);
    }

    @Test
    @Ignore
    public void insertFixGroupTest(){
        FixGroup fixGroup = new FixGroup();
        ClassInfo classInfo = new ClassInfo();
        User user = new User();
        classInfo.setId(new BigInteger("3"));
        user.setId(new BigInteger("9993"));
        fixGroup.setLeader(user);
        fixGroup.setClassInfo(classInfo);
        int a = fixGroupMapper.insertFixGroup(fixGroup);
        System.out.println(a);
        System.out.println(fixGroup.getId());
    }
}

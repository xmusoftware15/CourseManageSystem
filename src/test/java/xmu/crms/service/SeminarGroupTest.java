package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.SeminarGroupMember;
import xmu.crms.entity.User;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.InvalidOperationException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;

import java.math.BigInteger;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarGroupTest {
    @Autowired
    SeminarGroupService seminarGroupService;
@Test
public void deleteSeminarGroupMemberBySeminarGroupId(){
    seminarGroupService.deleteSeminarGroupMemberBySeminarGroupId(new BigInteger("5"));
}
    @Test
    public void insertSeminarGroupMemberById(){
        try {
            seminarGroupService.insertSeminarGroupMemberById(new BigInteger("5"),new BigInteger("5"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void listSeminarGroupMemberByGroupId(){
        try {
            seminarGroupService.listSeminarGroupMemberByGroupId(new BigInteger("1"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void listSeminarGroupIdByStudentId(){
        seminarGroupService.listSeminarGroupIdByStudentId(new BigInteger("1"));
    }
    @Test
    public void getSeminarGroupLeaderByGroupId(){
        try {
            seminarGroupService.getSeminarGroupLeaderByGroupId(new BigInteger("2"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void listSeminarGroupBySeminarId(){
        try {
            seminarGroupService.listSeminarGroupMemberByGroupId(new BigInteger("1"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void deleteSeminarGroupBySeminarId(){
        try {
            seminarGroupService.deleteSeminarGroupBySeminarId(new BigInteger("1"));
        } catch (SeminarNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void insertSeminarGroupBySeminarId(){
        seminarGroupService.insertSeminarGroupBySeminarId(new BigInteger("1"),new BigInteger("2"),new SeminarGroup());
    }
    @Test
    public void insertSeminarGroupMemberByGroupId(){
        seminarGroupService.insertSeminarGroupMemberByGroupId(new BigInteger("1"),new SeminarGroupMember());
    }
}

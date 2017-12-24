package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.SeminarGroupMember;
import xmu.crms.entity.User;
import xmu.crms.exception.*;

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
    @Test
    public void deleteSeminarGroupByGroupId(){
        try {
            seminarGroupService.deleteSeminarGroupByGroupId(new BigInteger("1"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getSeminarGroupByGroupId(){
        try {
            seminarGroupService.getSeminarGroupByGroupId(new BigInteger("1"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getSeminarGroupLeaderById(){

        try {
            seminarGroupService.getSeminarGroupLeaderById(new BigInteger("1"),new BigInteger("1"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void automaticallyGrouping(){
        try {
            seminarGroupService.automaticallyGrouping(new BigInteger("1"),new BigInteger("1"));
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
        } catch (SeminarNotFoundException e) {
            e.printStackTrace();
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getSeminarGroupById(){
        try {
            seminarGroupService.getSeminarGroupById(new BigInteger("1"),new BigInteger("1"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void listGroupByTopicId(){

            seminarGroupService.listGroupByTopicId(new BigInteger("1"));

    }
    @Test
    public void insertTopicByGroupId(){
        try {
            seminarGroupService.insertTopicByGroupId(new BigInteger("1"),new BigInteger("1"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void assignLeaderById(){
        try {
            seminarGroupService.assignLeaderById(new BigInteger("1"),new BigInteger("1"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void resignLeaderById(){
        try {
            seminarGroupService.resignLeaderById(new BigInteger("1"),new BigInteger("1"));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidOperationException e) {
            e.printStackTrace();
        }
    }
}

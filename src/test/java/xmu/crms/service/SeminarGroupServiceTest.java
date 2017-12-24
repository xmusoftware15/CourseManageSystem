package xmu.crms.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.TopicNotFoundException;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeminarGroupServiceTest {

    @Autowired
    private SeminarGroupService seminarGroupService;

    @Test
    public void testInsertTopicByGroupId() throws TopicNotFoundException, GroupNotFoundException {
        System.out.println(seminarGroupService.insertTopicByGroupId(BigInteger.valueOf(36), BigInteger.valueOf(2)));
    }
}

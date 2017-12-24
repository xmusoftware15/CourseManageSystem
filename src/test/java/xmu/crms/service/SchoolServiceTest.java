package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.School;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolServiceTest {

    @Autowired
    private SchoolService schoolService;

    @Test
    public void testGetSchoolBySchoolId() {
        School school = schoolService.getSchoolBySchoolId(new BigInteger("101"));
        System.out.println(school);
    }
}

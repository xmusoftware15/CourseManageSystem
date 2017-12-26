package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void testListCourseByUserId() throws Exception {
        System.out.println(courseService.listCourseByUserId(new BigInteger("4")));
    }
}

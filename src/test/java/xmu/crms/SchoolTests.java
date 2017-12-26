package xmu.crms;

import java.math.BigInteger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xmu.crms.entity.School;
import xmu.crms.service.impl.SchoolServiceImpl;

/**
 * @author 3-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CrmsApplication.class)
public class SchoolTests {
    @Autowired
    private SchoolServiceImpl schoolService;

    @Test
    public void getSchoolById(){
        School school;
        for(int i = 1; i < 10; i++){
            school = schoolService.getSchoolBySchoolId(BigInteger.valueOf(i));
            System.out.printf("%d %s %s %s\n",school.getId(),school.getCity(),school.getName(),school.getProvince());
        }
    }

}

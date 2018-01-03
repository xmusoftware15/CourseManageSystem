package xmu.crms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.mapper.ClassMapper;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClassMapperTest {

    @Autowired
    private ClassMapper classMapper;

    @Test
    public void testFindSeminarById() {
        System.out.println(classMapper.findSeminarById(new BigInteger("4")));
    }
}

package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FixGroupServiceTest {

    @Autowired
    private FixGroupService fixGroupService;

    @Test
    public void testFixedGroupToSeminarGroup() {
        try {
            fixGroupService.fixedGroupToSeminarGroup(BigInteger.valueOf(1), BigInteger.valueOf(2));
        } catch (Exception e) {

        }
    }
}

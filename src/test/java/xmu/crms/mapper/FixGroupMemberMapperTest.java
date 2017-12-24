package xmu.crms.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.FixGroup;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FixGroupMemberMapperTest {

    @Autowired
    private FixGroupMemberMapper fixGroupMemberMapper;

    @Test
    public void testListFixGroupMemberByFixGroup() {
        FixGroup fixGroup = new FixGroup();
        fixGroup.setId(BigInteger.valueOf(2));
        System.out.println(fixGroupMemberMapper.listFixGroupMemberByFixGroup(fixGroup));
    }
}

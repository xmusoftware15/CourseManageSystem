package xmu.crms.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.SeminarGroupTopic;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarGroupTopicMapperTest {

    @Autowired
    private SeminarGroupTopicMapper seminarGroupTopicMapper;

    @Test
    public void testGetBySeminarAndTopic() {
        SeminarGroupTopic seminarGroupTopic = new SeminarGroupTopic();
        seminarGroupTopic.setId(new BigInteger("1"));
        System.out.println(seminarGroupTopicMapper.getById(seminarGroupTopic));
    }
}

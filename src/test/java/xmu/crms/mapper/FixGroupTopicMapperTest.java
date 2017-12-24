package xmu.crms.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.FixGroup;

import java.math.BigInteger;

/**
 *
 * @author badcode
 * @date 2017/12/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FixGroupTopicMapperTest {

    private Log logger = LogFactory.getLog(this.getClass().getName());

    @Autowired
    private FixGroupTopicMapper fixGroupTopicMapper;

    @Test
    public void testListFixGroupTopicByFixGroup() {
        FixGroup fixGroup = new FixGroup();
        fixGroup.setId(BigInteger.valueOf(2));
        logger.info(fixGroupTopicMapper.listFixGroupTopicByFixGroup(fixGroup));
    }
}

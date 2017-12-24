package xmu.crms.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SchoolDAOTest {

    @Autowired
    SchoolDAO schoolDAO;

    @Test
    public void testListSchoolByCity() {
        System.out.println(schoolDAO.listSchoolByCity("太原"));
    }
}

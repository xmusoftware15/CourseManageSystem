package xmu.crms.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

/**
 * @Author: yexiaona
 * @Description:
 * @Data: 2017/12/19 21:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarMapperTest {

    @Autowired
    private SeminarMapper seminarMapper;

    @Test
    public void listSeminarByCourseIdTest() {
        List<Seminar> list = seminarMapper.listSeminarByCourseId(new BigInteger("1"));
        for (Seminar seminar : list
                ) {
            System.out.println(seminar);
        }
    }

    @Test
    public void deleteSeminarBySeminarIdTest() {
        Integer res = seminarMapper.deleteSeminarBySeminarId(new BigInteger("22"));
        System.out.println(res > 0);
    }

    @Test
    public void getSeminarBySeminarIdTest() {
        Seminar seminar = seminarMapper.getSeminarBySeminarId(new BigInteger("2"));
        System.out.println(seminar);
    }

    @Test
    public void insertSeminarByCourseId() {
        Seminar seminar = new Seminar();
        seminar.setName("testSeminar");
        Course course = new Course();
        course.setId(new BigInteger("1"));
        seminar.setCourse(course);
        seminar.setDescription("this is the test seminar.");
        seminar.setFixed(true);
        seminar.setStartTime(new Date(2017 - 12 - 15));
        seminar.setEndTime(new Date(2017 - 12 - 25));

        Integer isInserted = seminarMapper.insertSeminarByCourseId(seminar);
        System.out.println(seminar.getId());
        System.out.println(isInserted);
    }

    @Test
    public void updateSeminarBySeminarId() {
        Seminar seminar = seminarMapper.getSeminarBySeminarId(new BigInteger("9"));
        seminar.setName("small apple");

        Integer isUpdated = seminarMapper.updateSeminarBySeminarId(seminar);
        System.out.println(seminar);
        System.out.println(isUpdated);
    }
}

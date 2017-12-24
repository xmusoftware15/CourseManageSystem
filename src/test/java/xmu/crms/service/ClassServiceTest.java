package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.dao.ClassDao;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Location;
import xmu.crms.entity.Seminar;
import xmu.crms.exception.*;
import xmu.crms.mapper.ClassMapper;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClassServiceTest {

    @Autowired
    private ClassService classService;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private ClassMapper classMapper;

    @Test
    public void testFindClassByCourseId() {
        try {
            System.out.println(classService.listClassByCourseId(new BigInteger("1")).toString());
        }catch (CourseNotFoundException e){
            System.out.println("Course");
        }
    }

    @Test
    public void testListClassByName(){
        try {
            System.out.println(classService.listClassByName("课程1","邱明").toString());
        }catch (CourseNotFoundException e){
            System.out.println("Course");
        }catch (UserNotFoundException e){
            System.out.println("User");
        }catch (ClassesNotFoundException e){
            System.out.println("Class");
        }
    }
    @Test
    public void testDeleteClassSelectionByClassId(){
        classService.deleteClassSelectionByClassId(BigInteger.valueOf(3));
    }
    @Test
    public void testGetClassByClassId(){

        try {
            System.out.println(classService.getClassByClassId(new BigInteger("1")).toString());
        }catch (ClassesNotFoundException e){

        }
    }

    @Test
    public void testUpdateClassByClassId(){
        ClassInfo newClass = new ClassInfo();
        BigInteger b = new BigInteger("6");
        newClass.setName("hx");
        newClass.setCourse(null);
        newClass.setDescription("nope");
        newClass.setSite("no where");
        newClass.setClassTime("buzhidao");
        newClass.setReportPercentage(50);
        newClass.setPresentationPercentage(50);
        newClass.setFivePointPercentage(20);
        newClass.setFourPointPercentage(30);
        newClass.setThreePointPercentage(50);
        try {
            classService.updateClassByClassId(b,newClass);
        }catch (ClassesNotFoundException e){
            System.out.println("Class");
        }
    }

    @Test
    public void testDeleteClassByClassId(){
        try {
            classService.deleteClassByClassId(new BigInteger("16"));
        }catch (ClassesNotFoundException e){
            System.out.println("classes");
        }
    }

    @Test
    public void testInsertCourseSelectionById(){

        try {
            System.out.println(classService.insertCourseSelectionById(new BigInteger("18"),new BigInteger("2")));
        }catch (UserNotFoundException e){
            System.out.println("User");
        }catch (ClassesNotFoundException e){
            System.out.println("Class");
        }
    }

    @Test
    public void testDeleteCourseSelectionById(){
        try {
            classService.deleteCourseSelectionById(new BigInteger("18"),new BigInteger("2"));
        }catch (UserNotFoundException e){
            System.out.println("User");
        }catch (ClassesNotFoundException e){
            System.out.println("Class");
        }
    }

    @Test
    public void testGetCallStatusById(){
        try {
            System.out.println(classService.getCallStatusById(new BigInteger("1"),new BigInteger("1")));
        }catch (SeminarNotFoundException e){
            System.out.println("Seminar");
        }
    }

    @Test
    public void testMapper(){
        ClassInfo classInfo = new ClassInfo();
        classInfo.setName("liliilili");
       try {
           System.out.println(classService.insertClassById(new BigInteger("1"),classInfo));
       } catch (CourseNotFoundException e){
            System.out.println("Course");
       }
    }

    @Test
    public void testDeleteClassByCourseId(){
        try {
            classService.deleteClassByCourseId(BigInteger.valueOf(2));
        }catch (CourseNotFoundException e){

        }catch (ClassesNotFoundException e){

        }
    }

    @Test
    public void testDeleteScoreRuleById(){
        try {
            classService.deleteScoreRuleById(BigInteger.valueOf(6));
        }catch (ClassesNotFoundException e){
            System.out.println("Class");
        }
    }

    @Test
    public void testGetScoreRule(){
        try {
            System.out.println(classService.getScoreRule(BigInteger.valueOf(1)).toString());
        }catch (ClassesNotFoundException e){

        }
    }

    @Test
    public void testInsertScoreRule(){
        ClassInfo classInfo = new ClassInfo();
        classInfo.setReportPercentage(25);
        classInfo.setPresentationPercentage(75);
        classInfo.setFivePointPercentage(1);
        classInfo.setFourPointPercentage(2);
        classInfo.setThreePointPercentage(97);
        try {
            System.out.println(classService.insertScoreRule(BigInteger.valueOf(1),classInfo));
        }catch (InvalidOperationException e){
            System.out.println("invalid");
        }catch (ClassesNotFoundException e){

        }
    }

    @Test
    public void testCallInRollById(){
        ClassInfo classInfo = new ClassInfo();
        classInfo.setId(BigInteger.valueOf(1));
        Seminar seminar = new Seminar();
        seminar.setId(BigInteger.valueOf(1));
        Location location = new Location();
        location.setClassInfo(classInfo);
        location.setSeminar(seminar);
        location.setLatitude(Double.valueOf(123));
        location.setLongitude(Double.valueOf(234));
        try {
            System.out.println(classService.callInRollById(location));
        }catch (ClassesNotFoundException e){
            System.out.println("class");
        }catch (SeminarNotFoundException e){
            System.out.println("Seminar");
        }
    }

    @Test
    public void testListClassByUserId(){
        try {
            System.out.println(classService.listClassByUserId(BigInteger.valueOf(14)).toString());
        }catch (IllegalArgumentException e){

        }catch (ClassesNotFoundException e){
            System.out.println("classes");
        }
    }

    @Test
    public void testEndCallRollById(){
        ClassInfo classInfo = new ClassInfo();
        classInfo.setId(BigInteger.valueOf(1));
        Seminar seminar = new Seminar();
        seminar.setId(BigInteger.valueOf(1));
        Location location = new Location();
        location.setClassInfo(classInfo);
        location.setSeminar(seminar);
        location.setStatus(0);
        try {
            classService.endCallRollById(location);
        }catch (SeminarNotFoundException e){
            System.out.println("Seminar");
        }catch (ClassesNotFoundException e){
            System.out.println("Class");
        }
    }
}

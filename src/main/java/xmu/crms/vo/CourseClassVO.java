package xmu.crms.vo;


import xmu.crms.entity.ClassInfo;

import java.math.BigInteger;

public class CourseClassVO {

    private BigInteger id;
    private String name;
    private Integer numStudent;
    private String time;
    private String site;
    private BigInteger courseId;
    private String courseName;
    private String courseTeacher;

    public CourseClassVO() {}

    public CourseClassVO(ClassInfo classInfo) {
        this.id = classInfo.getId();
        this.name = classInfo.getName();
        this.time = classInfo.getClassTime();
        this.site = classInfo.getSite();
        this.courseName = classInfo.getCourse().getName();
        this.courseTeacher = classInfo.getCourse().getTeacher().getName();
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(Integer numStudent) {
        this.numStudent = numStudent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    @Override
    public String toString() {
        return "CourseClassVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numStudent=" + numStudent +
                ", time='" + time + '\'' +
                ", site='" + site + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseTeacher='" + courseTeacher + '\'' +
                '}';
    }
}

package xmu.crms.vo;

import xmu.crms.entity.Course;

import java.math.BigInteger;

public class CourseDetail {

    private BigInteger id;
    private String name;
    private String description;
    private String teacherName;
    private String teacherEmail;

    public CourseDetail(){}

    public CourseDetail(Course course) {
        this.name = course.getName();
        this.description = course.getDescription();
        this.teacherName = course.getTeacher().getName();
        this.teacherEmail = course.getTeacher().getEmail();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    @Override
    public String toString() {
        return "CourseDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                '}';
    }
}

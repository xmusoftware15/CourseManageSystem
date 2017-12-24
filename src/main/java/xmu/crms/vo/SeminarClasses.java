package xmu.crms.vo;

import xmu.crms.entity.Seminar;

import java.util.Date;
import java.util.List;

public class SeminarClasses {

    private Long id;
    private String name;
    private String courseName;
    private String groupingMethond;
    private Date startTime;
    private Date endTime;
    private List<Class> classes;

    public SeminarClasses() {}

    public SeminarClasses(Seminar seminar, List<Class> classes) {
        this.name = seminar.getName();
        this.courseName = seminar.getCourse().getName();
        this.startTime = seminar.getStartTime();
        this.endTime = seminar.getEndTime();
        this.classes = classes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGroupingMethond() {
        return groupingMethond;
    }

    public void setGroupingMethond(String groupingMethond) {
        this.groupingMethond = groupingMethond;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "SeminarClasses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseName='" + courseName + '\'' +
                ", groupingMethond='" + groupingMethond + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", classes=" + classes +
                '}';
    }
}

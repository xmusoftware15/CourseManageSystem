package xmu.crms.vo;

import xmu.crms.entity.Seminar;

import java.util.Date;

public class SeminarWithGrade {

    private Long id;
    private String name;
    private String description;
    private String groupingMethod;
    private Date startTime;
    private Date endTime;
    private Integer grade;

    public SeminarWithGrade(){}

    public SeminarWithGrade(Seminar seminar, Integer grade) {
        this.name = seminar.getName();
        this.description = seminar.getDescription();
        this.startTime = seminar.getStartTime();
        this.endTime = seminar.getEndTime();
        this.grade = grade;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupingMethod() {
        return groupingMethod;
    }

    public void setGroupingMethod(String groupingMethod) {
        this.groupingMethod = groupingMethod;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SeminarWithGrade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupingMethod='" + groupingMethod + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", grade=" + grade +
                '}';
    }
}

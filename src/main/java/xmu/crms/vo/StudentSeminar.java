package xmu.crms.vo;

import java.util.Date;

public class StudentSeminar {

    private Long id;
    private String name;
    private String groupingMethond;
    private String courseName;
    private Date startTime;
    private Date endTime;
    private Integer classCalling;
    private Boolean isLeader;
    private Boolean areTopicsSelected;

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

    public String getGroupingMethond() {
        return groupingMethond;
    }

    public void setGroupingMethond(String groupingMethond) {
        this.groupingMethond = groupingMethond;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public Integer getClassCalling() {
        return classCalling;
    }

    public void setClassCalling(Integer classCalling) {
        this.classCalling = classCalling;
    }

    public Boolean getLeader() {
        return isLeader;
    }

    public void setLeader(Boolean leader) {
        isLeader = leader;
    }

    public Boolean getAreTopicsSelected() {
        return areTopicsSelected;
    }

    public void setAreTopicsSelected(Boolean areTopicsSelected) {
        this.areTopicsSelected = areTopicsSelected;
    }

    @Override
    public String toString() {
        return "StudentSeminar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupingMethond='" + groupingMethond + '\'' +
                ", courseName='" + courseName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", classCalling=" + classCalling +
                ", isLeader=" + isLeader +
                ", areTopicsSelected=" + areTopicsSelected +
                '}';
    }
}

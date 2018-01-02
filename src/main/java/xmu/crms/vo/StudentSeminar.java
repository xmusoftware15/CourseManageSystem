package xmu.crms.vo;

import java.util.Date;

/**
 * @author 1-11、2-4
 */
public class StudentSeminar {

    private Long id;
    private String name;
    private String groupingMethod;
    private String courseName;
    private Date startTime;
    private Date endTime;
    private Integer classCalling;
    private String isLeader;
    private String areTopicsSelected;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getGroupingMethod() {
        return groupingMethod;
    }

    public void setGroupingMethod(String groupingMethod) {
        this.groupingMethod = groupingMethod;
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

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }

    public String getAreTopicsSelected() {
        return areTopicsSelected;
    }

    public void setAreTopicsSelected(String areTopicsSelected) {
        this.areTopicsSelected = areTopicsSelected;
    }

    @Override
    public String toString() {
        return "StudentSeminar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupingMethond='" + groupingMethod + '\'' +
                ", courseName='" + courseName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", classCalling=" + classCalling +
                ", isLeader='" + isLeader + '\'' +
                ", areTopicsSelected='" + areTopicsSelected + '\'' +
                '}';
    }
}

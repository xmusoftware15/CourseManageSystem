package xmu.crms.vo;

public class AttendanceStatus {

    private Integer numPresent;
    private Integer numStudent;
    private String status;
    private String group;

    public Integer getNumPresent() {
        return numPresent;
    }

    public void setNumPresent(Integer numPresent) {
        this.numPresent = numPresent;
    }

    public Integer getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(Integer numStudent) {
        this.numStudent = numStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "AttendanceStatus{" +
                "numPresent=" + numPresent +
                ", numStudent=" + numStudent +
                ", status='" + status + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}

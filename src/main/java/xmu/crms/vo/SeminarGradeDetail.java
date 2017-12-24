package xmu.crms.vo;


import xmu.crms.entity.SeminarGroup;

public class SeminarGradeDetail {

    private String seminarName;
    private String groupName;
    private String leaderName;
    private Integer presentationGrade;
    private Integer reportGrade;
    private Integer grade;

    public SeminarGradeDetail(SeminarGroup seminarGroup) {
        this.seminarName = seminarGroup.getSeminar().getName();
        this.leaderName = seminarGroup.getLeader().getName();
        this.presentationGrade = seminarGroup.getPresentationGrade();
        this.reportGrade = seminarGroup.getReportGrade();
        this.grade = seminarGroup.getFinalGrade();
    }

    public SeminarGradeDetail() {}


    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Integer getPresentationGrade() {
        return presentationGrade;
    }

    public void setPresentationGrade(Integer presentationGrade) {
        this.presentationGrade = presentationGrade;
    }

    public Integer getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(Integer reportGrade) {
        this.reportGrade = reportGrade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SeminarGradeDetail{" +
                "seminarName='" + seminarName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", leaderName='" + leaderName + '\'' +
                ", presentationGrade=" + presentationGrade +
                ", reportGrade=" + reportGrade +
                ", grade=" + grade +
                '}';
    }
}

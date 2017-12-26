package xmu.crms.vo;


import xmu.crms.entity.SeminarGroup;

import java.math.BigInteger;
import java.util.List;

public class SeminarGradeDetail {

    private String seminarName;
    private String groupName;
    private String leaderName;
    private List<PresentationGrade> presentationGrade;
    private BigInteger reportGrade;
    private BigInteger grade;
    public SeminarGradeDetail(String seminarName, String groupName, String leaderName, List<PresentationGrade> presentationGrade, BigInteger reportGrade, BigInteger grade) {
        this.seminarName = seminarName;
        this.groupName = groupName;
        this.leaderName = leaderName;
        this.presentationGrade = presentationGrade;
        this.reportGrade = reportGrade;
        this.grade = grade;
    }
    public SeminarGradeDetail(SeminarGroup seminarGroup) {
        this.seminarName = seminarGroup.getSeminar().getName();
        this.leaderName = seminarGroup.getLeader().getName();

    }

    public SeminarGradeDetail() {}

    public List<PresentationGrade> getPresentationGrade() {
        return presentationGrade;
    }

    public void setPresentationGrade(List<PresentationGrade> presentationGrade) {
        this.presentationGrade = presentationGrade;
    }

    public BigInteger getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(BigInteger reportGrade) {
        this.reportGrade = reportGrade;
    }

    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
        this.grade = grade;
    }

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

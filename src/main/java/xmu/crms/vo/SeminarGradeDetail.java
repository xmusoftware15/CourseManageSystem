package xmu.crms.vo;


import java.math.BigInteger;
import java.util.List;

public class SeminarGradeDetail {

    private String seminarName;
    private String groupName;
    private String leaderName;
    private List<PresentationGrade> presentationGrade;
    private BigInteger reportGrade;
    private BigInteger grade;

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



    public BigInteger getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(BigInteger reportGrade) {
        this.reportGrade = reportGrade;
    }

    public List<PresentationGrade> getPresentationGrade() {
        return presentationGrade;
    }

    public void setPresentationGrade(List<PresentationGrade> presentationGrade) {
        this.presentationGrade = presentationGrade;
    }

    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
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

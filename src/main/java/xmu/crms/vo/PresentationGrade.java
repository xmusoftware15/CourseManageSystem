package xmu.crms.vo;

import java.math.BigInteger;

public class PresentationGrade {
    private BigInteger topicId;
    private BigInteger grade;

    public BigInteger getTopicId() {
        return topicId;
    }

    public void setTopicId(BigInteger topicId) {
        this.topicId = topicId;
    }

    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
        this.grade = grade;
    }
}

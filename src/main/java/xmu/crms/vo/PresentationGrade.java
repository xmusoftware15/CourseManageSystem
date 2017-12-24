package xmu.crms.vo;

import java.math.BigInteger;

public class PresentationGrade {
    private BigInteger topic_id;
    private BigInteger grade;

    public BigInteger getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(BigInteger topic_id) {
        this.topic_id = topic_id;
    }

    public BigInteger getGrade() {
        return grade;
    }

    public void setGrade(BigInteger grade) {
        this.grade = grade;
    }
}

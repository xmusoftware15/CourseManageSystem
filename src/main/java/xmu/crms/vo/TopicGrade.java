package xmu.crms.vo;

/**
 * @author badcode
 * @date 2017/12/01
 */
public class TopicGrade {

    private Integer topicId;
    private Integer grade;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "TopicGrade{" +
                "topicId=" + topicId +
                ", grade=" + grade +
                '}';
    }
}

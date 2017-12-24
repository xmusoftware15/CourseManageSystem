package xmu.crms.vo;

/**
 * @Author: yexiaona
 * @Description:
 * @Data: 2017/12/23 0:23
 */
public class SeminarGroupVO {

    private Long id;
    private String name;
    private Long topicId;
    private String topicName;

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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}

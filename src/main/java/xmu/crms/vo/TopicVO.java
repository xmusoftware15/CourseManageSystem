package xmu.crms.vo;

import xmu.crms.entity.Topic;

import java.math.BigInteger;

/**
 *
 * @author badcode
 * @date 2017/12/24
 *
 */
public class TopicVO {

    private BigInteger id;
    private String serial;
    private String name;
    private String description;
    private Integer groupLimit;
    private Integer groupMemberLimit;
    private Integer groupLeft;

    public TopicVO() {}

    public TopicVO(Topic topic) {
        this.id = topic.getId();
        this.serial = topic.getSerial();
        this.name = topic.getName();
        this.description = topic.getDescription();
        this.groupLimit = topic.getGroupNumberLimit();
        this.groupMemberLimit = topic.getGroupStudentLimit();
    }

    public Topic transferToTopic() {
        Topic topic = new Topic();
        topic.setSerial(this.serial);
        topic.setDescription(this.description);
        topic.setGroupNumberLimit(this.groupLimit);
        topic.setGroupStudentLimit(this.groupMemberLimit);
        topic.setName(this.name);
        return topic;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGroupLimit() {
        return groupLimit;
    }

    public void setGroupLimit(Integer groupLimit) {
        this.groupLimit = groupLimit;
    }

    public Integer getGroupMemberLimit() {
        return groupMemberLimit;
    }

    public void setGroupMemberLimit(Integer groupMemberLimit) {
        this.groupMemberLimit = groupMemberLimit;
    }

    public Integer getGroupLeft() {
        return groupLeft;
    }

    public void setGroupLeft(Integer groupLeft) {
        this.groupLeft = groupLeft;
    }
}

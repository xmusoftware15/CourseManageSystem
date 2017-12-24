package xmu.crms.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yexiaona
 * @create 2017/12/24
 */
public class SeminarVO {

    private Long id;
    private String name;
    private String description;
    private String groupingMethod;
    private Date startTime;
    private Date endTime;
    private List<TopicBasicVO> topics;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupingMethod() {
        return groupingMethod;
    }

    public void setGroupingMethod(String groupingMethod) {
        this.groupingMethod = groupingMethod;
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

    public List<TopicBasicVO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicBasicVO> topics) {
        this.topics = topics;
    }
}

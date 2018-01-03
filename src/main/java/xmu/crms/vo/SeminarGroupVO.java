package xmu.crms.vo;

import java.math.BigInteger;

/**
 * @Author: yexiaona
 * @Description:
 * @Data: 2017/12/23 0:23
 */
public class SeminarGroupVO {

    private Long id;
    private String name;
    private TopicBasicVO topics;

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

    public TopicBasicVO getTopics() {
        return topics;
    }

    public void setTopics(TopicBasicVO topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "SeminarGroupVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topics=" + topics +
                '}';
    }
}

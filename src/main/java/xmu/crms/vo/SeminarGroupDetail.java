package xmu.crms.vo;

import java.math.BigInteger;
import java.util.List;

/**
 * @author yexiaona
 * @create 2017/12/25
 */
public class SeminarGroupDetail {

    private BigInteger id;
    private String name;
    private UserIdNameVO leader;
    private List<UserIdNameVO> members;
    private List<TopicBasicVO> topics;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserIdNameVO getLeader() {
        return leader;
    }

    public void setLeader(UserIdNameVO leader) {
        this.leader = leader;
    }

    public List<UserIdNameVO> getMembers() {
        return members;
    }

    public void setMembers(List<UserIdNameVO> members) {
        this.members = members;
    }

    public List<TopicBasicVO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicBasicVO> topics) {
        this.topics = topics;
    }
}

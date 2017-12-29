package xmu.crms.vo;

import xmu.crms.entity.Topic;

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
    private List<Topic> topics;

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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setMembers(List<UserIdNameVO> members) {
        this.members = members;
    }


}

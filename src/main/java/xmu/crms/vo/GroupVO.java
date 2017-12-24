package xmu.crms.vo;

import java.math.BigInteger;
import java.util.List;

public class GroupVO {
    BigInteger id;
    String name;
    Member leader;
    List<Member> members;
    List<TopicHXR> topics;
    String report;

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

    public Member getLeader() {
        return leader;
    }

    public void setLeader(Member leader) {
        this.leader = leader;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<TopicHXR> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicHXR> topics) {
        this.topics = topics;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "GroupVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leader=" + leader +
                ", members=" + members +
                ", topics=" + topics +
                ", report='" + report + '\'' +
                '}';
    }
}

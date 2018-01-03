package xmu.crms.vo;

import java.util.List;

/**
 * @author 1-11、2-4
 */
public class GroupMemberVO {

    private StudentVO leader;
    private List<StudentVO> members;

    public StudentVO getLeader() {
        return leader;
    }

    public void setLeader(StudentVO leader) {
        this.leader = leader;
    }

    public List<StudentVO> getMembers() {
        return members;
    }

    public void setMembers(List<StudentVO> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "GroupMemberVO{" +
                "leader=" + leader +
                ", members=" + members +
                '}';
    }
}

package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupMember;
import xmu.crms.mapper.FixGroupMemberMapper;

import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/23
 */
@Component
public class FixGroupMemberDAO {

    @Autowired
    private FixGroupMemberMapper fixGroupMemberMapper;

    public List<FixGroupMember> listFixGroupMemberByFixGroup(FixGroup fixGroup) {
        return fixGroupMemberMapper.listFixGroupMemberByFixGroup(fixGroup);
    }

    public void deleteStudentFromFixGroup(FixGroupMember fixGroupMember) {
        fixGroupMemberMapper.deleteFixGroupMemberByFixGroupAndStudent(fixGroupMember);
    }
}

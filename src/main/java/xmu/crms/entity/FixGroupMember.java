package xmu.crms.entity;

import java.math.BigInteger;

/**
 * @author 数据库标准组
 */
public class FixGroupMember {
	private BigInteger id;
	private FixGroup fixGroup;
	private User student;

	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public FixGroup getFixGroup() {
		return fixGroup;
	}
	public void setFixGroup(FixGroup fixGroup) {
		this.fixGroup = fixGroup;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "FixGroupMember{" +
				"id=" + id +
				", fixGroup=" + fixGroup +
				", student=" + student +
				'}';
	}
}

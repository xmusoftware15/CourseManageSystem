package xmu.crms.entity;

import java.math.BigInteger;

/**
 * @author 数据库标准组
 */
public class CourseSelection {
	private BigInteger id;
	private ClassInfo classInfo;
	private User student;

	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public ClassInfo getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "CourseSelection{" +
				"id=" + id +
				", classInfo=" + classInfo +
				", student=" + student +
				'}';
	}
}

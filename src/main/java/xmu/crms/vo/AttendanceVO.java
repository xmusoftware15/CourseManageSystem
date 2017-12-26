package xmu.crms.vo;

import java.math.BigInteger;

import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.User;

public class AttendanceVO {
	private BigInteger id;
	private User student;
	private String attendanceStatus;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(Integer attendanceStatus) {
		if(attendanceStatus==0)
			this.attendanceStatus = "出勤";
		else if(attendanceStatus==1)
			this.attendanceStatus="迟到";
		else {
			this.attendanceStatus="缺勤";
		}
	}
}

package xmu.crms.vo;

import xmu.crms.entity.School;

import java.math.BigInteger;

public class SchoolIdNameVO {
	private BigInteger id;
	private String name;
	
	public SchoolIdNameVO(School school)
	{
		id=school.getId();
		name=school.getName();
	}
	
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
}

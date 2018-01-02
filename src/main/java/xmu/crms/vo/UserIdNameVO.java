package xmu.crms.vo;

import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * @author 1-11、2-4
 */
public class UserIdNameVO {

	private BigInteger id;
	private String name;
	
	public UserIdNameVO(User user)
	{
		id=user.getId();
		name=user.getName();
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
	
	@Override
	public String toString() {
		return id + " " + name;
	}
}

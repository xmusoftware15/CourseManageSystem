package xmu.crms.vo;

import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * @author 1-11、2-4
 */
public class UserVO {
    private BigInteger id;
    private String type;
    private String name;
    private String number;
    private String phone;
    private String email;
    private String gender;
    private SchoolIdNameVO school;
    private Integer title;
    public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	private String avatar;

    public UserVO() {
    }

    public UserVO(User user)
    {
        id=user.getId();
        name=user.getName();
        number=user.getNumber();
        phone=user.getPhone();
        email=user.getEmail();
        Integer genderInt=user.getGender();
        if(genderInt==null) {
            gender = null;
        } else if(genderInt==0) {
            gender = "male";
        } else {
            gender = "female";
        }
        Integer typeInt=user.getType();
        if(typeInt==null) {
            type = null;
        } else if(typeInt==0) {
            type = "student";
        } else {
            type = "teacher";
        }
        school=new SchoolIdNameVO(user.getSchool());
        title=user.getTitle();
        avatar=user.getAvatar();
    }
    
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public SchoolIdNameVO getSchool() {
		return school;
	}

	public void setSchool(SchoolIdNameVO school) {
		this.school = school;
	}

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", school=" + school +
                ", title=" + title +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}

package xmu.crms.vo;

import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 *
 * @author badcode
 * @date 2017/12/24
 *
 */
public class UserVO {

    private BigInteger id;
    private String type;
    private String name;
    private String jwt;

    public UserVO() {}

    public UserVO(User user, String jwt) {
        this.id = user.getId();
        this.type = user.getType() == 0 ? "student" : "teacher";
        this.name = user.getName();
        this.jwt = jwt;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}

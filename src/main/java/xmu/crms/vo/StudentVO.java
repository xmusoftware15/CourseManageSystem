package xmu.crms.vo;

import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * @author 1-11、2-4
 */
public class StudentVO {

    private BigInteger id;
    private String name;
    private String number;

    public StudentVO() {}

    public StudentVO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.number = user.getNumber();
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "StudentVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

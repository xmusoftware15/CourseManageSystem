package xmu.crms.vo;

import xmu.crms.entity.SeminarGroup;

import java.math.BigInteger;

/**
 *
 * @author badcode
 * @date 2017/12/24
 */
public class GroupOfTopicVO {

    private BigInteger id;
    private String name;

    public GroupOfTopicVO() {}

    public GroupOfTopicVO(SeminarGroup seminarGroup) {
        this.id = seminarGroup.getId();
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
        return "GroupOfTopicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

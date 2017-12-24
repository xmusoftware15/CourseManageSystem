package xmu.crms.vo;

import xmu.crms.entity.User;

import java.util.List;

/**
 * @author badcode
 * @date 2017/12/01
 */
public class ClassAttendanceVO {

    private Integer numPresent;
    private List<User> present;
    private List<User> late;
    private List<User> absent;

    public Integer getNumPresent() {
        return numPresent;
    }

    public void setNumPresent(Integer numPresent) {
        this.numPresent = numPresent;
    }

    public List<User> getPresent() {
        return present;
    }

    public void setPresent(List<User> present) {
        this.present = present;
    }

    public List<User> getLate() {
        return late;
    }

    public void setLate(List<User> late) {
        this.late = late;
    }

    public List<User> getAbsent() {
        return absent;
    }

    public void setAbsent(List<User> absent) {
        this.absent = absent;
    }

    @Override
    public String toString() {
        return "ClassAttendanceVO{" +
                "numPresent=" + numPresent +
                ", present=" + present +
                ", late=" + late +
                ", absent=" + absent +
                '}';
    }
}

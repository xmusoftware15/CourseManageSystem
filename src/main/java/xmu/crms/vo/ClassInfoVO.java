package xmu.crms.vo;

import xmu.crms.entity.ClassInfo;

import java.math.BigInteger;

/**
 *
 * @author badcode
 * @date 2017/12/24
 *
 */
public class ClassInfoVO {

    private BigInteger id;
    private String name;
    private String time;
    private String site;
    private Proportions proportions;


    public ClassInfoVO() {}

    public ClassInfoVO(ClassInfo classInfo) {
        this.id = classInfo.getId();
        this.name = classInfo.getName();
        this.time = classInfo.getClassTime();
        this.site = classInfo.getSite();
        this.proportions = new Proportions();
        this.proportions.setA(classInfo.getFivePointPercentage());
        this.proportions.setB(classInfo.getFourPointPercentage());
        this.proportions.setC(classInfo.getThreePointPercentage());
        this.proportions.setPresentation(classInfo.getPresentationPercentage());
        this.proportions.setReport(classInfo.getReportPercentage());
    }

    public ClassInfo transferToClassInfo() {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setName(name);
        classInfo.setClassTime(time);
        classInfo.setSite(site);
        classInfo.setFivePointPercentage(proportions.getA());
        classInfo.setFourPointPercentage(proportions.getB());
        classInfo.setThreePointPercentage(proportions.getC());
        classInfo.setReportPercentage(proportions.getReport());
        classInfo.setPresentationPercentage(proportions.getPresentation());
        return classInfo;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Proportions getProportions() {
        return proportions;
    }

    public void setProportions(Proportions proportions) {
        this.proportions = proportions;
    }

    @Override
    public String toString() {
        return "ClassInfoVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", site='" + site + '\'' +
                ", proportions=" + proportions +
                '}';
    }
}

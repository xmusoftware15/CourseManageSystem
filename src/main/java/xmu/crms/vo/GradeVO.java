package xmu.crms.vo;

import java.util.List;

/**
 * @author badcode
 */
public class GradeVO {
    private List<PresentationGrade> list;

    public List<PresentationGrade> getList() {
        return list;
    }

    public void setList(List<PresentationGrade> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GradeVO{" +
                "list=" + list +
                '}';
    }
}

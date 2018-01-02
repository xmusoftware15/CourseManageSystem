package xmu.crms.vo;

/**
 * @author yexiaona
 * @create 2017/12/24
 */
public class TopicBasicVO {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "TopicBasicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

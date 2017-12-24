package xmu.crms.vo;

/**
 *
 * @author badcode
 * @date 2017/12/25
 */
public class Status {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status='" + status + '\'' +
                '}';
    }
}

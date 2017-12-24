package xmu.crms.vo;

import java.math.BigInteger;


/**
 * @author heqi
 * @date 2017/12/24
 */
public class SimpleClassInfoVo {
    private BigInteger id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}

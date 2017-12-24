package xmu.crms.vo;

import java.math.BigInteger;

/**
 *
 * @author badcode
 * @date 2017/12/22
 *
 */
public class LoginSuccessVO {

    private String jwt;
    private String name;
    private BigInteger id;
    private String type;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

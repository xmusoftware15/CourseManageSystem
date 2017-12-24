package xmu.crms.handler;

import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/19
 *
 */
public class Response {

    private String message;
    private List<String> error;

    public Response() {}

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}

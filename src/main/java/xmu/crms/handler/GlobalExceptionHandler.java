package xmu.crms.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xmu.crms.exception.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author badcode
 * @date 2017/12/19
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = TopicNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response topicNotFoundHandler() {
        Response response = new Response("未找到主题");
        return response;
    }

    @ExceptionHandler(value = SeminarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response seminarNotFoundHandler() {
        Response response = new Response("未找到讨论课");
        return response;
    }

    @ExceptionHandler(value = CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response courseNotFoundHandler() {
        Response response = new Response("未找到课程");
        return response;
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response classNotFoundHandler() {
        Response response = new Response("未找到班级");
        return response;
    }

    @ExceptionHandler(value = ClassesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response classesNotFoundHandler() {
        Response response = new Response("未找到班级");
        return response;
    }

    @ExceptionHandler(value = GroupNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response groupNotFoundHandler() {
        Response response = new Response("未找到小组");
        return response;
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response userNotFoundHandler(){
        Response response=new Response("未找到用户");
        return  response;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response illegalArgumentHandler(IllegalArgumentException e) {
        Response response = new Response("错误的ID格式");
        List<String> error = new ArrayList<>();
        error.add(e.getMessage());
        response.setError(error);
        return response;
    }
}

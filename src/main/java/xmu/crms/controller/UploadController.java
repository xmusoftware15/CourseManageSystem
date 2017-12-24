package xmu.crms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xmu.crms.vo.UrlVO;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UploadController {

    @PostMapping("/upload/avatar")
    @ResponseStatus(HttpStatus.CREATED)
    public UrlVO uploadAvatar(HttpServletResponse response) {
        response.setStatus(201);
        UrlVO url = new UrlVO();
        url.setUrl("/avatar/3486.png");
        return url;
    }
}

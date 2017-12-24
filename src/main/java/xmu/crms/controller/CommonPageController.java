package xmu.crms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonPageController {

    @RequestMapping("/login")
    public String login() {
        return "common/AccountLoginPage";
    }
}

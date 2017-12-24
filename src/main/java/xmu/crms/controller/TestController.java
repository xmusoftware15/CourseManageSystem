package xmu.crms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * 对权限测试和获取当前用户信息
 */
@RestController
public class TestController {

    @GetMapping("/t1")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public String t1() {
        return "t1";
    }

    @GetMapping("/t2")
    @PreAuthorize("hasRole('TEACHER')")
    public String t2() {
        return "t2";
    }

    @GetMapping("/s1")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public String s1() {
        return "s1";
    }

    @GetMapping("/s2")
    @PreAuthorize("hasRole('STUDENT')")
    public String s2() {
        return "s2";
    }

    @GetMapping("/a")
    public Object a(@RequestAttribute("user") User user1, @RequestAttribute("userId") BigInteger id1,
                    @RequestAttribute("name") String name1, @RequestAttribute("type") Integer type1) {
        return new Object() {
            public User user = user1;
            public BigInteger id = id1;
            public String name = name1;
            public Integer type = type1;
        };
    }
}

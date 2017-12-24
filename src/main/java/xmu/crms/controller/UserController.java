package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.LoginService;
import xmu.crms.service.UserService;
import xmu.crms.vo.UserVO;

import java.math.BigInteger;


/**
 * @author badcode
 * @date 2017/11/29
 */
@RestController
public class UserController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public User getInfo(@RequestAttribute("userId") String userId)
            throws UserNotFoundException {
        return userService.getUserByUserId(new BigInteger(userId));
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyInfo(@RequestBody User user, @RequestAttribute("userId") String userId)
            throws UserNotFoundException {
        userService.updateUserByUserId(new BigInteger(userId), user);
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public UserVO loginByPassword(@RequestBody User user, @RequestAttribute("jwt") String jwt) throws UserNotFoundException {
        User userInfo = loginService.signInPhone(user);
        UserVO userVO = new UserVO(userInfo, jwt);
        return userVO;
    }

}

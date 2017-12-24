package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xmu.crms.entity.School;
import xmu.crms.entity.User;
import xmu.crms.security.auth.JwtService;
import xmu.crms.service.LoginService;
import xmu.crms.vo.LoginSuccessVO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 登录控制器。微信小程序登录与 Web 端账号密码登录
 *
 * @author LuLongfei
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public LoginSuccessVO register(@RequestBody Map<String, Object> map) throws NoSuchAlgorithmException {
        String phone = map.get("phone").toString();
        String password = map.get("password").toString();

        String name = map.get("name").toString();
        BigInteger schoolId = BigInteger.valueOf(Long.valueOf(map.get("schoolId").toString()));
        Integer gender = Integer.valueOf(map.get("gender").toString());
        Integer type = Integer.valueOf(map.get("type").toString());
        String email = map.get("email") == null ? null : map.get("email").toString();
        String number = map.get("number") == null ? null : map.get("number").toString();
        User user = new User();
        user.setPhone(phone);
        user.setPassword(md5Hex(password));
        user.setName(name);

        School school = new School();
        school.setId(schoolId);
        user.setSchool(school);
        user.setGender(gender);
        user.setType(type);
        user.setEmail(email);
        user.setNumber(number);

        user = loginService.signUpPhone(user);
        String jwt = jwtService.generateJwt(user);
        return new LoginSuccessVO(user.getId(), user.getType() == 0 ? "teacher" : "student", user.getName(), jwt);
    }

    private String md5Hex(String input) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] res = md5.digest(input.getBytes());
        return toHex(res);
    }

    private String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}

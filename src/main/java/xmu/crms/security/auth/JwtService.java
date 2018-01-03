package xmu.crms.security.auth;

import xmu.crms.entity.User;

/**
 * @author lulongfei
 */
public interface JwtService {
    /**
     * 从登录成功的用户信息生成 JWT
     *
     * @param user
     * @return
     */
    String generateJwt(User user);

    /**
     * 改变Jwt.
     * @param jwtString
     * @return
     */
    JwtPayload verifyJwt(String jwtString);
}

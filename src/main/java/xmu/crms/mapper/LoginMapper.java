package xmu.crms.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * LoginMapper
 * 仅获取登录所需要的用户信息
 *
 * @author LuLongfei
 * @date 2017-12-18
 */
@Component
public interface LoginMapper {
    /**
     * 获取使用手机号登录时所需的用户信息
     *
     * @param phone
     * @return
     */
    User getUserLoginByPhone(String phone);

    /**
     * 获取微信小程序登录时所需的用户信息
     *
     * @param wechat
     * @return
     */
    User getUserLoginByWechat(String wechat);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserbyId(BigInteger id);

    /**
     * 手机注册.
     * @param user
     */
    void createUserWithPhone(User user);

    /**
     * 用微信号创建用户.
     * @param user
     */
    void createUserWithWechat(User user);

    /**
     * 通过学校id和学工号获取用户.
     * @param schoolId
     * @param number
     * @return User
     */
    User getUserBySchoolAndNumber(@Param("schoolId") BigInteger schoolId, @Param("number") String number);

    /**
     * 为用户添加微信.
     * @param user
     */
    void addWechatToUser(User user);

    /**
     * 为用户添加手机.
     * @param user
     */
    void addPhoneToUser(User user);
}

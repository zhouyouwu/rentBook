package com.zhouyouwu.mapper;

import com.zhouyouwu.model.User;
import com.zhouyouwu.model.vo.ShowUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface UserMapper {

    User getUserById(@Param("userid") String userid);

    List<ShowUser> getUser(@Param("searchParam") String searchParam);

    void createUser(@Param("user") User user);

    void delUser(@Param("userid") String userid);

    /**
     * 仅支持修改密码和手机，账号身份证注册时间都是无法更改的
     * @param user
     */
    void updateUser(@Param("user") User user);

    void updateBalance(@Param("userid") String userid, @Param("opBalance") BigDecimal opBalance);
}

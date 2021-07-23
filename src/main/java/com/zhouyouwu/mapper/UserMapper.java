package com.zhouyouwu.mapper;

import com.zhouyouwu.model.User;
import com.zhouyouwu.model.vo.ShowUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface UserMapper {

    User getUserById(@Param("userid") String userid);

    List<ShowUser> getUser(@Param("searchParam") String searchParam);
}

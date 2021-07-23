package com.zhouyouwu.mapper;

import com.zhouyouwu.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface UserMapper {

    User getUserById(String userid);
}

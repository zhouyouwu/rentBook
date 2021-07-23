package com.zhouyouwu.service;

import com.zhouyouwu.mapper.UserMapper;
import com.zhouyouwu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(String userid) {

        return userMapper.getUserById(userid);
    }
}

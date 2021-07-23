package com.zhouyouwu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhouyouwu.mapper.UserMapper;
import com.zhouyouwu.model.User;
import com.zhouyouwu.model.vo.ShowUser;
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

    public PageInfo<ShowUser> getUser(String searchParam, Integer page, Integer size) {

        PageHelper.startPage(page, size);
        return new PageInfo<>(userMapper.getUser(searchParam));
    }
}

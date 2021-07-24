package com.zhouyouwu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhouyouwu.exception.UserException;
import com.zhouyouwu.mapper.UserMapper;
import com.zhouyouwu.model.User;
import com.zhouyouwu.model.vo.ShowUser;
import com.zhouyouwu.utils.SnowflakeUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Administrator
 */
@Log4j
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

    public void createUser(User user) {

        long userid = SnowflakeUtil.getSnowflakeId();
        user.setUserid(String.valueOf(userid));
        user.setRegisterTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        userMapper.createUser(user);
    }

    public void delUser(String userid, String param, boolean isIdCard) throws UserException {

        User user = userMapper.getUserById(userid);
        if(user == null){
            throw new UserException("用户不存在");
        }

        if(isIdCard){
            if(!user.getIdCardNo().equals(param)){
                throw new UserException("身份证不匹配");
            }
        }else {
            if(!user.getPassword().equals(param)){
                throw new UserException("密码错误");
            }
        }

        userMapper.delUser(userid);
    }

    public void updateUser(User user) {

        userMapper.updateUser(user);
    }
}

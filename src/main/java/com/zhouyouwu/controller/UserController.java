package com.zhouyouwu.controller;

import com.zhouyouwu.constant.BaseConstant;
import com.zhouyouwu.model.ResultObject;
import com.zhouyouwu.model.User;
import com.zhouyouwu.model.vo.LoginUser;
import com.zhouyouwu.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@Log4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login.do")
    public Object login(@RequestBody LoginUser loginUser){
        if(loginUser.getUserid().length() != BaseConstant.USERID_LENGTH){
            return ResultObject.fail("用户名不正确，请检查");
        }
        if(loginUser.getPassword().length() != BaseConstant.PASSWORD_LENGTH){
            return ResultObject.fail("密码长度有误，请检查");
        }

        User user = userService.getUserById(loginUser.getUserid());

        if(user == null){
            return ResultObject.fail("用户不存在！");
        }
        if(!user.getPassword().equals(loginUser.getPassword())){
            return ResultObject.fail("密码错误");
        }

        return ResultObject.ok("登录成功！");
    }

    @GetMapping("getUser.do")
    public Object getUser(@RequestParam(value="searchParam", required = false) String searchParam,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size){

        log.info("查询全部用户...");

        return ResultObject.ok(userService.getUser(searchParam, page, size));
    }

}

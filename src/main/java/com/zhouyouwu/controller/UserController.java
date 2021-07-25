package com.zhouyouwu.controller;

import com.zhouyouwu.constant.BaseConstant;
import com.zhouyouwu.exception.UserException;
import com.zhouyouwu.model.ResultObject;
import com.zhouyouwu.model.User;
import com.zhouyouwu.model.vo.LoginUser;
import com.zhouyouwu.service.UserService;
import com.zhouyouwu.utils.JwtUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

        String role = "1".equals(user.getUserid())?"admin":"emp";
        String token = JwtUtil.createJWT(user.getUserid(), role, "", 30*60*60);
        HashMap<String, String> map = new HashMap<>(2);
        map.put("token", token);
        map.put("role", role);
        return ResultObject.ok("登录成功！", map);
    }

    @PostMapping("loginByIdCard.do")
    public Object loginByIdCard(@RequestBody LoginUser loginUser){
        if(loginUser.getUserid().length() != BaseConstant.USERID_LENGTH){
            return ResultObject.fail("用户名不正确，请检查");
        }

        User user = userService.getUserById(loginUser.getUserid());

        if(user == null){
            return ResultObject.fail("用户不存在！");
        }
        if(!user.getIdCardNo().equals(loginUser.getIdCardNo())){
            return ResultObject.fail("身份验证失败");
        }
        if(!user.getPhoneNo().equals(loginUser.getPhoneNo())){
            return ResultObject.fail("身份验证失败");
        }

        String role = "1".equals(user.getUserid())?"admin":"emp";
        String token = JwtUtil.createJWT(user.getUserid(), role, "", 30*60*60);
        HashMap<String, String> map = new HashMap<>(2);
        map.put("token", token);
        map.put("role", role);
        return ResultObject.ok("登录成功！", map);
    }

    @GetMapping("getUser.do")
    public Object getUser(@RequestParam(value="searchParam", required = false) String searchParam,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size){

        log.info("查询全部用户...");

        return ResultObject.ok(userService.getUser(searchParam, page, size));
    }

    @PostMapping("createUser.do")
    public Object createUser(@RequestBody User user){

        log.info("添加新用户...");
        if(user == null){
            return ResultObject.fail("参数有误");
        }

        userService.createUser(user);
        return ResultObject.ok("添加成功");
    }

    @PostMapping("delUser.do")
    public Object delUser(@RequestParam("userid") String userid,
                          @RequestParam("param") String param,
                          @RequestParam("isIdCard") Boolean isIdCard) throws UserException {

        System.out.println("delUser......");
        userService.delUser(userid, param, isIdCard);
        return ResultObject.ok("删除成功");
    }

    @PostMapping("updateUser.do")
    public Object updateUser(@RequestBody User user){

        userService.updateUser(user);
        return ResultObject.ok("更新成功");
    }

}

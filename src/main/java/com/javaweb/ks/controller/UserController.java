package com.javaweb.ks.controller;


import com.javaweb.ks.dao.UserDao;
import com.javaweb.ks.model.User;
import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.UserService;
import com.javaweb.ks.util.TokenVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private TokenVerify tokenVerify;


    @GetMapping("/test")
    @ResponseBody
    public User test01(){
        return userService.getUserById(1);
    }


    // 用户注册
    @PostMapping("/register")
    @ResponseBody
    public Results register(String username, String password, String nickname){
        return userService.userRegister(username, password, nickname);
    }

    // 用户登录
    @PostMapping("/login")
    @ResponseBody
    public Results login(String username, String password){
        return userService.userLogin(username, password);
    }

    // 获取用户信息
    @GetMapping("/getInfo")
    @ResponseBody
    public Results getInfo(int userID, String token){
        return userService.getInfo(userID, token);
    }

    // 修改用户信息
    @PostMapping("/changeInfo")
    @ResponseBody
    public Results changeInfo(User user){
        return userService.changeUserInfo(user);
    }

    // 修改用户密码
    @PostMapping("/changePassword")
    @ResponseBody
    public Results changePassword(int ID, String token, String oldPassword, String password){
        return userService.changePassword(ID, token, oldPassword, password);
    }

    // 根据用户id和token获取用户地址
    @PostMapping("/getUserSite")
    @ResponseBody
    public Results getUserSite(int userID, String token){
        if(tokenVerify.tokenVerify(userID, token)){
            return userService.getUserSite(userID);
        }else{
            return new Results(0, "非法访问");
        }
    }

}

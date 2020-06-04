package com.javaweb.ks.service.impl;

import com.javaweb.ks.dao.UserDao;
import com.javaweb.ks.model.User;
import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.UserService;
import com.javaweb.ks.util.MD5;
import com.javaweb.ks.util.TokenVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    TokenVerify tokenVerify;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(1);
    }

    // 用户注册
    @Override
    public Results userRegister(String username, String password, String nickname) {
        if(userDao.getUserByUsername(username) != 0){ // 已经有这个username的用户了
            return new Results(0,"用户名已存在");
        }else {// 这个用户名还没有注册
            if(userDao.registerUser(username, MD5.crypt(password), nickname) != 1){ // 插入数据库失败
                return new Results(0,"未知错误");
            }else{
                return new Results(1,"注册成功");
            }
        }
    }

    // 用户登录
    @Override
    public Results userLogin(String username, String password) {
        String userPass = userDao.getUserPassByUsername(username); // 根据用户名获取密码
        if(userPass == null){ // 用户名不存在
            return new Results(0, "用户名不存在");
        }else {
            if(MD5.crypt(password).equals(userPass)){ // 密码正确
                String token = MD5.crypt(password + new Date().toString());// 生成token
                userDao.insertToken(username, token); // 插入token到数据库中
                int ID = userDao.getUserIdByUsername(username); // 根据用户的username获取用户的ID
                User user = new User();
                user.setID(ID);
                user.setToken(token);
                userDao.insertLoginTime(username);// 插入用户登录的时间
                return new Results(1,"登录成功", user);
            }else {
                return new Results(0, "用户名或密码错误");
            }
        }

    }

    // 获取用户信息
    @Override
    public Results getInfo(int userID, String token) {
        if(tokenVerify.tokenVerify(userID, token)){ // token对了
            return new Results(1, "获取用户信息成功", userDao.getUserInfoById(userID));
        }else {
            return new Results(0, "非法访问");
        }
    }

    // 修改用户信息
    @Override
    public Results changeUserInfo(User user) {
        if(tokenVerify.tokenVerify(user.getID(), user.getToken())){ // token对了
            userDao.changeUserInfo(user);
            return new Results(1, "修改用户信息成功");
        }else {
            return new Results(0, "非法访问");
        }
    }

    // 修改密码
    @Override
    public Results changePassword(int id, String token, String oldPassword, String password) {
        if(tokenVerify.tokenVerify(id, token)){ // token对了
            User user = userDao.getUserById(id);// 先通过用户id获取用户
            if(user.getPassword().equals(MD5.crypt(oldPassword))){ // 老密码对了
                userDao.changePassword(id, MD5.crypt(password));// 修改密码
                return new Results(1, "修改成功");
            }else {
                return new Results(0, "原密码不正确");
            }
        }else {
            return new Results(0, "非法访问");
        }
    }

    // 获取用户地址
    @Override
    public Results getUserSite(int userID) {
        return new Results(1, "获取地址成功", userDao.getUserSite(userID));
    }
}

package com.javaweb.ks.service;

import com.javaweb.ks.model.Shop;
import com.javaweb.ks.model.User;
import com.javaweb.ks.result.AdminResults;
import com.javaweb.ks.result.Results;

public interface AdminService {

    // 管理员登录
    Results adminLogin(String username, String password);

    // 获取用户列表信息，包括分页功能
    AdminResults getAllUser(Integer offset, Integer limit);

    // 添加用户
    Results addUser(String username, String password, String nickname);

    // 删除用户
    Results deleteUser(int userID);

    // 获取所有商品列表，同时分页
    AdminResults getAllShop(Integer offset, Integer limit);

    // 添加商品
    Results addShop(Shop shop);

    // 修改商品信息
    Results changeShopInform(Shop shop);

    // 删除商品
    Results deleteShop(int shopID);

    // 获取用户购物车
    AdminResults getAllCart(Integer offset, Integer limit);

    // 获取主页的一些信息， 用户总数和商品总数
    Results getIndex();

    // 修改管理员密码
    Results changePassword(String oldPassword, String newPassword);

    // 修改用户信息
    Results changeUserInfo(User user);
}

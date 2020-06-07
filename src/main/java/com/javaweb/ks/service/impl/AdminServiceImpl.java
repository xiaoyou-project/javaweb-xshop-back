package com.javaweb.ks.service.impl;

import com.javaweb.ks.dao.AdminDao;
import com.javaweb.ks.dao.UserDao;
import com.javaweb.ks.dto.IndexPage;
import com.javaweb.ks.model.Shop;
import com.javaweb.ks.model.User;
import com.javaweb.ks.result.AdminResults;
import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.AdminService;
import com.javaweb.ks.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private UserDao userDao;

    // 管理员登录
    @Override
    public Results adminLogin(String username, String password) {
        String adminPassword = adminDao.getAdminPass(username);// 先获取老的密码
        if(MD5.crypt(password).equals(adminPassword)){ // 密码正确
            return new Results(1, "登录成功");
        }else {
            return new Results(0, "账号或密码错误");
        }
    }

    // 获取用户列表信息，包括分页功能
    @Override
    public AdminResults getAllUser(Integer offset, Integer limit) {
        return new AdminResults(0, "获取成功", adminDao.getAllUser(offset, limit), adminDao.getUserCount());
    }

    // 添加用户
    @Override
    public Results addUser(String username, String password, String nickname) {
        adminDao.addUser(username, MD5.crypt(password), nickname);
        return new Results(1, "添加成功");
    }

    // 删除用户
    @Override
    public Results deleteUser(int userID) {
        adminDao.deleteUser(userID);
        return new Results(1, "删除成功");
    }

    // 获取所有商品列表，同时分页
    @Override
    public AdminResults getAllShop(Integer offset, Integer limit) {
        return new AdminResults(0, "获取成功", adminDao.getAllShop(offset, limit), adminDao.getShopCount());
    }

    // 添加商品
    @Override
    public Results addShop(Shop shop) {
        adminDao.addShop(shop);
        return new Results(1, "添加成功");
    }

    // 修改商品信息
    @Override
    public Results changeShopInform(Shop shop) {
        adminDao.changeShopInform(shop);
        return new Results(1, "修改成功");
    }

    // 删除商品
    @Override
    public Results deleteShop(int shopID) {
        adminDao.deleteShop(shopID);
        return new Results(1, "删除成功");
    }

    // 获取用户购物车
    @Override
    public AdminResults getAllCart(Integer offset, Integer limit) {
        return new AdminResults(0, "获取成功", adminDao.getAllCart(offset, limit), adminDao.getCartCount());
    }

    // 获取主页的一些信息，获取商品总数和用户总数
    @Override
    public Results getIndex() {
        IndexPage indexPage = new IndexPage();
        Integer userCount = adminDao.getUserCount(); // 用户总数
        Integer shopCount = adminDao.getShopCount(); // 商品总数
        indexPage.setShop(shopCount);
        indexPage.setUser(userCount);
        return new Results(1, "获取成功", indexPage);
    }

    // 修改密码
    @Override
    public Results changePassword(String oldPassword, String newPassword) {
        Results results = adminLogin("admin", oldPassword);
        if(results.getCode() == 1){ // 旧密码正确
            adminDao.changePassword(MD5.crypt(newPassword));
            return new Results(1, "密码修改成功");
        }else {
            return new Results(0, "旧密码不正确");
        }
    }

    // 修改用户信息
    @Override
    public Results changeUserInfo(User user) {
        userDao.changeUserInfo(user);
        return new Results(1, "修改用户信息成功");
    }

}
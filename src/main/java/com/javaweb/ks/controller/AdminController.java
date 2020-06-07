package com.javaweb.ks.controller;


import com.javaweb.ks.dao.AdminDao;
import com.javaweb.ks.model.Shop;
import com.javaweb.ks.model.User;
import com.javaweb.ks.result.AdminResults;
import com.javaweb.ks.result.PageTableRequest;
import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.AdminService;
import com.javaweb.ks.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    // 管理员登录
    @PostMapping("/login")
    @ResponseBody
    public Results AdminLogin(String username, String password){
        return adminService.adminLogin(username, password);
    }

    // 获取用户列表信息，包括分页功能
    @GetMapping("/getAllUser")
    @ResponseBody
    public AdminResults getAllUser(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset(); // 先计算offset
        return adminService.getAllUser(pageTableRequest.getOffset(), pageTableRequest.getLimit());
    }

    // 添加用户
    @PostMapping("/addUser")
    @ResponseBody
    public Results addUser(String username, String password, String nickname){
        return adminService.addUser(username, password, nickname);
    }

    // 修改用户信息
    @PostMapping("/changeUser")
    @ResponseBody
    public Results changeInfo(int userID, User user){
        user.setID(userID);
        return adminService.changeUserInfo(user);
    }

    // 删除用户
    @PostMapping("/deleteUser")
    @ResponseBody
    public Results deleteUser(int userID){
        return adminService.deleteUser(userID);
    }
    // 获取所有商品列表，同时分页
    @GetMapping("/getAllShop")
    @ResponseBody
    public AdminResults getAllShop(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return adminService.getAllShop(pageTableRequest.getOffset(), pageTableRequest.getLimit());
    }

    // 添加商品
    @PostMapping("/addShop")
    @ResponseBody
    public Results addShop(Shop shop){
        return adminService.addShop(shop);
    }

    // 修改商品信息
    @PostMapping("/changeShop")
    @ResponseBody
    public Results changeShop(int shopID, Shop shop){
        shop.setID(shopID);
        return adminService.changeShopInform(shop);
    }

    // 删除商品
    @PostMapping("/deleteShop")
    @ResponseBody
    public Results deleteShop(int shopID){
        return adminService.deleteShop(shopID);
    }

    // 获取用户购物车
    @GetMapping("/getAllCart")
    @ResponseBody
    public AdminResults getAllCart(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return adminService.getAllCart(pageTableRequest.getOffset(), pageTableRequest.getLimit());
    }

    // 获取主页的一些信息，用户总数和商品总数
    @GetMapping("/getIndex")
    @ResponseBody
    public Results getIndex(){
        return adminService.getIndex();
    }

    // 修改管理员密码
    @PostMapping("/changePassword")
    @ResponseBody
    public Results changePassword(String oldPassword, String newPassword){
        return adminService.changePassword(oldPassword, newPassword);
    }

}

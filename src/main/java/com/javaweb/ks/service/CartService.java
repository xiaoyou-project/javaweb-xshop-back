package com.javaweb.ks.service;

import com.javaweb.ks.result.Results;

public interface CartService {

    // 加入购物车（相当于修改用户商品数量）number为1则加1,为-1则减一
    Results addCart(int shopID, int id, String token, int number);

    // 获取用户的购物车列表
    Results getUserCartsByUserId(int userID);

    // 删除用户购物车的商品
    Results deleteCart(int cartID);

    // 修改商品数量
    Results changeCartNum(int shopID, int id, int number);
}

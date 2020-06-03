package com.javaweb.ks.controller;


import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.CartService;
import com.javaweb.ks.util.TokenVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private TokenVerify tokenVerify;

    //加入购物车（相当于修改用户商品数量）number为1则加1,为-1则减一
    @PostMapping("/addCart")
    @ResponseBody
    public Results addCart(int shopID, int ID, String token, int number){
        return cartService.addCart(shopID, ID, token, number);
    }

    // 获取用户购物车
    @GetMapping("/getCart")
    @ResponseBody
    public Results getCart(int userID, String token){
        if(tokenVerify.tokenVerify(userID, token)){
            return cartService.getUserCartsByUserId(userID);
        }else {
            return new Results(0, "非法访问");
        }
    }

    // 删除用户购物车的商品
    @GetMapping("/deleteCart")
    @ResponseBody
    public Results deleteCart(int cartID, int userID, String token){
        if(tokenVerify.tokenVerify(userID, token)){
            return cartService.deleteCart(cartID);
        }else {
            return new Results(0, "非法访问");
        }
    }


}

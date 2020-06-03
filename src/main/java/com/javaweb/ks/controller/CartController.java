package com.javaweb.ks.controller;


import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //加入购物车（相当于修改用户商品数量）number为1则加1,为-1则减一
    @GetMapping("/addCart")
    @ResponseBody
    public Results addCart(int shopID, int ID, String token, int number){
        return cartService.addCart(shopID, ID, token, number);
    }

}

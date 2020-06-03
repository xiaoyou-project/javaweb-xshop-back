package com.javaweb.ks.controller;


import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.CartService;
import com.javaweb.ks.util.TokenVerify;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Results addCart(@RequestParam("shopID") int shopID, @RequestParam("ID") int ID, @RequestParam("token") String token,@RequestParam("number") int number){
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
    @PostMapping("/deleteCart")
    @ResponseBody
    public Results deleteCart(int cartID, int userID, String token){
        if(tokenVerify.tokenVerify(userID, token)){
            return cartService.deleteCart(cartID);
        }else {
            return new Results(0, "非法访问");
        }
    }

    // 修改购物车商品数量
    @PostMapping("/changeCartNum")
    @ResponseBody
    public Results changeCartNum(@RequestParam("shopID") int shopID, @RequestParam("ID") int ID, @RequestParam("token") String token,@RequestParam("number") int number){
        if(tokenVerify.tokenVerify(ID, token)){
            return cartService.changeCartNum(shopID, ID, number);
        }else {
            return new Results(0, "非法访问");
        }
    }


}

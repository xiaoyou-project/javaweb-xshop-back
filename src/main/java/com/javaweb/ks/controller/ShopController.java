package com.javaweb.ks.controller;

import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品controller
 */
@Controller
@Slf4j
@RequestMapping("api/v1/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    //获取商品详情信息
    @GetMapping("/getInfo")
    @ResponseBody
    public Results getInfo(int shopID){
        return shopService.getShopInfo(shopID);
    }



}

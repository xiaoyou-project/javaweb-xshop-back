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
        log.info("商品id" + shopID);
        return shopService.getShopInfo(shopID);
    }

    // 获取所有商品信息
    @GetMapping("/getAllShop")
    @ResponseBody
    public Results getAllShop(){
        return shopService.getAllShop();
    }
    // 获取某种类型商品的列表页面
    @GetMapping("/getShopList")
    @ResponseBody
    public Results getShopList(int type){
        return shopService.getShopList(type);
    }

    // 通过商品名字模糊查询
    @GetMapping("/search")
    @ResponseBody
    public Results search(String key){
        return shopService.searchShop(key);
    }

}

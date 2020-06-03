package com.javaweb.ks.service;

import com.javaweb.ks.result.Results;

public interface ShopService {
    // 获取商品详情信息
    Results getShopInfo(int shopID);

    // 获取所有商品信息
    Results getAllShop();

    // 获取某种类型商品的列表页面
    Results getShopList(int type);

    // 通过商品名字模糊查询
    Results searchShop(String key);
}

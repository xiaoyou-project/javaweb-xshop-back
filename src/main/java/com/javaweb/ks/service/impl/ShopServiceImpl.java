package com.javaweb.ks.service.impl;

import com.javaweb.ks.dao.ShopDao;
import com.javaweb.ks.model.Shop;
import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;


    // 获取商品详情信息
    @Override
    public Results getShopInfo(int shopID) {
        return new Results(1, "获取成功", shopDao.getShopInfoById(shopID));
    }

    // 获取所有商品
    @Override
    public Results getAllShop() {
        List<Shop> AllShop = shopDao.getAllShop();
        if(AllShop.size() == 0){
            return new Results(0, "获取失败");
        }else {
            return new Results(1, "获取成功", AllShop);
        }
    }

    //获取某种类型商品的列表页面
    @Override
    public Results getShopList(int type) {
        List<Shop> shops = shopDao.getShopList(type);
        if(shops.size() == 0){
            return new Results(0, "获取失败");
        }else {
            return new Results(1, "获取成功", shops);
        }
    }

    // // 通过商品名字模糊查询
    @Override
    public Results searchShop(String key) {
        return new Results(1, "获取成功", shopDao.searchShopByShopName(key));
    }

}

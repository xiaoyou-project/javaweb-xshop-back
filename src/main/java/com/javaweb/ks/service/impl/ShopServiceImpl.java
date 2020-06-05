package com.javaweb.ks.service.impl;

import com.javaweb.ks.dao.ShopDao;
import com.javaweb.ks.model.Carousel;
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
        List<Shop> shops = shopDao.getShopList(type); // 获取商品列表
        List<Carousel> carousels = shopDao.getCarouselList(type); // 获取某种商品列表的最上面的轮播图
        String carousel = ""; // 存储轮播图的图片
        if(carousels.size() < 1){
            carousel = ""; // 轮播图中没有图片，则随便放一张
        }else {
            for(int i = 0; i < carousels.size(); i ++){ // 拼接图片
                if(i == 0){
                    carousel = carousels.get(0).getImg();
                }else{
                    carousel = carousel + "&&" + carousels.get(i).getImg();
                }
            }
        }

        if(shops.size() == 0){
            return new Results(0, "获取失败");
        }else {
            return new Results(1, "获取成功", shops, carousel);
        }
    }

    // // 通过商品名字模糊查询
    @Override
    public Results searchShop(String key) {
        return new Results(1, "获取成功", shopDao.searchShopByShopName(key));
    }

}

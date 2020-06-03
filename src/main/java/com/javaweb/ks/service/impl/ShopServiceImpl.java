package com.javaweb.ks.service.impl;

import com.javaweb.ks.dao.ShopDao;
import com.javaweb.ks.model.Cart;
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

}

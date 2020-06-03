package com.javaweb.ks.dao;

import com.javaweb.ks.model.Cart;
import com.javaweb.ks.model.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopDao {


    // 根据商品id获取商品详情信息
    @Select("select * from shop where ID = #{shopID} ")
    Shop getShopInfoById(int shopID);


}

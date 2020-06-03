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

    // 获取所有商品
    @Select("select * from shop")
    List<Shop> getAllShop();

    // 获取某种类型商品的列表页面
    @Select("select * from shop where sort = #{type} ")
    List<Shop> getShopList(int type);

    // 通过商品名字模糊查询
    @Select("select * from shop where name like '%${key}%'")
    List<Shop> searchShopByShopName(String key);
}

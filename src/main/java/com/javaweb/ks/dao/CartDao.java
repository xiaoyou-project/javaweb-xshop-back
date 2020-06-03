package com.javaweb.ks.dao;

import com.javaweb.ks.model.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CartDao {

    // 通过用户id获取用户的购物车
    @Select("select * from cart where user_id = #{id} ")
    List<Cart> getCartsByUserID(int id);

    // 修改指定购物车商品的数量
    @Update("update cart set count = #{i} where ID = #{id}")
    void changeShopNum(int id, int i);

    // 添加商品到购物车
    @Insert("insert into cart(user_id, shop_id, count) values(#{id}, #{shopID}, #{number}) ")
    void addCart(int shopID, int id, int number);
}

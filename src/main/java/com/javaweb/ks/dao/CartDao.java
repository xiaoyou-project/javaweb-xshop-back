package com.javaweb.ks.dao;

import com.javaweb.ks.dto.ShopCart;
import com.javaweb.ks.model.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartDao {

    // 通过用户id获取用户的购物车
    @Select("select t1.ID as cart_id,t2.ID as shop_id, t2.img, t2.name as shop_name, t2.price, t1.count from cart t1 INNER JOIN shop t2 on t1.shop_id = t2.ID where t1.user_id = #{id}")
    @Results(value={
            @Result(column = "cart_id", property = "cartId"),
            @Result(column = "shop_id", property = "shopId"),
            @Result(column = "img", property = "img"),
            @Result(column = "shop_name", property = "shopName"),
            @Result(column = "price", property = "price"),
            @Result(column = "count", property = "count")
    })
    List<ShopCart> getCartsByUserID(int id);

    // 修改指定购物车商品的数量
    @Update("update cart set count = #{i} where ID = #{id}")
    void changeShopNum(int id, int i);

    // 添加商品到购物车
    @Insert("insert into cart(user_id, shop_id, count) values(#{id}, #{shopID}, #{number}) ")
    void addCart(int shopID, int id, int number);

    // 删除用户购物车的商品
    @Delete("delete from cart where ID = #{cartID} ")
    void deleteCartByC(int cartID);

    // 修改商品数量
    @Update("update cart set count = #{number} where shop_id = #{shopID} and user_id = #{id} ")
    void changeCartNum(int shopID, int id, int number);
}

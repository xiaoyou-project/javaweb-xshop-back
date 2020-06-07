package com.javaweb.ks.dao;

import com.javaweb.ks.dto.IndexPage;
import com.javaweb.ks.dto.ShopCart;
import com.javaweb.ks.model.Shop;
import com.javaweb.ks.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminDao {

    // 获取管理员的密码
    @Select("select password from admin where username = #{username} ")
    String getAdminPass(String username);

    // 获取所有用户信息，同时分页
    @Select("select * from user limit #{limit} offset #{offset}")
    List<User> getAllUser(Integer offset, Integer limit);

    // 获取用户总数
    @Select("select count(username) from user ")
    Integer getUserCount();

    // 添加用户
    @Insert("insert into user(username, password, nickname, site) values(#{username}, #{password}, #{nickname}, '中国')")
    void addUser(String username, String password, String nickname);

    // 删除用户
    @Delete("delete from user where ID = #{userID} ")
    void deleteUser(int userID);

    // 获取商品总数
    @Select("select count(ID) from shop")
    Integer getShopCount();

    // 获取所有商品信息，同时分页
    @Select("select * from shop limit #{limit} offset #{offset} ")
    List<Shop> getAllShop(Integer offset, Integer limit);

    // 添加商品
    @Insert("insert into shop(name, price, old_price, description, img, sort, other) values(#{name}, #{price}, #{oldPrice}, #{description}, #{img}, #{sort}, #{other})")
    void addShop(Shop shop);

    // 修改商品信息
    void changeShopInform(Shop shop);

    // 删除商品
    @Delete("delete from shop where ID = #{shopID}")
    void deleteShop(int shopID);

    // 获取购物车总数
    @Select("select count(user_id) from cart")
    Integer getCartCount();

    // 获取所有购物车
    @Select("select t1.*, t2.nickname " +
            "from  " +
            "(select cart.ID as cartId,cart.shop_id as shopId, shop.img, shop.name as shopName, shop.price, shop.old_price as oldPrice, cart.count, cart.user_id " +
            "from cart LEFT JOIN shop on cart.shop_id = shop.ID) " +
            " as t1 LEFT JOIN `user` as t2  " +
            "on t1.user_id = t2.ID ")
    List<ShopCart> getAllCart(Integer offset, Integer limit);

    // 修改管理员密码
    @Update("update admin set password = #{crypt} where username = 'admin' ")
    void changePassword(String crypt);
}

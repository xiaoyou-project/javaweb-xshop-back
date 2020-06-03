package com.javaweb.ks.service.impl;

import com.javaweb.ks.dao.CartDao;
import com.javaweb.ks.model.Cart;
import com.javaweb.ks.result.Results;
import com.javaweb.ks.service.CartService;
import com.javaweb.ks.util.TokenVerify;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private TokenVerify tokenVerify;



    // 加入购物车（相当于修改用户商品数量）number为1则加1,为-1则减一
    /**
     * 如果购物车有了这个商品，并且number为1，则购物车的这个商品加1
     *                     如果number是-1，则购物车这个商品减一
     * 如果购物车没有这个商品，则将商品放入购物车
     * @param shopID
     * @param id
     * @param token
     * @param number
     * @return
     */
    @Override
    public Results addCart(int shopID, int id, String token, int number) {
        if (tokenVerify.tokenVerify(id, token)) { // token对了
            int flag = 0;// 为0表示没有这个商品
            // 先获取用户的购物车
            List<Cart> userCarts = cartDao.getCartsByUserID(id);
            // 循环遍历这个List集合看里面有没有这个shopID的商品
            for (Cart cart : userCarts) {
                if (cart.getShopId() == shopID) { // 有这个商品
                    // 修改商品的数量
                    cartDao.changeShopNum(cart.getID(), number == 1 ? (cart.getCount() + 1) : (cart.getCount() - 1));
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                return new Results(1, "修改商品数量成功");
            } else { // 添加购物车
                cartDao.addCart(shopID, id, number);
                return null;
            }
        }else {
            return new Results(0, "非法访问");
        }
    }
}

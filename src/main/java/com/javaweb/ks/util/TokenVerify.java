package com.javaweb.ks.util;

// 验证token的工具类

import com.javaweb.ks.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenVerify {

    @Autowired
    UserDao userDao;

    public Boolean tokenVerify(int ID, String token){
        String userToken = userDao.getTokenByUserId(ID);
        if(token.equals(userToken)){
            return true;
        }else {
            return false;
        }
    }

}

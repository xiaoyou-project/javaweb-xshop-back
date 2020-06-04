package com.javaweb.ks.dao;

import com.javaweb.ks.model.User;
import com.javaweb.ks.result.Results;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {


    // 根据用户id查询用户
    @Select("select * from user where id = #{id} ")
    User getUserById(Integer id);

    // 通过用户的username查询用户的数量
    @Select("select count(*) from user where username = #{username}")
    int getUserByUsername(String username);

    // 用户注册，插入用户
    @Insert("insert into user(username, password, nickname, site) values(#{username}, #{password}, #{nickname}, '中国')")
    int registerUser(String username, String password, String nickname);

    // 用户登录功能

    // 通过username获取用户的密码
    @Select("select t.password from user t where t.username = #{username}")
    String getUserPassByUsername(String username);

    // 插入token到数据库中
    @Update("update user set token = #{token} where username = #{username} ")
    void insertToken(String username, String token);

    // 通过用户的username获取用户的ID
    @Select("select ID from user where username = #{username} ")
    int getUserIdByUsername(String username);

    // 通过用户ID获取token
    @Select("select token from user where ID = #{ID} ")
    String getTokenByUserId(int ID);

    // 插入用户登录的时间
    @Update("update user set last_login = now() where username = #{username}")
    void insertLoginTime(String username);


    //

    // 通过用户id获取用户信息
    @Select("select t.username, t.nickname, t.avatar, t.site, t.sign, t.last_login from user t where t.ID = #{userID}")
    User getUserInfoById(int userID);

    // 修改用户数据， 使用的xml文件配置
    void changeUserInfo(User user);

    // 修改用户密码
    @Update("update user set password = #{crypt} where ID = #{id}")
    void changePassword(int id, String crypt);

    // 获取用户地址
    @Select("select site from user where ID = #{userID} ")
    String getUserSite(int userID);
}

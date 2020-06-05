package com.javaweb.ks.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PicDao {

    // 上传图片
    @Update("update user set avatar = #{avatar} where ID = #{userID} ")
    void add(String avatar, int userID);

}

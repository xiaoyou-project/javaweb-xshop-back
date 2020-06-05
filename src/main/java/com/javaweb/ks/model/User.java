package com.javaweb.ks.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int ID;
    private String username;
    private String nickname;
    private String password;
    private String avatar; // 图片的id
    private String sign;
    private String site;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;
    private Date nowLogin;
    private String token; // 用于登录的时候用，安全令牌

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sign='" + sign + '\'' +
                ", site='" + site + '\'' +
                ", lastLogin=" + lastLogin +
                ", nowLogin=" + nowLogin +
                ", token='" + token + '\'' +
                '}';
    }
}

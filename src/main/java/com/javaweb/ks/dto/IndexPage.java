package com.javaweb.ks.dto;


import lombok.Data;

/**
 * 主页的一些信息
 */
@Data
public class IndexPage {

    private Integer shop; // 商品总数
    private Integer user; // 用户总数

}

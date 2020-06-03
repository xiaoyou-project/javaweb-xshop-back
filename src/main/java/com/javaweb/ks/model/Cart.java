package com.javaweb.ks.model;

import lombok.Data;

@Data
public class Cart {

    private int ID;
    private int userId;
    private int shopId;
    private int count;
    private int collectType;

}

package com.javaweb.ks.model;

import lombok.Data;

@Data
public class Shop {

    private int ID;
    private String name;
    private float price;
    private float oldPrice;
    private String description;
    private String img;
    private int sort;
    private String other;

}

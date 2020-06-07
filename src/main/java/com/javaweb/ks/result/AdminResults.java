package com.javaweb.ks.result;


import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class AdminResults<T> implements Serializable {

    Integer code;
    String msg;
    Integer count;
    List<T> data;

    public AdminResults(Integer code, String msg, List<T> data, Integer count){
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }


}

package com.javaweb.ks.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

// 封装结果集

@Data
public class Results<T> implements Serializable {

    Integer code;
    String msg;
    List<T> datas;
    T data;

    public Results(Integer code, String msg){ // 没有返回值
        this.code = code;
        this.msg = msg;
    }

    public Results(Integer code, String msg, T data){ // data不是数组的
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Results(Integer code, String msg, List<T> datas){ // datas是数组的
        this.code = code;
        this.msg = msg;
        this.datas = datas;
    }

    public Results(Integer code, String msg, List<T> datas, T data){ // datas是数组的，data也有的情况
        this.code = code;
        this.msg = msg;
        this.datas = datas;
        this.data = data;
    }


}

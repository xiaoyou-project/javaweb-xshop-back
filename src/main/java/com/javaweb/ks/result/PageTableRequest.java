package com.javaweb.ks.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageTableRequest implements Serializable {// 封装分页相关的需要的参数

    private Integer page;
    private Integer limit;
    private Integer offset;

    public void countOffset(){
        if(this.page == null || this.limit == null){
            this.offset = 0;
        }else{
            this.offset = (this.page - 1) * this.limit;
        }
    }
}


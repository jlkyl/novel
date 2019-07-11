package com.qianqiu.novel.entity;

import lombok.Data;

import java.util.List;

@Data
public class Layui {
    private Integer code=0;
    private String msg;
    private Integer count;
    private Integer limits=5;
    private List<?> data;
}

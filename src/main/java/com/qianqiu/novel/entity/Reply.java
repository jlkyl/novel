package com.qianqiu.novel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {

    private Integer replyid;
    private Integer userid;
    private Integer evaid;
    private String content;
    private Date replytime;

}

package com.qianqiu.novel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Emps {
    private Integer empid;
    private String  uname;
    private String pwd;
    private String empname;
    private String phone;
    private String idcard;
    private Integer roleid;
    private Integer state;
    private Integer operateeid;
    private Date operatedate;
}

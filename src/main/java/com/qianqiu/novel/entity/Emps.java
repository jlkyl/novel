package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operatedate;
}

package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Complaint {
    private Integer comid;
    private Integer userid;
    private Integer chapid;
    private String content;
    private Integer state;
    private Date comtime;
    private Integer operateeid;
    private Date operatedate;
}

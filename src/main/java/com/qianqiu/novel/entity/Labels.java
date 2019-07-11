package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Labels {

  private Integer labelid;
  private String labelname;
  private Integer operateeid;
  private Date operatedate;
}

package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Expnses {
    public Integer expid;
    public Integer userid;
    public Integer bookid;
    public Integer chapid;
    public Integer exptypeid;
    public Integer expmoney;
    public Date exptime;
    public Double money;
}

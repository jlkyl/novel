package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Expnses {
    public Integer expid;
    public Integer userid;
    public Integer bookid;
    public Integer chapid;
    public Integer exptypeid;
    public Double expmoney;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date exptime;
    public Double money;

    public Integer getExpid() {
        return expid;
    }

    public void setExpid(Integer expid) {
        this.expid = expid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getChapid() {
        return chapid;
    }

    public void setChapid(Integer chapid) {
        this.chapid = chapid;
    }

    public Integer getExptypeid() {
        return exptypeid;
    }

    public void setExptypeid(Integer exptypeid) {
        this.exptypeid = exptypeid;
    }

    public Double getExpmoney() {
        return expmoney;
    }

    public void setExpmoney(Double expmoney) {
        this.expmoney = expmoney;
    }

    public Date getExptime() {
        return exptime;
    }

    public void setExptime(Date exptime) {
        this.exptime = exptime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Expnses{" +
                "expid=" + expid +
                ", userid=" + userid +
                ", bookid=" + bookid +
                ", chapid=" + chapid +
                ", exptypeid=" + exptypeid +
                ", expmoney=" + expmoney +
                ", exptime=" + exptime +
                ", money=" + money +
                '}';
    }
}

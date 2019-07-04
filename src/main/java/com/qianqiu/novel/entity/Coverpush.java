package com.qianqiu.novel.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Coverpush {
    public Integer pushid;
    public Integer bookid;
    public String cover;
    public Integer operateeid;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date operatedate;

    public String bookname;

    public Integer getPushid() {
        return pushid;
    }

    public void setPushid(Integer pushid) {
        this.pushid = pushid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getOperateeid() {
        return operateeid;
    }

    public void setOperateeid(Integer operateeid) {
        this.operateeid = operateeid;
    }

    public Date getOperatedate() {
        return operatedate;
    }

    public void setOperatedate(Date operatedate) {
        this.operatedate = operatedate;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Override
    public String toString() {
        return "Coverpush{" +
                "pushid=" + pushid +
                ", bookid=" + bookid +
                ", cover='" + cover + '\'' +
                ", operateeid=" + operateeid +
                ", operatedate=" + operatedate +
                ", bookname='" + bookname + '\'' +
                '}';
    }
}

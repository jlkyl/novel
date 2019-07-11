package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Notices {

    public Integer noticeid;
    public String title;
    public String  content;
    public Integer operateeid;
    public Date opeartedate;

    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOperateeid() {
        return operateeid;
    }

    public void setOperateeid(Integer operateeid) {
        this.operateeid = operateeid;
    }

    public Date getOpeartedate() {
        return opeartedate;
    }

    public void setOpeartedate(Date opeartedate) {
        this.opeartedate = opeartedate;
    }

    @Override
    public String toString() {
        return "notices{" +
                "noticeid=" + noticeid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", operateeid=" + operateeid +
                ", opeartedate=" + opeartedate +
                '}';
    }
}

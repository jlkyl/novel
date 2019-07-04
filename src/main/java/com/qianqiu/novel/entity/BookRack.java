package com.qianqiu.novel.entity;

public class BookRack {
    private Integer rackid;
    private Integer bookid;
    private Integer userid;

    public BookRack() {
        super();
    }

    public BookRack(Integer rackid, Integer bookid, Integer userid) {
        this.rackid = rackid;
        this.bookid = bookid;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "BookRack{" +
                "rackid=" + rackid +
                ", bookid=" + bookid +
                ", userid=" + userid +
                '}';
    }

    public Integer getRackid() {
        return rackid;
    }

    public void setRackid(Integer rackid) {
        this.rackid = rackid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}

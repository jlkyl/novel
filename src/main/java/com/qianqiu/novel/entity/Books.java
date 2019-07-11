package com.qianqiu.novel.entity;

import java.io.Serializable;
import java.util.Date;

public class Books implements Serializable {
	private Integer bookid;
	private String bookname;
	private String cover;
	private Integer userid;
	private Integer typeid;
	private Integer state;
	private Integer putaway;
	private String details;
	private Date createtime;
	private Date endtime;
	private Integer clicknum;
	private String url;

	public Books(){}

	public Books(Integer bookid,String bookname,String cover,Integer userid,Integer typeid,Integer state,Integer putaway,String details,Date createtime,Date endtime,Integer clicknum,String url){
		this.bookid = bookid;
		this.bookname = bookname;
		this.cover = cover;
		this.userid = userid;
		this.typeid = typeid;
		this.state = state;
		this.putaway = putaway;
		this.details = details;
		this.createtime = createtime;
		this.endtime = endtime;
		this.clicknum = clicknum;
		this.url = url;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPutaway() {
		return putaway;
	}

	public void setPutaway(Integer putaway) {
		this.putaway = putaway;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getClicknum() {
		return clicknum;
	}

	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toString() {
		return "Books [bookid="+bookid+", bookname="+bookname+", cover="+cover+", userid="+userid+", typeid="+typeid+", state="+state+", putaway="+putaway+", details="+details+", createtime="+createtime+", endtime="+endtime+", clicknum="+clicknum+", url="+url+"]";
	}
}

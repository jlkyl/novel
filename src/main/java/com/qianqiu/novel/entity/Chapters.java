package com.qianqiu.novel.entity;

import java.io.Serializable;

import java.util.Date;

public class Chapters implements Serializable {
	private Integer chapterid;
	private String chaptername;
	private String url;
	private Integer chapternum;
	private Integer state;
	private Integer wordnum;
	private Integer isvip;
	private Date updatetime;
	private Integer rollid;

	public Chapters(){}

	public Chapters(Integer chapterid,String chaptername,String url,Integer chapternum,Integer state,Integer wordnum,Integer isvip,Date updatetime,Integer rollid){
		this.chapterid = chapterid;
		this.chaptername = chaptername;
		this.url = url;
		this.chapternum = chapternum;
		this.state = state;
		this.wordnum = wordnum;
		this.isvip = isvip;
		this.updatetime = updatetime;
		this.rollid = rollid;
	}
	public Integer getChapterid(){
		return chapterid;
	}

	public void setChapterid(Integer chapterid){
		this.chapterid=chapterid;
 	}
	public String getChaptername(){
		return chaptername;
	}

	public void setChaptername(String chaptername){
		this.chaptername=chaptername;
 	}
	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
 	}
	public Integer getChapternum(){
		return chapternum;
	}

	public void setChapternum(Integer chapternum){
		this.chapternum=chapternum;
 	}
	public Integer getState(){
		return state;
	}

	public void setState(Integer state){
		this.state=state;
 	}
	public Integer getWordnum(){
		return wordnum;
	}

	public void setWordnum(Integer wordnum){
		this.wordnum=wordnum;
 	}
	public Integer getIsvip(){
		return isvip;
	}

	public void setIsvip(Integer isvip){
		this.isvip=isvip;
 	}
	public Date getUpdatetime(){
		return updatetime;
	}

	public void setUpdatetime(Date updatetime){
		this.updatetime=updatetime;
 	}
	public Integer getRollid(){
		return rollid;
	}

	public void setRollid(Integer rollid){
		this.rollid=rollid;
 	}
	public String toString() {
		return "Chapters [chapterid="+chapterid+", chaptername="+chaptername+", url="+url+", chapternum="+chapternum+", state="+state+", wordnum="+wordnum+", isvip="+isvip+", updatetime="+updatetime+", rollid="+rollid+"]";
	}
}

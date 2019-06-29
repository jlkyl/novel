package com.qianqiu.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Roles implements Serializable {
	private Integer roleid;
	private String rolename;
	private Integer operateeid;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date operatedate;
	private String empname;
	private List<Menus> menus = new ArrayList<Menus>();

	public Roles(){}

	public Roles(Integer roleid,String rolename,Integer operateeid,Date operatedate){
		this.roleid = roleid;
		this.rolename = rolename;
		this.operateeid = operateeid;
		this.operatedate = operatedate;
	}
	public Integer getRoleid(){
		return roleid;
	}

	public void setRoleid(Integer roleid){
		this.roleid=roleid;
 	}
	public String getRolename(){
		return rolename;
	}

	public void setRolename(String rolename){
		this.rolename=rolename;
 	}
	public Integer getOperateeid(){
		return operateeid;
	}

	public void setOperateeid(Integer operateeid){
		this.operateeid=operateeid;
 	}
	public Date getOperatedate(){
		return operatedate;
	}

	public void setOperatedate(Date operatedate){
		this.operatedate=operatedate;
 	}

	public List<Menus> getMenus() {
		return menus;
	}

	public void setMenus(List<Menus> menus) {
		this.menus = menus;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String toString() {
		return "Roles [roleid="+roleid+", rolename="+rolename+", operateeid="+operateeid+", operatedate="+operatedate+"]";
	}
}

package com.qianqiu.novel.entity;

import java.util.Date;

public class Booktype {

    private Integer typeid;
    private String typename;
    private String icon;
    private Integer parentid;
    private Integer operateeid;
    private Date operatedate;
    private Integer _parentid;

    public Booktype() {
        super();
    }

    public Booktype(Integer typeid, String typename, String icon, Integer parentid, Integer operateeid, Date operatedate) {
        this.typeid = typeid;
        this.typename = typename;
        this.icon = icon;
        this.parentid = parentid;
        this.operateeid = operateeid;
        this.operatedate = operatedate;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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

    public Integer get_parentid() {
        return _parentid;
    }

    public void set_parentid(Integer _parentid) {
        this._parentid = _parentid;
    }

    @Override
    public String toString() {
        return "Booktype{" +
                "typeid=" + typeid +
                ", typename='" + typename + '\'' +
                ", icon='" + icon + '\'' +
                ", parentid=" + parentid +
                ", operateeid=" + operateeid +
                ", operatedate=" + operatedate +
                '}';
    }
}

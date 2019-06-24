package com.qianqiu.novel.entity;

public class Emps {
    private Integer empid;
    private String  uname;
    private String pwd;
    private String empname;
    private String phone;
    private String idcard;
    private String roleid;
    private String state;
    private String operateeid;
    private String operatedate;

    public Emps() {
        super();
    }

    public Emps(Integer empid, String uname, String pwd, String empname, String phone, String idcard, String roleid, String state, String operateeid, String operatedate) {
        this.empid = empid;
        this.uname = uname;
        this.pwd = pwd;
        this.empname = empname;
        this.phone = phone;
        this.idcard = idcard;
        this.roleid = roleid;
        this.state = state;
        this.operateeid = operateeid;
        this.operatedate = operatedate;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOperateeid() {
        return operateeid;
    }

    public void setOperateeid(String operateeid) {
        this.operateeid = operateeid;
    }

    public String getOperatedate() {
        return operatedate;
    }

    public void setOperatedate(String operatedate) {
        this.operatedate = operatedate;
    }

    @Override
    public String toString() {
        return "Emps{" +
                "empid=" + empid +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", empname='" + empname + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", roleid='" + roleid + '\'' +
                ", state='" + state + '\'' +
                ", operateeid='" + operateeid + '\'' +
                ", operatedate='" + operatedate + '\'' +
                '}';
    }
}

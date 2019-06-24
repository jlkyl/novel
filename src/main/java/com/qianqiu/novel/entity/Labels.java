package com.qianqiu.novel.entity;

import java.util.Date;

public class Labels {

  private Integer labelid;
  private String labelname;
  private Integer operateeid;
  private Date operatedate;


  public Integer getLabelid() {
    return labelid;
  }

  public void setLabelid(Integer labelid) {
    this.labelid = labelid;
  }


  public String getLabelname() {
    return labelname;
  }

  public void setLabelname(String labelname) {
    this.labelname = labelname;
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

}

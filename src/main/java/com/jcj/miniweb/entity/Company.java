package com.jcj.miniweb.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 江成军
 * @Date: 2019/07/16 10:17
 * @Description: 公司实体
 */
@Entity
@Table(name = "company")
public class Company {
  @Id
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(length = 32)
  private String uuid;

  @Column(length = 60)
  private String cname;

  @Column(length = 120)
  private String caddress;


  @Column(length = 120)
  private String curl;

  @Column(length = 60)
  private String cemail;

  @Column
  private int cpersonnum;

  @Column(columnDefinition = "double(10,4) default '0.00'")
  private float totalincome;

  @Column(length = 10)
  private String legalpersonname;

  @Column(columnDefinition = "char(11)")
  private String legalpersonmobil;

  @Column(length = 120)
  private String businesslicense;

  @Column(columnDefinition = "text")
  private String cbrief;

  @Column(columnDefinition = "char(19)")
  private String createtime;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }

  public String getCaddress() {
    return caddress;
  }

  public void setCaddress(String caddress) {
    this.caddress = caddress;
  }

  public String getCurl() {
    return curl;
  }

  public void setCurl(String curl) {
    this.curl = curl;
  }

  public String getCemail() {
    return cemail;
  }

  public void setCemail(String cemail) {
    this.cemail = cemail;
  }

  public int getCpersonnum() {
    return cpersonnum;
  }

  public void setCpersonnum(int cpersonnum) {
    this.cpersonnum = cpersonnum;
  }

  public float getTotalincome() {
    return totalincome;
  }

  public void setTotalincome(float totalincome) {
    this.totalincome = totalincome;
  }

  public String getLegalpersonname() {
    return legalpersonname;
  }

  public void setLegalpersonname(String legalpersonname) {
    this.legalpersonname = legalpersonname;
  }

  public String getLegalpersonmobil() {
    return legalpersonmobil;
  }

  public void setLegalpersonmobil(String legalpersonmobil) {
    this.legalpersonmobil = legalpersonmobil;
  }

  public String getBusinesslicense() {
    return businesslicense;
  }

  public void setBusinesslicense(String businesslicense) {
    this.businesslicense = businesslicense;
  }

  public String getCbrief() {
    return cbrief;
  }

  public void setCbrief(String cbrief) {
    this.cbrief = cbrief;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }
}
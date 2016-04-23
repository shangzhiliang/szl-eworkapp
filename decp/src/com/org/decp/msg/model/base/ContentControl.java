package com.org.decp.msg.model.base;

import org.hibernate.validator.constraints.NotBlank;

import com.thoughtworks.xstream.annotations.XStreamAlias;


public class ContentControl
{

  @XStreamAlias("isZip")
  @NotBlank
  private String isZip;

  @XStreamAlias("zipType")
  @NotBlank
  private String zipType;

  @XStreamAlias("isEncrypt")
  @NotBlank
  private String isEncrypt;

  @XStreamAlias("encrytType")
  private String encrytType;

  public String getIsZip()
  {
    return this.isZip;
  }

  public void setIsZip(String isZip) {
    this.isZip = isZip;
  }

  public String getZipType() {
    return this.zipType;
  }

  public void setZipType(String zipType) {
    this.zipType = zipType;
  }

  public String getIsEncrypt() {
    return this.isEncrypt;
  }

  public void setIsEncrypt(String isEncrypt) {
    this.isEncrypt = isEncrypt;
  }

  public String getEncrytType() {
    return this.encrytType;
  }

  public void setEncrytType(String encrytType) {
    this.encrytType = encrytType;
  }
}
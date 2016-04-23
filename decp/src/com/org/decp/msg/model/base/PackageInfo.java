package com.org.decp.msg.model.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;


public class PackageInfo
{

  @XStreamAlias("sequence")
  private Integer sequence = Integer.valueOf(1);

  @XStreamAlias("content")
  private String content;

  @XStreamAlias("paramList")
  private String paramList;

  public Integer getSequence()
  {
    return this.sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public String getParamList() {
    return this.paramList;
  }

  public void setParamList(String paramList) {
    this.paramList = paramList;
  }
}
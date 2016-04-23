package org.zwork.framework.listener.impl;

import java.util.List;

public class WebAppInitConfig
{
  private List<WebAppInit> webAppInits;

  public List<WebAppInit> getWebAppInits()
  {
    return this.webAppInits;
  }

  public void setWebAppInits(List<WebAppInit> webAppInits) {
    this.webAppInits = webAppInits;
  }
}
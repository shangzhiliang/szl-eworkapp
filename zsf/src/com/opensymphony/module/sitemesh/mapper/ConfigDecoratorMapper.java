package com.opensymphony.module.sitemesh.mapper;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;


public class ConfigDecoratorMapper extends AbstractDecoratorMapper
{
  private ConfigLoader configLoader = null;

  private static final Logger LOG = LoggerFactory.getLogger(ConfigDecoratorMapper.class);

  public void init(Config config, Properties properties, DecoratorMapper parent)
    throws InstantiationException
  {
    super.init(config, properties, parent);
    try {
      String fileName = properties.getProperty("config", 
        "/WEB-INF/decorators.xml");
      this.configLoader = new ConfigLoader(fileName, config);
    } catch (Exception e) {
      throw new InstantiationException(e.toString());
    }
  }

  public Decorator getDecorator(HttpServletRequest request, Page page)
  {
    String thisPath = request.getServletPath();

    LOG.debug("sitemesh info: 请求路径(ServletPath)[{}]", thisPath);

    if (thisPath == null) {
      String requestURI = request.getRequestURI();
      LOG.debug("sitemesh info: 请求路径(requestURI)[{}]", requestURI);
      if (request.getPathInfo() != null)
      {
        thisPath = requestURI.substring(0, requestURI.indexOf(request
          .getPathInfo()));
      }
      else thisPath = requestURI;
    }
    else if ("".equals(thisPath))
    {
      thisPath = request.getPathInfo();
    }
    LOG.debug("sitemesh info: 当前请求路径[{}]", thisPath);
    String name = null;
    try {
      name = this.configLoader.getMappedName(thisPath);
      LOG.debug("sitemesh info: 装饰器名称[{}]", name);
    } catch (ServletException e) {
      e.printStackTrace();
    }
    if ((name == null) || ("".equals(name.trim()))) {
      LOG.debug("sitemesh info: 请求路径[{}]未找到对应的装饰器", thisPath);
    }
    Decorator result = getNamedDecorator(request, name);
    return result == null ? super.getDecorator(request, page) : result;
  }

  public Decorator getNamedDecorator(HttpServletRequest request, String name)
  {
    Decorator result = null;
    try {
      result = this.configLoader.getDecoratorByName(name);
    } catch (ServletException e) {
      e.printStackTrace();
    }

    if ((result == null) || (
      (result.getRole() != null) && 
      (!request.isUserInRole(result
      .getRole()))))
    {
      return super.getNamedDecorator(request, name);
    }
    return result;
  }
}
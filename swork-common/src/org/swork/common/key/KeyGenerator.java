package org.swork.common.key;

import java.util.Map;

import org.swork.common.key.config.KeyGeneratorConfig;

public abstract interface KeyGenerator
{
  public abstract String getNextKey(String paramString);

  public abstract String getNextKey(String paramString, Map<String, Object> paramMap);

  public abstract void setConfig(KeyGeneratorConfig paramKeyGeneratorConfig);
}
package org.swork.common.key.config;

import java.util.Map;

import javax.sql.DataSource;

public abstract class BaseKeyGeneratorConfig implements KeyGeneratorConfig {

    private String keyName;
    private DataSource dataSource;
    private Map<String, Object> context;
    
    public String getKeyName() {
	return this.keyName;
    }

    public void setKeyName(String keyName) {
	this.keyName = keyName;
    }

    public DataSource getDataSource() {
	return this.dataSource;
    }

    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }

    public Map<String, Object> getContext() {
	return this.context;
    }

    public void setContext(Map<String, Object> context) {
	 if (this.context != null)
	      this.context.putAll(context);
	  else
	      this.context = context;
    }

}

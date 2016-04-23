package org.swork.common.key.config;

import java.util.Map;

import javax.sql.DataSource;

import org.swork.common.key.KeyGenerator;

public interface KeyGeneratorConfig {
    
    public abstract String getKeyName();

    public abstract void setKeyName(String paramString);

    public abstract DataSource getDataSource();

    public abstract void setDataSource(DataSource paramDataSource);

    public abstract Map<String, Object> getContext();

    public abstract void setContext(Map<String, Object> paramMap);

    public abstract KeyGenerator getKeyGeneratorInstance(String paramString);
}

package org.swork.common.key;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swork.common.key.config.KeyGeneratorConfig;

public class KeyGeneratorContainer {
    private static final Logger LOGGER = LoggerFactory
	    .getLogger(KeyGeneratorContainer.class);
    private DataSource dataSource;
    private Map<String, Object> context;
    private Map<String, KeyGeneratorConfig> keyGeneratorConfigs;

    public String getNextKey(String keyName, Map<String, Object> params) {
	if ((this.keyGeneratorConfigs == null)
		|| (!this.keyGeneratorConfigs.containsKey(keyName))) {
	    throw new RuntimeException("���м�[" + keyName
		    + "]δ���壬�������������������ļ��Ƿ���ȷ!");
	}
	KeyGeneratorConfig keyGeneratorConfig = (KeyGeneratorConfig) this.keyGeneratorConfigs
		.get(keyName);
	keyGeneratorConfig.setKeyName(keyName);
	keyGeneratorConfig.setDataSource(this.dataSource);
	keyGeneratorConfig.setContext(this.context);

	KeyGenerator keyGenerator = keyGeneratorConfig
		.getKeyGeneratorInstance(keyName);
	keyGenerator.setConfig(keyGeneratorConfig);
	String keyValue = keyGenerator.getNextKey(keyName, params);
	LOGGER.debug("��õ����м���ֵ[{}={}]", keyName, keyValue);
	return keyValue;
    }

    public String getNextKey(String keyName) {
	return getNextKey(keyName, null);
    }

    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }

    public void setKeyGeneratorConfigs(
	    Map<String, KeyGeneratorConfig> keyGeneratorConfigs) {
	this.keyGeneratorConfigs = keyGeneratorConfigs;
    }

    public void setContext(Map<String, Object> context) {
	this.context = context;
    }
}

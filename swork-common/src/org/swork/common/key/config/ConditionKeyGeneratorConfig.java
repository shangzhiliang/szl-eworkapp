package org.swork.common.key.config;

import org.swork.common.key.ConditionKeyGenerator;
import org.swork.common.key.KeyGenerator;
import org.swork.common.utils.SystemUtils;

public class ConditionKeyGeneratorConfig extends BaseKeyGeneratorConfig{

    private String tableName;
    private int poolSize;
    private String condition;
    private String keyPattern;
    private int length;
    private int minLength;
    private int maxLength;

    public ConditionKeyGeneratorConfig()
    {
      setPoolSize(1);
      setTableName("pm_key_generator");
      setKeyPattern("%s");
      setLength(-1);
      setMinLength(1);
      setMaxLength(-1);
      setCondition("");
    }

    public KeyGenerator getKeyGeneratorInstance(String keyName)
    {
      return ConditionKeyGenerator.getInstance(keyName);
    }

    public String getTableName() {
      return this.tableName;
    }

    public void setTableName(String tableName) {
      this.tableName = tableName;
    }

    public int getPoolSize() {
      return this.poolSize;
    }

    public void setPoolSize(int poolSize) {
      this.poolSize = poolSize;
    }

    public String getCondition() {
      return this.condition;
    }

    public void setCondition(String condition) {
      this.condition = condition;
    }

    public String getKeyPattern() {
      return this.keyPattern;
    }

    public void setKeyPattern(String keyPattern) {
      this.keyPattern = keyPattern;
    }

    public int getLength() {
      return this.length;
    }

    public void setLength(int length) {
      this.length = length;
    }

    public int getMinLength() {
      return this.minLength;
    }

    public void setMinLength(int minLength) {
      this.minLength = minLength;
    }

    public int getMaxLength() {
      return this.maxLength;
    }

    public void setMaxLength(int maxLength) {
      this.maxLength = maxLength;
    }

    public String toString() {
      StringBuffer str = new StringBuffer();
      str.append("ConditionKeyGeneratorConfig{").append(SystemUtils.LINE_SEPARATOR);
      str.append("    tableName=").append(getTableName()).append(SystemUtils.LINE_SEPARATOR);
      str.append("    poolSize=").append(getPoolSize()).append(SystemUtils.LINE_SEPARATOR);
      str.append("    condition=").append(getCondition()).append(SystemUtils.LINE_SEPARATOR);
      str.append("    keyPattern=").append(getKeyPattern()).append(SystemUtils.LINE_SEPARATOR);
      str.append("    length=").append(getLength()).append(SystemUtils.LINE_SEPARATOR);
      str.append("    minLength=").append(getMinLength()).append(SystemUtils.LINE_SEPARATOR);
      str.append("    maxLength=").append(getMaxLength()).append(SystemUtils.LINE_SEPARATOR);
      str.append("}");
      return str.toString();
    }

}

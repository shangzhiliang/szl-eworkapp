package org.swork.common.key;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swork.common.key.config.ConditionKeyGeneratorConfig;
import org.swork.common.key.config.KeyGeneratorConfig;
import org.swork.common.utils.StringUtils;
import org.swork.common.utils.SystemUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ConditionKeyGenerator implements KeyGenerator {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(ConditionKeyGenerator.class);

    private Map<String, Object> keyList;

    private static ConditionKeyGenerator instance;

    private void addKeyInfo(String keyName) {
	if (this.keyList == null) {
	    this.keyList = new HashMap<String, Object>();
	}

	if (!this.keyList.containsKey(keyName))
	    this.keyList.put(keyName, new KeyInfo(this, keyName));
    }

    public static synchronized ConditionKeyGenerator getInstance(String keyName) {
	if (instance == null) {
	    instance = new ConditionKeyGenerator();
	}

	instance.addKeyInfo(keyName);

	return instance;
    }

    public String getNextKey(String keyName) {
	KeyInfo keyInfo = (KeyInfo) this.keyList.get(keyName);
	LOGGER.debug("序列键[{}]配置信息:{}{}", new String[] { keyName,
		SystemUtils.LINE_SEPARATOR, keyInfo.config.toString() });
	return keyInfo.getNextKey(this.keyList);
    }

    public String getNextKey(String keyName, Map<String, Object> params) {
	KeyInfo keyInfo = (KeyInfo) this.keyList.get(keyName);
	LOGGER.debug("序列键[{}]配置信息:{}{}", new String[] { keyName,
		SystemUtils.LINE_SEPARATOR, keyInfo.config.toString() });
	return keyInfo.getNextKey(params);
    }

    public void setConfig(KeyGeneratorConfig keyGeneratorConfig) {
	ConditionKeyGeneratorConfig config = (ConditionKeyGeneratorConfig) keyGeneratorConfig;
	String keyName = config.getKeyName();
	KeyInfo keyInfo = (KeyInfo) this.keyList.get(keyName);
	keyInfo.config = config;
    }

    /**
     * 内部类
     * 
     * <pre>
     * <b>类描述:</b>(这里用一句话描述这个类的作用)
     * <b>作者:</b>shangzhiliang
     * <b>创建日期:</b>2016年2月24日 上午10:55:23
     * </pre>
     */
    class KeyInfo {

	private String keyName;
	private String condition;
	private String keyPattern;
	private int nextKeySeq;
	private int keySeqMax;
	private ConditionKeyGeneratorConfig config;

	public KeyInfo(ConditionKeyGenerator paramConditionKeyGenerator,
		String keyName) {
	    this.keyName = keyName;
	}

	public synchronized String getNextKey(Map<String, Object> params) {
	    try {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");

		Template template = new Template("conditon", new StringReader(
			this.config.getCondition()), cfg);
		StringWriter writer = new StringWriter();
		Map context = new HashMap();
		if (this.config.getContext() != null) {
		    context.putAll(this.config.getContext());
		}

		if (context.containsKey("params")) {
		    LOGGER.warn("主键上下文Map对象中的key使用了主键生成器的关键字 [params],请调整配置!");
		}

		if (params != null) {
		    context.put("params", params);
		}
		template.process(context, writer);
		this.condition = writer.toString();
		writer.close();

		template = new Template("keyPattern", new StringReader(
			this.config.getKeyPattern()), cfg);
		writer = new StringWriter();
		template.process(context, writer);
		this.keyPattern = writer.toString();
		writer.close();
	    } catch (IOException e) {
		throw new RuntimeException("解析序列键配置文件失败，序列键标识[" + this.keyName
			+ "]", e);
	    } catch (TemplateException e) {
		throw new RuntimeException("解析序列键配置文件失败，序列键标识[" + this.keyName
			+ "]", e);
	    }

	    if (this.nextKeySeq >= this.keySeqMax) {
		getNextKeySeqFromDB();
	    }

	    String nextKeySeqStr = String.valueOf(this.nextKeySeq++);

	    int length = this.config.getLength();
	    if (length != -1) {
		nextKeySeqStr = StringUtils.leftPad(nextKeySeqStr, length, '0');
		if (nextKeySeqStr.length() > length)
		    throw new RuntimeException("获取序列键失败!生成的顺序号["
			    + nextKeySeqStr + "]长度已达到序列键[" + this.keyName
			    + "]设置的长度[" + length + "],请重新配置!");
	    } else {
		int minLength = this.config.getMinLength();
		nextKeySeqStr = StringUtils.leftPad(nextKeySeqStr, minLength,
			'0');
		if ((this.config.getMaxLength() != -1)
			&& (nextKeySeqStr.length() > this.config.getMaxLength())) {
		    throw new RuntimeException("获取序列键失败!生成的顺序号["
			    + nextKeySeqStr + "]长度已达到序列键[" + this.keyName
			    + "]设置的最大长度[" + this.config.getMaxLength()
			    + "],请重新配置!");
		}
	    }

	    String nextKey = this.keyPattern.replaceAll("%s", nextKeySeqStr);
	    return nextKey;
	}

	private void getNextKeySeqFromDB() {
	    LOGGER.debug("从数据库中获取序列键[{}]的顺序号", this.keyName);
	    Connection connection = null;
	    try {
		connection = this.config.getDataSource().getConnection();

		connection.setAutoCommit(false);

		if (getNextKeySeq(connection) == -1) {
		    insertNextKeySeq(connection);
		}

		this.nextKeySeq = getNextKeySeq(connection);
		this.keySeqMax = (this.nextKeySeq + this.config.getPoolSize());
		updateNextSeq(connection);

		connection.commit();
		LOGGER.debug("数据库中序列键[{}]的顺序号为[{}]", this.keyName,
			Integer.valueOf(this.nextKeySeq));
	    } catch (SQLException e) {
		try {
		    if (connection != null) {
			connection.rollback();
		    }
		} catch (SQLException e1) {
		    LOGGER.error("执行数据库回滚失败!", e);
		}
		throw new RuntimeException("从数据库中获取序列键[" + this.keyName
			+ "]的顺序号失败!", e);
	    } finally {
		try {
		    if ((connection != null) && (!connection.isClosed()))
			connection.close();
		} catch (SQLException e) {
		    LOGGER.error("释放数据库链接资源出现异常!", e);
		}
	    }
	    this.keySeqMax = (this.nextKeySeq + this.config.getPoolSize());
	}

	private void insertNextKeySeq(Connection connection)
		throws SQLException {
	    String insert = "insert into " + this.config.getTableName()
		    + "(key_name,conditions,next_seq) values(?,?,?)";
	    LOGGER.debug("执行SQL:{}", insert);
	    PreparedStatement statement = connection.prepareStatement(insert);
	    statement.setString(1, this.keyName);
	    statement.setString(2, this.condition);
	    statement.setInt(3, 1);
	    statement.executeUpdate();

	    statement.close();
	}

	private int getNextKeySeq(Connection connection) throws SQLException {
	    int nextKeySeq = -1;
	    String select = "select next_seq from "
		    + this.config.getTableName()
		    + " where key_name = ? and conditions = ?";
	    LOGGER.debug("执行SQL:{}", select);
	    PreparedStatement statement = connection.prepareStatement(select);
	    statement.setString(1, this.keyName);
	    statement.setString(2, this.condition);
	    ResultSet resultSet = statement.executeQuery();
	    if ((resultSet != null) && (resultSet.next())) {
		nextKeySeq = resultSet.getInt(1);
	    }

	    if (resultSet != null) {
		resultSet.close();
	    }
	    statement.close();
	    return nextKeySeq;
	}

	private void updateNextSeq(Connection connection) throws SQLException {
	    String update = "update " + this.config.getTableName()
		    + " set next_seq = next_seq+" + this.config.getPoolSize()
		    + " where key_name = ? and conditions = ?";
	    LOGGER.debug("执行SQL:{}", update);
	    PreparedStatement statement = connection.prepareStatement(update);
	    statement.setString(1, this.keyName);
	    statement.setString(2, this.condition);
	    statement.executeUpdate();

	    statement.close();
	}

    }
}

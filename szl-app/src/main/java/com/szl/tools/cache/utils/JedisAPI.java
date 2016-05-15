package com.szl.tools.cache.utils;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 实现所有jedis连接redis的服务接口
 * 
 * @author shangzhiliang
 *
 */
public class JedisAPI {

	/**
	 * 记录日志
	 */
	private static final Logger log = LoggerFactory.getLogger(JedisAPI.class);
	private static JedisPool jedisPool;
	
	/**
     * jedis测试 单机版
     */
    public static void testJedisSingle(){
        Jedis jedis = new Jedis(JedisConstants.MASTER_HOST_IP, JedisConstants.MASTER_HOST_PORT);
        jedis.set("test", "this i a test");
        String str = jedis.get("test");
        log.debug("---:{}",str);
        //关闭jedis的链接
        jedis.close();
    }
    
    /**
     * 使用连接池
     */
    public static void testJedisPool(){
        jedisPool = new JedisPool(JedisConstants.MASTER_HOST_IP, JedisConstants.MASTER_HOST_PORT);
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get("test");
        log.debug("---:{}",str);
        jedis.close();
    }
    
    public static void testSpringJedisSingle(){
    	 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-*.xml");
         JedisPool jedisPool = (JedisPool)context.getBean("redisClient");
         Jedis jedis = jedisPool.getResource();
         jedis.set("key1", "1111");
         String str = jedis.get("key1");
         log.debug("--:{}",str);
         jedis.close();
         jedisPool.close();
    }
    
    /**
     * 测试入口
     * @param args
     */
    public static void main(String[] args) {
//    	testJedisSingle();
//    	testJedisPool();
    	testSpringJedisSingle();
	}
}

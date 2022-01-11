package com.huahua.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;


/**
 * Jedis工具类
 * @author Huahua
 */
@Component
public class JedisUtils {

	private static JedisPoolConfig poolConfig;
	private static JedisPool jedisPool;
	private static Integer maxTotal;
	private static Integer maxIdle;
	private static String host;
	private static Integer port;

	//定义读取配置文件的对象
	private static ResourceBundle rb;
	/**
	 * 使用静态代码块初始化Jedis连接池
	 */
	static{
		//实例化ResourceBundle对象
		rb = ResourceBundle.getBundle("jedis");
		//读取配置文件 获得参数值
		maxTotal = Integer.parseInt(rb.getString("jedis.maxTotal"));
		maxIdle = Integer.parseInt(rb.getString("jedis.maxIdle"));
		port = Integer.parseInt(rb.getString("jedis.port"));
		host = rb.getString("jedis.host");
		poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		jedisPool = new JedisPool(poolConfig,host,port);
	}

	/**
	 * 判断是否使用过了
	 * @return
	 */
	public static boolean isUsed(){
		try{
			if(jedisPool == null){
				return false;
			}
			Jedis jedis = jedisPool.getResource();
			if(jedis == null){
				return  false;
			}
			jedis.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		    return false;
		}
	}

	/**
	 * 获取Jedis对象
	 * @return
	 */
	public static Jedis getResource(){
		return jedisPool.getResource();
	}
}

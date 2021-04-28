package com.aguo.tourism.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/28 上午10:52
 */
public class JedisUtils {
    private static JedisPool jedisPool;

    /**
     * 静态代码块，读取配置文件
     */
    static {
        Properties ppt = new Properties();
        try {
            ppt.load(JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(ppt.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(ppt.getProperty("maxIdle")));
        jedisPool = new JedisPool(config,ppt.getProperty("host"),Integer.parseInt(ppt.getProperty("port")));
    }

    public static Jedis getJedisPool(){
        return jedisPool.getResource();
    }

}

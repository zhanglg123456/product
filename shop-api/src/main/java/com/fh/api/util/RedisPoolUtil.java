package com.fh.api.util;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

public class RedisPoolUtil {

       private static JedisPool jedisPool;
       private static JedisCluster jedisCluster;

       //普通连接池
       private static void initPool(){

           JedisPoolConfig poolConfig = new JedisPoolConfig();
           poolConfig.setMaxTotal(1000);
           poolConfig.setMaxIdle(100);
           poolConfig.setMinIdle(100);
           poolConfig.setTestOnBorrow(true);
           poolConfig.setTestOnReturn(true);
           jedisPool = new JedisPool(poolConfig,"192.168.0.150",6379);


       }



    //集群链接池
    private static void initJedisCluster(){
        JedisPoolConfig config = new JedisPoolConfig();
        Set<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("192.168.0.150",7001));
        nodes.add(new HostAndPort("192.168.0.150",7002));
        nodes.add(new HostAndPort("192.168.0.150",7003));
        nodes.add(new HostAndPort("192.168.0.150",7004));
        nodes.add(new HostAndPort("192.168.0.150",7005));
        nodes.add(new HostAndPort("192.168.0.150",7000));
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setMinIdle(100);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        //timeout的单位是毫秒,5000就是五秒
        jedisCluster = new JedisCluster(nodes, 5000, config);

    }
       //静态代码块,在类加载的时候执行一次
       static {
           initPool();
           initJedisCluster();
       }

    //普通连接池的方法
    public static Jedis getResource(){
       Jedis jedis = jedisPool.getResource();
       return jedis;
    }
    //集群的链接的方法,都是封装好的,不需要getResource()
    public static JedisCluster clusterRedis(){
        return jedisCluster;
    }

    public static void main(String[] args) {
       /* Jedis jedis = RedisPoolUtil.getResource();
        String set = jedis.set("name", "wangshazi");
        System.out.println(set);*/
        JedisCluster jedisCluster = RedisPoolUtil.clusterRedis();
        String set = jedisCluster.set("username", "zhangsan");
        System.out.println(set);
        String username = jedisCluster.get("username");
        System.out.println(username);
    }
}

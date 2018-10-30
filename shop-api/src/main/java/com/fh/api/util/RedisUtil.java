package com.fh.api.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {


    public static void set(String key,String value){
        Jedis jedis =null;
        try {
            jedis = RedisPoolUtil.getResource();
            jedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null!=jedis){
                jedis.close();
            }
        }
    }


    public static String get(String key){
        Jedis jedis = null;
        String result = "";
        try {
            jedis = RedisPoolUtil.getResource();
           result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null!=jedis){
                jedis.close();
            }
        }
        return result;
    }


   public static long incrExpire(String key , int seconds ){
       Jedis jedis=null;
       try {
           jedis = RedisPoolUtil.getResource();
           Long incr = jedis.incr(key);
           if(incr == 1){
               jedis.expire(key,seconds);
           }
               return incr;
       } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       } finally {
           if(null!=jedis){
               jedis.close();
           }
       }



   }


    public static boolean setNXAndExpire(String key,String value ,int seconds){

        Jedis jedis=null;
        try {
            jedis = RedisPoolUtil.getResource();
            Long setnx = jedis.setnx(key, value);
            if(setnx == 1){
                jedis.expire(key,seconds);
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null!=jedis){
                jedis.close();
            }
        }



    }


    public static void  setEX(String key,String value,int seconds){
        Jedis jedis=null;
        try {
            jedis = RedisPoolUtil.getResource();
            jedis.setex(key,seconds,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null!=jedis){
                jedis.close();
            }
        }
    }

    public static void expire(String key,int seconds){
        Jedis jedis=null;
        try {
            jedis = RedisPoolUtil.getResource();
            jedis.expire(key,seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null!=jedis){
                jedis.close();
            }
        }
    }

    public static void del(String key){
        Jedis jedis=null;
        try {
            jedis = RedisPoolUtil.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null!=jedis){
                jedis.close();
            }
        }
    }
}

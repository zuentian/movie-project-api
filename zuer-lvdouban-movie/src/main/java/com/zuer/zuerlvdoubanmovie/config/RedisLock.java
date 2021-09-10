package com.zuer.zuerlvdoubanmovie.config;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/9/7 14:24
 */
public class RedisLock {

    /**
     * 锁的过期时间
     */
    protected  long internalLockLeaseTime = 30000;
    private long timeout = 999999;
    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

    private JedisPool jedisPool;

    public RedisLock(JedisPool jedisPool){
        this.jedisPool =  jedisPool;
    }

    public boolean lock(String id,String lockKey){
        Jedis jedis = jedisPool.getResource();
        Long start = System.currentTimeMillis();
        try {
            while (true){
                String lock = jedis.set(lockKey,id,params);
                if("OK".equals(lock)){
                    return true;
                }
                long l = System.currentTimeMillis() - start;
                if(l >= timeout ){
                    return false;
                }
            }
        } finally {
            jedis.close();
        }
    }

    public boolean unlock(String id,String lockKey){
        Jedis jedis = jedisPool.getResource();
        String script = "if " +
                "redis.call('get',KEYS[1]) == ARGV[1] " +
                "then " +
                "return redis.call('del',KEY[1])" +
                " else " +
                "return 0 " +
                "end";

        try {
            Object result = jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(id));
            if("1".equals(result.toString())){
                return true;
            }
            return false;
        } finally {
            jedis.close();
        }
    }
}
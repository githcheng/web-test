package cn.daxiaobiao.redis.cache;


import cn.daxiaobiao.redis.model.RedisNameSpace;
import cn.daxiaobiao.redis.model.RedisPoolConfig;
import cn.daxiaobiao.redis.util.PropertyUtil;
import com.google.common.collect.Maps;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolCache {

    private static Logger logger = LoggerFactory.getLogger(JedisPoolCache.class);
    private static Map<RedisNameSpace, JedisPool> jedisPoolMap = Maps.newHashMap();
    private static String allocation;
    private static final String SPLIT = ".";
    
    public static JedisPool getPool(RedisNameSpace nameSpace){
        synchronized (JedisPoolCache.class) {
            JedisPool jedisPool = jedisPoolMap.get(nameSpace);
            if(jedisPool == null){
                JedisPoolConfig config = new JedisPoolConfig();
                
                int maxTotal = PropertyUtil.getInt(RedisPoolConfig.MAX_ACTIVE.getKey());
                int maxIdel = PropertyUtil.getInt(RedisPoolConfig.MAX_IDEL.getKey());
                int maxWait = PropertyUtil.getInt(RedisPoolConfig.MAX_WAIT.getKey());
                boolean testOnBorrow = PropertyUtil.getBool(RedisPoolConfig.TEST_ON_BORROW.getKey());
                boolean testOnReturn = PropertyUtil.getBool(RedisPoolConfig.TEST_ON_RETURN.getKey());
                
                config.setMaxTotal(maxTotal);
                config.setMaxIdle(maxIdel);
                config.setMaxWaitMillis(maxWait);
                config.setTestOnBorrow(testOnBorrow);
                config.setTestOnReturn(testOnReturn);
                

                String host = PropertyUtil.getString("ip");
                String port = PropertyUtil.getString("port");
                String password = PropertyUtil.getString("password");
                
                if(logger.isDebugEnabled()){
                    logger.debug("redis-tool host " + host + " port is " + port);
                }
                logger.info("redis-tool host " + host + " port is " + port + ", password: " + password);
                logger.info("maxTotal:{}|maxIdel:{}|maxWait:{} ",maxTotal,maxIdel,maxWait);
                
                if(StringUtils.isBlank(host) || StringUtils.isBlank(port) || StringUtils.isBlank(password)){
                    throw new IllegalArgumentException("redis-tool error, not found host or port.");
                }
                
                jedisPool = new JedisPool(config, host, Integer.parseInt(port),
                        maxWait,password);
                
                jedisPoolMap.put(nameSpace, jedisPool);
            }
            return jedisPool;
        }
    }
}

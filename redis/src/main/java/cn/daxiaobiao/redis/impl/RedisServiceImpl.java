package cn.daxiaobiao.redis.impl;


import cn.daxiaobiao.redis.IRedisService;
import cn.daxiaobiao.redis.util.RedisTool;
import cn.daxiaobiao.redis.model.RedisNameSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 */
@Service
public class RedisServiceImpl implements IRedisService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Value("#{propertyConfigurerForRedis[redis_name_space]}")
	public String redisNameSpace;

	@Value("#{propertyConfigurerForRedis[redis_conf_prefix]}")
	public String redisConfPrefix;


	private Redis redis;

	@PostConstruct
	public void init() {
		try{
			LOGGER.debug("redisNameSpace:{}", redisNameSpace);
			redis = RedisTool.getRedis(RedisNameSpace.getNameSpace(redisNameSpace), redisConfPrefix);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	@Override
	public Redis getRedis() {
		return redis;
	}

	public void setRedis(Redis redis) {
		this.redis = redis;
	}

	public String getWholeKey(String key) {
		return redisConfPrefix + key;
	}

	@Override
	public String get(String key) {
		String wholeKey = getWholeKey(key);
		return redis.get(wholeKey);
	}

	@Override
	public String set(String key, Object object) {
		String wholeKey = getWholeKey(key);
		return redis.set(wholeKey,object.toString());
	}

	@Override
	public String setex(String key, int seconds, Object object) {
		String wholeKey = getWholeKey(key);
		return redis.setex(wholeKey, seconds,object.toString());
	}
}

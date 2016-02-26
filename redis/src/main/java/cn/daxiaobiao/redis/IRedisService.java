package cn.daxiaobiao.redis;

import cn.daxiaobiao.redis.impl.Redis;

/**
 * 
 * @author xuemingtang
 *
 */
public interface IRedisService {
	/**
	 * 
	 * @return
	 */
	public Redis getRedis();

	public String getWholeKey(String key);

	public String get(String key);
	public String set(String key, Object object);

	public String setex(String key, int seconds,Object object);
}

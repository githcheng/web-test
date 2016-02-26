package cn.daxiaobiao.redis.util;


import cn.daxiaobiao.redis.impl.Redis;
import cn.daxiaobiao.redis.model.RedisNameSpace;

public class RedisTool {

    public static Redis getRedis(RedisNameSpace nameSpace, String pName){
        return new Redis(nameSpace, pName);
    }
}

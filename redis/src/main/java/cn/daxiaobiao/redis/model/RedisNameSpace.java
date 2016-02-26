package cn.daxiaobiao.redis.model;

public enum RedisNameSpace{
    
    redis_basic;
    
    public static RedisNameSpace getNameSpace(String name){
        for(RedisNameSpace nameSpace : RedisNameSpace.values()){
            if(nameSpace.name().equals(name)){
                return nameSpace;
            }
        }
        return null;
    }
}
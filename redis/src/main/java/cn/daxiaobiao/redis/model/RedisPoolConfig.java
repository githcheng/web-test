package cn.daxiaobiao.redis.model;

public enum RedisPoolConfig {
    MAX_ACTIVE("redis.pool.maxActive"), 
    MAX_IDEL("redis.pool.maxIdle"), 
    MAX_WAIT("redis.pool.maxWait"), 
    TEST_ON_BORROW("redis.pool.testOnBorrow"), 
    TEST_ON_RETURN("redis.pool.testOnReturn");
    
    private String key;
    
    RedisPoolConfig(String key){
        this.key = key;
    }
    
    public String getKey(){
        return key;
    }
}

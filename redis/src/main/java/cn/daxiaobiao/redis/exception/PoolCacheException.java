package cn.daxiaobiao.redis.exception;

public class PoolCacheException extends RuntimeException{

    private static final long serialVersionUID = 8001815482414763959L;

    public PoolCacheException(String msg) {
        super(msg);
    }

    public PoolCacheException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PoolCacheException(Throwable cause) {
        super(cause);
    }
}

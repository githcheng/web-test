package cn.daxiaobiao.redis.exception;

public class ConfigErrorException extends Exception{

    private static final long serialVersionUID = -8374555035848003604L;

    public ConfigErrorException(String msg) {
        super(msg);
    }

    public ConfigErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}

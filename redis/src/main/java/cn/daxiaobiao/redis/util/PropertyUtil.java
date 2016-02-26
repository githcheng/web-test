package cn.daxiaobiao.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    
    private static Properties props;
    
    private static final String CONFIG_PATH = "jedis.properties";
    
    static{
        props = new Properties();
        InputStream in = getClassLoader().getResourceAsStream(CONFIG_PATH);
          
        try {   
            props.load(in);
        }catch(IOException e){
            logger.error("load redis property file error.", e);
        }finally{
            if (in != null){
                try{
                    in.close();
                }catch(IOException e){
                    logger.error("close redis file stream error", e);
                }
            }
        }
    }
    
    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = PropertyUtil.class.getClassLoader();
        }
        return classLoader;
    }
    
    public static String getString(String key){
        return props.getProperty(key);   
    }
    
    public static Integer getInt(String key){
        return Integer.parseInt(props.getProperty(key));   
    }
    
    public static Long getLong(String key){
        return Long.parseLong(props.getProperty(key));   
    }
    
    public static Boolean getBool(String key){
        return Boolean.parseBoolean(props.getProperty(key));   
    }
}

package cn.daxiaobiao.redis.service;

import cn.daxiaobiao.redis.BaseTest;
import cn.daxiaobiao.redis.impl.RedisServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cheng on 2016/2/26.
 */
public class RedisTest extends BaseTest{

    @Autowired
    private RedisServiceImpl redisService;

    @Test
    public void getTest(){
        String status = redisService.set("foo", "1234567");
        System.out.println("status: "+status);
        String foo = redisService.get("foo");
        System.out.println("foo: "+foo);
    }
}

package cn.daxiaobiao.web.api;

import cn.daxiaobiao.core.service.JavaSmsApi;
import cn.daxiaobiao.redis.IRedisService;
import cn.daxiaobiao.redis.impl.RedisServiceImpl;
import cn.daxiaobiao.web.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("test")
public class RedisController {

    private final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private IRedisService redisService;

    @RequestMapping(value="/getRedis", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JsonNode getPictureCode(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("key") String key ){

        String value = redisService.get(key);
        ObjectNode objectNode = JacksonUtil.getObjectNode();
        ObjectNode data = JacksonUtil.getObjectNode();
        data.put("key",key);
        data.put("value",value);
        objectNode.put("code",0);
        objectNode.put("data",data);
        objectNode.put("msg","success");
        return objectNode;
    }
}
package cn.daxiaobiao.web.api;

import cn.daxiaobiao.core.service.JavaSmsApi;
import cn.daxiaobiao.redis.IRedisService;
import cn.daxiaobiao.web.util.CommonUtil;
import cn.daxiaobiao.web.util.ConstVar;
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

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("register")
public class AuthCodeController {

    private final Logger logger = LoggerFactory.getLogger(AuthCodeController.class);

    @Autowired
    private JavaSmsApi javaSmsApi;

    @Autowired
    private IRedisService redisService;

    @RequestMapping(value="/getPictureCode", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JsonNode getPictureCode(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("phone") String phone,
            @RequestParam("user") String user ){


        String authCode = CommonUtil.generatePictureCode();
        //写入redis
        String status = redisService.setex(ConstVar.picturePrefix + phone, 3600, authCode);
        logger.info("status:{}, smsAuthCode:{}", status,authCode);

        ObjectNode objectNode = JacksonUtil.getObjectNode();
        ObjectNode data = JacksonUtil.getObjectNode();
        data.put("authCode",authCode);
        objectNode.put("code",0);
        objectNode.put("data",data);
        objectNode.put("msg","success");
        return objectNode;
    }

    @RequestMapping(value="/getSmsCode", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JsonNode getPictureCode(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam("phone") String phone,
                           @RequestParam("user") String user,
                           @RequestParam("authCode") String authCode){


        // 验证参数合法性
        checkArgument(phone != null, "电话号码是空值");
        checkArgument(authCode != null, "图片验证码是空值");


        String redisAuthCode = redisService.get(ConstVar.picturePrefix + phone);
        logger.info("redisAuthCode:{}, authCode:{}", redisAuthCode,authCode);
        if (redisAuthCode == null || !redisAuthCode.equals(authCode)){
            return JacksonUtil.fail("图片验证码错误");
        }

        String smsAuthCode = CommonUtil.generateSmsCode();
        logger.info("smsAuthCode:{}", smsAuthCode);

        //写入redis
        String status = redisService.setex(ConstVar.smsPrefix + phone,7200, smsAuthCode);
        logger.info("status:{}, smsAuthCode:{}", status,smsAuthCode);

        String text = String.format("【大小标网】%s会员注册短信验证码，10分钟内有效!", smsAuthCode);
        try {
            if (ConstVar.smsSwitch){
                javaSmsApi.sendSms(text,phone);
            }
        } catch (IOException e) {
            return JacksonUtil.fail("获取验证码失败,请稍后再试");
        }

        ObjectNode objectNode = JacksonUtil.getObjectNode();
        ObjectNode data = JacksonUtil.getObjectNode();
        data.put("authCode",smsAuthCode);
        objectNode.put("code",0);
        objectNode.put("data",data);
        objectNode.put("msg","success");
        return objectNode;
    }
}
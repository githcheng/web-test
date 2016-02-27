package cn.daxiaobiao.web.util;

import cn.daxiaobiao.web.fixenum.ApiEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.util.StringUtils;

/**
 * Created by cheng on 2015/9/17.
 */
public class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public static ObjectNode getObjectNode(){
        ObjectNode objectNode = objectMapper.createObjectNode();
        return objectNode;
    }

    public static ArrayNode getArrayNode(){
        return objectMapper.createArrayNode();
    }

    public static JsonNode fail() {
        ObjectNode objectNode = getObjectNode();
        objectNode.put("code", 1);
        objectNode.put("msg","接口异常");
        return objectNode;
    }

    public static JsonNode ok() {
        ObjectNode objectNode = getObjectNode();
        objectNode.put("code", 0);
        objectNode.put("msg","success");
        return objectNode;
    }

    public static JsonNode ok(String msg) {
        ObjectNode objectNode = getObjectNode();
        objectNode.put("code", 0);
        objectNode.put("msg",msg);
        return objectNode;
    }

    public static JsonNode fail(ApiEnum apiEnum) {
        ObjectNode objectNode = getObjectNode();
        objectNode.put("code", apiEnum.getCode());
        objectNode.put("msg",apiEnum.getDesc());
        return objectNode;
    }

    public static JsonNode fail(String msg) {
        if (StringUtils.isEmpty(msg)){
            msg = "接口异常";
        }
        ObjectNode objectNode = getObjectNode();
        objectNode.put("code", -1);
        objectNode.put("msg",msg);
        return objectNode;
    }

}

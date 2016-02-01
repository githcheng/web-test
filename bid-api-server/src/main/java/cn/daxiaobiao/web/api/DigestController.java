package cn.daxiaobiao.web.api;

import cn.daxiaobiao.core.model.Digest;
import cn.daxiaobiao.core.service.BidService;
import cn.daxiaobiao.web.fixenum.ApiEnum;
import cn.daxiaobiao.web.util.DateTimeUtil;
import cn.daxiaobiao.web.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.util.List;

@Controller
@RequestMapping(value="digest")
public class DigestController {

    private static Log logger = LogFactory.getLog(DigestController.class);

    @Autowired
    private BidService service;

    @RequestMapping(value="/getPageList", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    JsonNode get(
                 @RequestParam() Integer page,
                 @RequestParam() Integer page_size,
                 @RequestParam() Integer type,
                 @RequestParam() Long min_id,
                 @RequestParam() String time
                 ){

        ObjectNode objectNode = JacksonUtil.getObjectNode();
        try {
            List<Digest> digestList = service.getDigestList(min_id, type,
                    page, page_size, time);
            if(CollectionUtils.isEmpty(digestList)){
                return JacksonUtil.fail(ApiEnum.NO_DIGEST_LIST);
            }
            int totalCount = service.getBidTotalCountByType(type);
            ArrayNode arrayNode = JacksonUtil.getArrayNode();
            for(Digest digest : digestList){
                ObjectNode bidInfo = JacksonUtil.getObjectNode();
                bidInfo.put("id", digest.getId());
                bidInfo.put("source", digest.getUrl());
                bidInfo.put("url", digest.getUrl());
                bidInfo.put("type", digest.getType());
                bidInfo.put("title", digest.getTitle());
                DateFormat dateFormat = DateTimeUtil.getDateFormat();
                bidInfo.put("time", dateFormat.format(digest.getTime()));
                arrayNode.add(bidInfo);
            }
            objectNode.put("code",0);
            objectNode.put("digest_list", arrayNode);
            objectNode.put("total", totalCount);
        } catch (Exception e){
            logger.error("error", e);
            objectNode.put("code",-1);
            objectNode.put("msg", "fail");
        }
        return objectNode;
    }
}

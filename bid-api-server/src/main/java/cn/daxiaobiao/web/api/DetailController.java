package cn.daxiaobiao.web.api;

import cn.daxiaobiao.core.model.Bid;
import cn.daxiaobiao.core.service.BidService;
import cn.daxiaobiao.web.fixenum.ApiEnum;
import cn.daxiaobiao.web.util.DateTimeUtil;
import cn.daxiaobiao.web.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;

@Controller
@RequestMapping(value="detail")
public class DetailController {

    private static Log logger = LogFactory.getLog(DetailController.class);

    @Autowired
    private BidService bidService;

    @RequestMapping(value="/get", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    JsonNode get(
                 @RequestParam() Long id,
                 @RequestParam() Integer type
                 ){

        ObjectNode objectNode = JacksonUtil.getObjectNode();
        objectNode.put("code",0);
        try {

            Bid bid = bidService.getBidById(id, type);
            if (null == bid){
                return JacksonUtil.fail(ApiEnum.NO_EXIST_BID);
            }
            ObjectNode bidInfo = JacksonUtil.getObjectNode();
            bidInfo.put("source", bid.getUrl());
            bidInfo.put("title", bid.getTitle());
            bidInfo.put("type", type);
            bidInfo.put("content", bid.getContent());
            DateFormat dateFormat = DateTimeUtil.getDateFormat();
            bidInfo.put("time", dateFormat.format(bid.getTime()));
            bidInfo.put("web_site", bid.getSiteName());
            objectNode.put("code",0);
            objectNode.put("info",bidInfo);
            logger.info("success");
        } catch (Exception e){
            logger.error(" ==== Exception ======", e);
            objectNode.put("code",-1);
            objectNode.put("Msg", "failed");
        }
        return objectNode;
    }
}

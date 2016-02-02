package cn.daxiaobiao.spider.service.impl;

import cn.daxiaobiao.core.bEnum.BidTypeEnum;
import cn.daxiaobiao.core.model.Bid;
import cn.daxiaobiao.core.model.RawBid;
import cn.daxiaobiao.core.model.Record;
import cn.daxiaobiao.core.service.BidService;
import cn.daxiaobiao.core.service.RecordService;
import cn.daxiaobiao.core.util.TypeRegexUtil;
import cn.daxiaobiao.spider.dao.RawDAO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cheng on 2015/10/24.
 */
@Service
public class AddBidService {

    private final Logger logger = LoggerFactory.getLogger(AddBidService.class);

    @Autowired
    private BidService bidService;

    @Autowired
    private RawDAO rawDAO;

    @Autowired
    private RecordService recordService;

    public void excute(){

        Record lastRecord = null;
        long offset = 2L;
        int size = 100;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
        List<RawBid> rawBidList = null;
        boolean flag = true;
        while (flag){
            lastRecord = recordService.getLastRecord();
            rawBidList = rawDAO.getRawBidListByRange(lastRecord.getOffset(), size);

            if(CollectionUtils.isEmpty(rawBidList)){
                break;
            }
            logger.info("rawBidList size : {}",rawBidList.size());
            for(RawBid rawBid : rawBidList){
                saveBid(rawBid, objectMapper);
            }
            lastRecord.setOffset(lastRecord.getOffset()+rawBidList.size());
            saveRecord(lastRecord);
        }
    }


    private int saveBid(RawBid rawBid, ObjectMapper objectMapper){
        Bid bid = this.getBid(rawBid, objectMapper);
        if (bid == null){
            logger.error("bid|null|rawBid Id|{}", rawBid.getId());
            return -1;
        }
        int status = bidService.insert(bid);
        if(status <= 0) {
            logger.error("{}|status<=0|fail",bid.getUrl());
        }
        return 0;
    }

    private int saveRecord(Record record){
        logger.info("saveRecord: {} ", record.toString());
        recordService.addRecord(record);
        return 0;
    }

    private Bid getBid(RawBid rawBid, ObjectMapper objectMapper){

        if(null == rawBid){
            return null;
        }

        String url = null;
        String content = null;
        String title = null;
        String siteType = null;

        try {
            url = rawBid.getUrl();
            JsonNode contentJson = null;
            String rawBidContent = rawBid.getJsonData();
            contentJson = objectMapper.readTree(rawBidContent);
            JsonNode result = contentJson.get("result");
            content = result.get("content").asText();
            title = result.get("title").asText();
            siteType = result.get("siteType").asText();
        } catch (Exception e) {
            logger.error("json transfer error: |{}|{}",url,e);
            return null;
        }

        // 标题为空, 内容为空时 continue
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content)){
            logger.info("title or content|empty|{}",url);
            return null;
        }
        Timestamp time = rawBid.getCreateDate();
        BidTypeEnum typeEnum = TypeRegexUtil.getBidTypeEnum(siteType);

        Bid bid = new Bid();
        bid.setUrl(url);
        bid.setTitle(title);
        bid.setContent(content);
        bid.setSiteName("");
        bid.setCity(0);
        bid.setTime(time);
        bid.setType(typeEnum.getId());
        return bid;
    }
}

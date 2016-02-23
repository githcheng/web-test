package cn.daxiaobiao.spider.service.impl;

import cn.daxiaobiao.core.dao.BidDao;
import cn.daxiaobiao.core.model.Bid;
import cn.daxiaobiao.core.service.BidService;
import cn.daxiaobiao.core.util.HtmlUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.html.HTMLUListElement;

import java.util.List;

/**
 * Created by cheng on 2016/1/5.
 */
@Service
public class SyncPureContentService {

    private final Logger logger = LoggerFactory.getLogger(SyncPureContentService.class);

    @Autowired
    private BidDao bidDao;

    public void updateAllPureContent(){
        long startId = 0L;
        while (true){
            List<Bid> bidList = bidDao.getBidListByOffset(startId, 500);
            if (CollectionUtils.isEmpty(bidList)){
                break;
            }
            long max = 0;
            for(Bid bid : bidList){
                String content = bid.getContent();
                String pureText = HtmlUtil.getPureText(content);
                if (StringUtils.isEmpty(pureText)){
                    continue;
                }
                bid.setPureContent(pureText);
                bidDao.updatePureContent(bid);
                if (max < bid.getId()){
                    max = bid.getId();
                }
            }
            startId = max;
            logger.info("startId:{}", startId);
        }
    }

    public static void main(String[] args){

    }
}

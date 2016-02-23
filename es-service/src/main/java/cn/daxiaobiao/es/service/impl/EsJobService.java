package cn.daxiaobiao.es.service.impl;

import cn.daxiaobiao.core.dao.BidDao;
import cn.daxiaobiao.core.dao.EsRecordDao;
import cn.daxiaobiao.core.model.Bid;
import cn.daxiaobiao.es.dao.BidEsDao;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cheng on 2015/10/24.
 */
@Service
public class EsJobService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BidDao bidDao;

    @Autowired
    private BidEsDao bidEsDao;

    @Autowired
    private EsRecordDao esRecordDao;

    public void createIndex(){
        bidEsDao.createIndex(Bid.class);
    }

    public void updateEs(){
        Long start = 0L;
        Integer limit = 100;
        start = esRecordDao.getBidId();
        while (true){
            logger.info("esjob run {} cycle", start);
            List<Bid> bidList = bidDao.getPureContentBidListByOffset(start, limit);
            if (!CollectionUtils.isEmpty(bidList)){
                start = max(bidList);
            } else {
                break;
            }
            bidEsDao.add(bidList);
            esRecordDao.insert(start);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long max(List<Bid> bidList){
        long max = -1;
        if (CollectionUtils.isEmpty(bidList)){
            return max;
        }
        for (Bid bid : bidList){
            if (bid.getId() > max){
                max = bid.getId();
            }
        }
        return max;
    }
}

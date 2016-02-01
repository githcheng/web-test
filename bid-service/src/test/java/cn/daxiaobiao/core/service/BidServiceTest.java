package cn.daxiaobiao.core.service;

import cn.daxiaobiao.core.BaseTest;
import cn.daxiaobiao.core.model.Bid;
import cn.daxiaobiao.core.dao.BidDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BidServiceTest extends BaseTest {

    private static Logger log = LoggerFactory.getLogger(BidServiceTest.class);

    @Autowired
    private BidService bidService;

    @Autowired
    private BidDao bidDao;

    @Test
    public void getBidListTest() {

//        Long id = Long.MAX_VALUE;
//        Integer type = 1;
//        Long offset = 12L;
//        Integer limit = 5;
//        String time = "2015-09-11 12:00:00";
//
//        List<Digest> digestList = bidService.getDigestListDB(id, type, offset, limit, time);
//        for (Digest bid : digestList){
//            log.info(bid.toString());
//        }

    }

    @Test
    public void getBidByIdTest() {
        List<Bid> bidListByRange = bidDao.getBidListByRange(1L, 300);
        System.out.println("====== "+bidListByRange.size());

        for (Bid bid : bidListByRange){
            System.out.println(bid.toString());
        }
    }
}
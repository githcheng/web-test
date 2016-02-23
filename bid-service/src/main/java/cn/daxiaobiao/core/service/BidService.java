package cn.daxiaobiao.core.service;

import cn.daxiaobiao.core.model.Bid;
import cn.daxiaobiao.core.model.Digest;
import cn.daxiaobiao.core.dao.BidDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by cheng on 2015/10/24.
 */
@Service
public class BidService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BidDao bidDao;


    public List<Digest> getDigestList(Long id, Integer type, Integer page,
                                      Integer pageSize, String time) {
        Long offset = (page - 1L) * pageSize;
        Integer limit = pageSize;

        if (id < 0) { // 首次
            id = Long.MAX_VALUE;
        }
        return this.getDigestListByType(id, type, offset, limit, time);
    }

    public List<Digest> getDigestListByType(Long id, Integer type, Long offset,
                                        Integer limit, String time) {

        List<Digest> digestList = null;
        if(type != 0){
            digestList = bidDao.getDigestListByType(id, type, offset, limit, time);
        } else {
            digestList = bidDao.getDigestList(id,offset, limit, time);
        }
        return digestList;
    }

    public Bid getBidById(Long id, int type) {
        return bidDao.getBidById(id, type);
    }

//    public int insert(String url, String title,
//                      String content, Timestamp time,
//                      Integer city, String siteName,
//                      Integer type) {
//        int rows = 0;
//        try {
//            rows = bidDao.insert(url, title, content, time, city, siteName, type);
//        }catch (Exception e){
//            logger.error(e.getMessage(),e);
//        }
//        return rows;
//    }

    public int insert(Bid bid) {
        if (bid == null){
            return 0;
        }
        int rows = 0;
        try {
            rows = bidDao.insert(bid);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return rows;
    }


    // 取某类型标的总数
    public int getBidTotalCountByType(Integer type) {

        String key = "total_count_"+type;
//        String totalCountStr = redisService.get(key);
//        if (!StringUtils.isEmpty(totalCountStr)){
//            logger.info("redis|type|{}|totalCount|{}",type,totalCountStr);
//            return Integer.valueOf(totalCountStr);
//        }

        int totalCount = 0;
        if(type != 0 ){
            totalCount = bidDao.getBidTotalCountByType(type);
        } else {
            totalCount = bidDao.getBidTotalCount();
        }
        logger.info("db|type|{}|totalCount|{}", type, totalCount);
//        redisService.set(key,totalCount);
        return totalCount;
    }
}

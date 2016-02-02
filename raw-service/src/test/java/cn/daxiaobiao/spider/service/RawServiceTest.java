package cn.daxiaobiao.spider.service;

import cn.daxiaobiao.core.model.RawBid;
import cn.daxiaobiao.spider.BaseTest;
import cn.daxiaobiao.spider.dao.RawDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by cheng on 2016/2/1.
 */
public class RawServiceTest extends BaseTest {

    @Autowired
    private RawDAO rawDAO;

    @Test
    public void getRawTest(){
        List<RawBid> list = rawDAO.getRawBidListByRange(1L, 20);
        for (RawBid raw : list){
            System.out.println(raw.toString());
        }
    }
}

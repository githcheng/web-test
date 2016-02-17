package cn.daxiaobiao.es.service;

import cn.daxiaobiao.core.dao.BidDao;
import cn.daxiaobiao.core.model.Bid;
import cn.daxiaobiao.es.BaseTest;
import cn.daxiaobiao.es.dao.BidEsDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ElasticsearchServiceTest extends BaseTest {

	private static Logger logger = LoggerFactory.getLogger(ElasticsearchServiceTest.class);

	@Autowired
	private BidEsDao bidEsDao;

	@Autowired
	private BidDao bidDao;

	@Test
	public void createIndexTest() {

//		bidEsDao.createIndex(Bid.class);
		List<Bid> bidListByRange = bidDao.getBidListByOffset(3547L,20);
		System.out.println("size: "+bidListByRange.size());
		bidEsDao.add(bidListByRange);
	}


	@Test
	public void addTest() {

//		List<Bid> bidListByRange = bidDao.getBidListByRange(1L, 10);
//		System.out.println(bidListByRange.size());
//		bidEsDao.add(bidListByRange);
	}
}
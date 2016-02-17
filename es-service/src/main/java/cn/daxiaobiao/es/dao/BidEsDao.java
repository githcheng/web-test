package cn.daxiaobiao.es.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.daxiaobiao.core.model.Bid;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class BidEsDao extends BaseESDao {

	public void add(Bid bid) {

		System.out.println("elasticSearch");

		Long id = bid.getId();
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withId(String.valueOf(id))
				.withObject(bid)
				.build();
		elasticsearchTemplate.bulkIndex(Arrays.asList(indexQuery));
	}


	public void createIndex(Class tClass){
		elasticsearchTemplate.createIndex(tClass);
		elasticsearchTemplate.putMapping(Bid.class);
		elasticsearchTemplate.refresh(Bid.class,true);
	}

	public boolean add(List<Bid> bidList) {

		if (CollectionUtils.isEmpty(bidList)){
			return true;
		}
//		elasticsearchTemplate.createIndex(Bid.class);
//		elasticsearchTemplate.putMapping(Bid.class);
//		elasticsearchTemplate.refresh(Bid.class,true);
		ArrayList<IndexQuery> indexQueries = new ArrayList<>();
		for (Bid bid : bidList){
			Long id = bid.getId();
			IndexQuery indexQuery = new IndexQueryBuilder()
					.withId(String.valueOf(id))
					.withObject(bid)
					.build();
			indexQueries.add(indexQuery);
		}
		elasticsearchTemplate.bulkIndex(indexQueries);
		elasticsearchTemplate.refresh(Bid.class, true);
		return true;
	}

}

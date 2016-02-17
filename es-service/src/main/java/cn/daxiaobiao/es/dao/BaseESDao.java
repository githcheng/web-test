package cn.daxiaobiao.es.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

public class BaseESDao {

	@Autowired
	protected ElasticsearchTemplate elasticsearchTemplate;

	public void createIndex(Class tTlass){
		elasticsearchTemplate.createIndex(tTlass);
	}

}

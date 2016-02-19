package cn.daxiaobiao.spider.dao;

import cn.daxiaobiao.core.model.RawBid;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by guojiang on 2015/1/20.
 */


public interface RawDAO {

    @Select("select id,url, content, json_data,create_date,site_type_names " +
            " from t_b_data where id>#{offset} limit #{size}")
    public List<RawBid> getRawBidListByRange(@Param("offset") Long offset,
                                             @Param("size") Integer size);

}

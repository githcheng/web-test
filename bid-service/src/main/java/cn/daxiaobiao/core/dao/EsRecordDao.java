package cn.daxiaobiao.core.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EsRecordDao {

    @Select("select bid_id  from t_es_record order by id desc limit 1")
    public Long getBidId();

    @Update("insert into t_es_record(bid_id, create_time) " +
            "value(#{bid_id}, now())")
    public Integer insert(@Param("bid_id") Long bidId);
}
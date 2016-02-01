package cn.daxiaobiao.core.dao;

import cn.daxiaobiao.core.model.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

public interface RecordDao {


    @Insert("insert into t_record(start_time, end_time) value(#{start},#{end})")
    int insert(@Param("start") Timestamp start,
           @Param("end") Timestamp end);

    @Select("select start_time, end_time from t_record order by start_time desc limit 1")
    Record getLastRecord();
}
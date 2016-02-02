package cn.daxiaobiao.core.dao;

import cn.daxiaobiao.core.model.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RecordDao {

    @Insert("insert into t_record(offset, create_time) " +
            "value(#{record.offset},now())")
    int insert(@Param("record") Record record);

    @Select("select offset,create_time from t_record order by id desc limit 1")
    Record getLastRecord();
}
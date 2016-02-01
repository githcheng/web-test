package cn.daxiaobiao.core.service;

import cn.daxiaobiao.core.dao.RecordDao;
import cn.daxiaobiao.core.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by cheng on 2015/10/24.
 */
@Service
public class RecordService {

    @Autowired
    private RecordDao recordDao;


    public int addRecord(Timestamp start, Timestamp end){
        return recordDao.insert(start,end);
    }

    public Record getLastRecord(){
        return recordDao.getLastRecord();
    }
}

package cn.daxiaobiao.core.model;

import java.sql.Timestamp;

/**
 * Created by cheng on 2016/1/5.
 */
public class Record {

    Timestamp startTime;
    Timestamp endTime;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}

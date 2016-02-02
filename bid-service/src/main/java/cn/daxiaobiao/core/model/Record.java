package cn.daxiaobiao.core.model;

import java.sql.Timestamp;

/**
 * Created by cheng on 2016/1/5.
 */
public class Record {

    Long offset;
    Timestamp createTime;

    @Override
    public String toString() {
        return "Record{" +
                "offset=" + offset +
                ", createTime=" + createTime +
                '}';
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}

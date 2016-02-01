package cn.daxiaobiao.core.model;

import java.sql.Timestamp;

/**
 * Created by guojiang on 2015/3/8.
 */
public class BidRange {

    private Long id;
    private Long offset;
    private Integer pageSize;
    private Timestamp time;


    @Override
    public String toString() {
        return "BidRange{" +
                "id=" + id +
                ", offset=" + offset +
                ", pageSize=" + pageSize +
                ", time=" + time +
                '}';
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

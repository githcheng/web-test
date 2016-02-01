package cn.daxiaobiao.core.bEnum;

/**
 * Created by cheng on 2015/11/20.
 */
public enum BidTypeEnum {

    ZHAOBIAO(1, "招标公告"),
    ZHONGBIAO(2,"中标公告"),
    BIANGEN(3,"更公告"),
    NEW(4,"新闻资讯"),
    OTHER(100,"其它信息"),
    ;

    private Integer id;
    private String desc;

    BidTypeEnum(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

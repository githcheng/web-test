package cn.daxiaobiao.web.fixenum;

/**
 * Created by cheng on 2015/10/24.
 */
public enum ApiEnum {

    NO_EXIST_BID(10001,"您访问的内容不存在"),
    NO_DIGEST_LIST(10002,"没有更多内容");


    private Integer code;
    private String desc;

    ApiEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

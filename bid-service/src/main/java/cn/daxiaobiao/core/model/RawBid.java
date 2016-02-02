package cn.daxiaobiao.core.model;

import java.sql.Timestamp;

/**
 * Created by guojiang on 2015/3/8.
 */
public class RawBid {

    private Long id;
    private String url;
    private String content;
    private String jsonData;
    private Timestamp createDate;
    private String siteTypeNames;


    @Override
    public String toString() {
        return "RawBid{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", jsonData='" + jsonData + '\'' +
                ", createDate=" + createDate +
                ", siteTypeNames='" + siteTypeNames + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getSiteTypeNames() {
        return siteTypeNames;
    }

    public void setSiteTypeNames(String siteTypeNames) {
        this.siteTypeNames = siteTypeNames;
    }
}

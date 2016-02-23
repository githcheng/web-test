package cn.daxiaobiao.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.sql.Timestamp;

/**
 * Created by guojiang on 2015/3/8.
 */
@Document(indexName = "daxiaobiao",type = "bid", shards = 1, replicas = 0)
public class Bid {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String url;

    private String title;

    private String content;

    @Field(type = FieldType.String,index=FieldIndex.analyzed,
            indexAnalyzer="ik", searchAnalyzer="ik",
            store = true)
    private String pureContent;

    private Integer city;

    private Timestamp time;

    private String siteName;

    private Integer type;

    public Bid() {

    }

    public String getPureContent() {
        return pureContent;
    }

    public void setPureContent(String pureContent) {
        this.pureContent = pureContent;
    }

    public Bid(Long id, String url, String title, String content, Integer city, Timestamp time, String siteName,
               Integer type) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.content = content;
        this.city = city;
        this.time = time;
        this.siteName = siteName;
        this.type = type;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", city=" + city +
                ", time=" + time +
                ", siteName='" + siteName + '\'' +
                ", type=" + type +
                '}';
    }
}

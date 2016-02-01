package cn.daxiaobiao.core.model;

import java.sql.Timestamp;

/**
 * Created by guojiang on 2015/3/8.
 */
public class Digest {

    private Long id;
    private String url;
    private String title;
    private Integer city;
    private Timestamp time;
    private Integer type;

    @Override
    public String toString() {
        return "Digest{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", city=" + city +
                ", time=" + time +
                ", type=" + type +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
}

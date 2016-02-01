package cn.daxiaobiao.core.dao;

import cn.daxiaobiao.core.model.Bid;
import cn.daxiaobiao.core.model.Digest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface BidDao {



    static String tableName = "t_bid";

    @Select("select id,url, title, content,time,city,site_name, type " +
            " from "+tableName+" where id < #{id} and type = #{type} order by id desc limit #{offset}, #{limit}")
    public List<Digest> getDigestListByType(@Param("id") Long id,
                                      @Param("type") Integer type,
                                      @Param("offset") Long offset,
                                      @Param("limit") Integer limit,
                                      @Param("time") String time
    );


    @Select("select id, url, title, content,time,city , site_name, type "  +
            " from "+tableName+" limit #{offset}, #{limit}")
    public List<Bid> getBidListByRange(  @Param("offset") Long offset,
                                            @Param("limit") Integer limit
    );

    @Select("select id, url, title, content,time,city , site_name, type "  +
            " from "+tableName+" where id >= #{offset_id} limit  #{limit}")
    public List<Bid> getBidListByOffset(  @Param("offset_id") Long offset_id,
                                         @Param("limit") Integer limit
    );

    @Select("select id,url, title, content,time,city,site_name, type " +
            " from "+tableName+" where id < #{id} order by id desc limit #{offset}, #{limit}")
    public List<Digest> getDigestList(@Param("id") Long id,
                                      @Param("offset") Long offset,
                                      @Param("limit") Integer limit,
                                      @Param("time") String time
    );

    @Select("select id,url, title, content,time,city,site_name, type " +
            " from "+tableName+" where id =#{id}")
    public Bid getBidById(@Param("id") Long id,
                          @Param("type") Integer type
    );

    @Insert("insert into "+tableName+"(url, title, content,time,city," +
            "site_name, type,create_time)" +
            " values(#{url},#{title},#{content},#{time},#{city},#{site_name},#{type},now());")
    public int insert(@Param("url")String url,
                      @Param("title")String title,
                      @Param("content")String content,
                      @Param("time")Timestamp time,
                      @Param("city")Integer city,
                      @Param("site_name")String siteName,
                      @Param("type") Integer type);

    @Select("select count(1) from t_bid where type = #{type}")
    int getBidTotalCountByType(@Param("type")Integer type);

    @Select("select count(1) from t_bid where type in (1,2,3)")
    int getBidTotalCount();
}
package cn.daxiaobiao.core.dao;

import cn.daxiaobiao.core.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Insert("insert into t_user(user, name,password,email,phone,company,create_time) " +
            "value(#{user},#{name},#{password},#{email},#{phone},#{company},now())")
    int insert(User user);

    @Select("select user, name,password,email,phone,company,create_time " +
            " from t_user where user=#{user}")
    User getUser(@Param("user")String user);

    @Select("select user, name,password,email,phone,company,create_time " +
            " from t_user where phone=#{phone}")
    User getUserByPhone(@Param("phone")String phone);
}
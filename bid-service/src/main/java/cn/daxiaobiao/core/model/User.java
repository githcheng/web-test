package cn.daxiaobiao.core.model;

import java.sql.Timestamp;

/**
 * Created by cheng on 2016/2/23.
 */
public class User {

    private String user;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String company;
    private Timestamp createTime;

    public User(){}

    public User(String user, String name, String password, String email, String phone, String company) {
        this.user = user;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.company = company;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}

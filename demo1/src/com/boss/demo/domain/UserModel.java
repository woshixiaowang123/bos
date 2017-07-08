package com.boss.demo.domain;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 隔壁老王 on 2017/6/25.
 */
@Entity

public class UserModel {
    private String id;
    private String username;
    private String password;
    private Double salary;
    private Date birthday;
    private String gender;
    private String station;
    private String telephone;
    private String remark;
    private Set<NoticebillModel> noticebills = new HashSet(0);
    private Set<RoleModel> roles = new HashSet(0);


    public String getBirthdayStr(){
        if (birthday!=null){
            //将日期转化为yyyy-MM-dd格式
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            return format.format(birthday);
        }
        return "";
    }

    public Set<NoticebillModel> getNoticebills() {
        return noticebills;
    }

    public void setNoticebills(Set<NoticebillModel> noticebills) {
        this.noticebills = noticebills;
    }

    public Set getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (id != null ? !id.equals(userModel.id) : userModel.id != null) return false;
        if (username != null ? !username.equals(userModel.username) : userModel.username != null) return false;
        if (password != null ? !password.equals(userModel.password) : userModel.password != null) return false;
        if (salary != null ? !salary.equals(userModel.salary) : userModel.salary != null) return false;
        if (birthday != null ? !birthday.equals(userModel.birthday) : userModel.birthday != null) return false;
        if (gender != null ? !gender.equals(userModel.gender) : userModel.gender != null) return false;
        if (station != null ? !station.equals(userModel.station) : userModel.station != null) return false;
        if (telephone != null ? !telephone.equals(userModel.telephone) : userModel.telephone != null) return false;
        if (remark != null ? !remark.equals(userModel.remark) : userModel.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                ", remark='" + remark + '\'' +
                ", noticebills=" + noticebills +
                ", roles=" + roles +
                '}';
    }
}

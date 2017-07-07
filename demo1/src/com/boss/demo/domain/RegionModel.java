package com.boss.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 隔壁老王 on 2017/6/26.
 */
@Entity

public class RegionModel {
    private String id;
    private String province;
    private String city;
    private String district;
    private String postcode;
    private String shortcode;
    private String citycode;
    private Set<SubareaModel> Subareas=new HashSet<SubareaModel>(0);

    //因为分区中要用的区域的名称相加,所以就把名称加一块,拼接字符串作为属性
    public String getName(){
        return province + city + district;
    }

    public RegionModel() {
    }

    public RegionModel(String id, String province, String city, String district, String postcode, String shortcode, String citycode, Set<SubareaModel> subareas) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.district = district;
        this.postcode = postcode;
        this.shortcode = shortcode;
        this.citycode = citycode;
        Subareas = subareas;
    }

    public RegionModel(String id, String province, String city, String district, String postcode) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.district = district;
        this.postcode = postcode;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }


    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionModel that = (RegionModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (shortcode != null ? !shortcode.equals(that.shortcode) : that.shortcode != null) return false;
        if (citycode != null ? !citycode.equals(that.citycode) : that.citycode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (shortcode != null ? shortcode.hashCode() : 0);
        result = 31 * result + (citycode != null ? citycode.hashCode() : 0);
        return result;
    }


    public Set<SubareaModel> getSubareas() {
        return Subareas;
    }

    public void setSubareas(Set<SubareaModel> subareas) {
        Subareas = subareas;
    }

    @Override
    public String toString() {
        return "RegionModel{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", postcode='" + postcode + '\'' +
                ", shortcode='" + shortcode + '\'' +
                ", citycode='" + citycode + '\'' +
                ", Subareas=" + Subareas +
                '}';
    }
}

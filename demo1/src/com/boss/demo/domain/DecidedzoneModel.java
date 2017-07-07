package com.boss.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 隔壁老王 on 2017/6/26.
 */
@Entity

public class DecidedzoneModel {
    private String id;
    private String name;
    private StaffModel Staff;
    private Set<SubareaModel> Subareas=new HashSet<SubareaModel>(0);


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecidedzoneModel that = (DecidedzoneModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


    public StaffModel getStaff() {
        return Staff;
    }

    public void setStaff(StaffModel staff) {
        Staff = staff;
    }

    public Set<SubareaModel> getSubareas() {
        return Subareas;
    }

    public void setSubareas(Set<SubareaModel> subareas) {
        Subareas = subareas;
    }

    @Override
    public String toString() {
        return "DecidedzoneModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", Staff=" + Staff +
                ", Subareas=" + Subareas +
                '}';
    }
}

package com.boss.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 隔壁老王 on 2017/6/26.
 */
@Entity

public class StaffModel {
    private String id;
    private String name;
    private String telephone;
    private String haspda="1";
    private String deltag="1";
    private String station;
    private String standard;
    private Set<DecidedzoneModel> Decidedzones=new HashSet<DecidedzoneModel>(0);

    public StaffModel(String id, String name, String telephone, String haspda, String deltag, String station, String standard) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.haspda = haspda;
        this.deltag = deltag;
        this.station = station;
        this.standard = standard;
    }

    public StaffModel() {
    }

    public StaffModel(String id, String name, String telephone, String haspda, String deltag, String station, String standard, Set<DecidedzoneModel> decidedzones) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.haspda = haspda;
        this.deltag = deltag;
        this.station = station;
        this.standard = standard;
        Decidedzones = decidedzones;
    }


    @Id
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


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getHaspda() {
        return haspda;
    }

    public void setHaspda(String haspda) {
        this.haspda = haspda;
    }


    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag;
    }


    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }


    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffModel that = (StaffModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (haspda != null ? !haspda.equals(that.haspda) : that.haspda != null) return false;
        if (deltag != null ? !deltag.equals(that.deltag) : that.deltag != null) return false;
        if (station != null ? !station.equals(that.station) : that.station != null) return false;
        if (standard != null ? !standard.equals(that.standard) : that.standard != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (haspda != null ? haspda.hashCode() : 0);
        result = 31 * result + (deltag != null ? deltag.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
        return result;
    }


    public Set<DecidedzoneModel> getDecidedzones() {
        return Decidedzones;
    }

    public void setDecidedzones(Set<DecidedzoneModel> decidedzones) {
        Decidedzones = decidedzones;
    }

    @Override
    public String toString() {
        return "StaffModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", haspda='" + haspda + '\'' +
                ", deltag='" + deltag + '\'' +
                ", station='" + station + '\'' +
                ", standard='" + standard + '\'' +
                ", Decidedzones=" + Decidedzones +
                '}';
    }
}

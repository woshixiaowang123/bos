package com.boss.demo.domain;

import javax.persistence.*;

/**
 * Created by 隔壁老王 on 2017/6/26.
 */
@Entity

public class SubareaModel {
    private String id;
    private String addresskey;
    private String startnum;
    private String endnum;
    private String single;
    private String position;
    private DecidedzoneModel Decidedzone;
    private RegionModel Region;

    public SubareaModel() {
    }

    public SubareaModel(String id, String addresskey, String startnum, String endnum, String single, String position, DecidedzoneModel decidedzone, RegionModel region) {
        this.id = id;
        this.addresskey = addresskey;
        this.startnum = startnum;
        this.endnum = endnum;
        this.single = single;
        this.position = position;
        Decidedzone = decidedzone;
        Region = region;
    }


    public String getId() {
        return id;
    }

    public String getSubareaId(){
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddresskey() {
        return addresskey;
    }

    public void setAddresskey(String addresskey) {
        this.addresskey = addresskey;
    }


    public String getStartnum() {
        return startnum;
    }

    public void setStartnum(String startnum) {
        this.startnum = startnum;
    }

    public String getEndnum() {
        return endnum;
    }

    public void setEndnum(String endnum) {
        this.endnum = endnum;
    }


    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubareaModel that = (SubareaModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (addresskey != null ? !addresskey.equals(that.addresskey) : that.addresskey != null) return false;
        if (startnum != null ? !startnum.equals(that.startnum) : that.startnum != null) return false;
        if (endnum != null ? !endnum.equals(that.endnum) : that.endnum != null) return false;
        if (single != null ? !single.equals(that.single) : that.single != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (addresskey != null ? addresskey.hashCode() : 0);
        result = 31 * result + (startnum != null ? startnum.hashCode() : 0);
        result = 31 * result + (endnum != null ? endnum.hashCode() : 0);
        result = 31 * result + (single != null ? single.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }


    public DecidedzoneModel getDecidedzone() {
        return Decidedzone;
    }

    public void setDecidedzone(DecidedzoneModel decidedzone) {
        Decidedzone = decidedzone;
    }


    public RegionModel getRegion() {
        return Region;
    }

    public void setRegion(RegionModel region) {
        Region = region;
    }

    @Override
    public String toString() {
        return "SubareaModel{" +
                "id='" + id + '\'' +
                ", addresskey='" + addresskey + '\'' +
                ", startnum='" + startnum + '\'' +
                ", endnum='" + endnum + '\'' +
                ", single='" + single + '\'' +
                ", position='" + position + '\'' +
                ", Decidedzone=" + Decidedzone +
                ", Region=" + Region +
                '}';
    }
}

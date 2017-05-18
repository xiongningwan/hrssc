package com.maiyu.hrssc.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 获取某个二级业务的基础信息（官方网站，体检、报到地址，联系人，联系方式）
 */

public class CategoryBaseinfo implements Parcelable {
    private String id;//5,              //id
    private String city;//深圳市,      //城市
    private String cid1;//2,            //所属一级业务
    private String cid2;//5,            //所属二级业务
    private String website;//http;////www.baidu.com,  //官方网站
    private String create_time;//2017-04-28 12;//27;//28.0,
    private String baodao_addr;//,   //报到地址  或者 体检地址，共用字段
    private String baodao_contact;//, //报到联系人
    private String baodao_phone;//


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCid1() {
        return cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public String getCid2() {
        return cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getBaodao_addr() {
        return baodao_addr;
    }

    public void setBaodao_addr(String baodao_addr) {
        this.baodao_addr = baodao_addr;
    }

    public String getBaodao_contact() {
        return baodao_contact;
    }

    public void setBaodao_contact(String baodao_contact) {
        this.baodao_contact = baodao_contact;
    }

    public String getBaodao_phone() {
        return baodao_phone;
    }

    public void setBaodao_phone(String baodao_phone) {
        this.baodao_phone = baodao_phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.city);
        dest.writeString(this.cid1);
        dest.writeString(this.cid2);
        dest.writeString(this.website);
        dest.writeString(this.create_time);
        dest.writeString(this.baodao_addr);
        dest.writeString(this.baodao_contact);
        dest.writeString(this.baodao_phone);
    }

    public CategoryBaseinfo() {
    }

    protected CategoryBaseinfo(Parcel in) {
        this.id = in.readString();
        this.city = in.readString();
        this.cid1 = in.readString();
        this.cid2 = in.readString();
        this.website = in.readString();
        this.create_time = in.readString();
        this.baodao_addr = in.readString();
        this.baodao_contact = in.readString();
        this.baodao_phone = in.readString();
    }

    public static final Parcelable.Creator<CategoryBaseinfo> CREATOR = new Parcelable.Creator<CategoryBaseinfo>() {
        @Override
        public CategoryBaseinfo createFromParcel(Parcel source) {
            return new CategoryBaseinfo(source);
        }

        @Override
        public CategoryBaseinfo[] newArray(int size) {
            return new CategoryBaseinfo[size];
        }
    };
}

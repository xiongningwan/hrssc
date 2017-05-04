package com.maiyu.hrssc.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 自取地址
 */

public class SelfAddress implements Parcelable{
    private String address;//福田区办事处,     //地址名称
    private String id;//2,                       //地址id
    private String city;//深圳市,              //所属城市
    private String address_info;//深圳市福田区xx路xx大厦8808室,//地址详情
    private String create_time;//2017-05-02 153003.0

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getAddress_info() {
        return address_info;
    }

    public void setAddress_info(String address_info) {
        this.address_info = address_info;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.id);
        dest.writeString(this.city);
        dest.writeString(this.address_info);
        dest.writeString(this.create_time);
    }

    public SelfAddress() {
    }

    protected SelfAddress(Parcel in) {
        this.address = in.readString();
        this.id = in.readString();
        this.city = in.readString();
        this.address_info = in.readString();
        this.create_time = in.readString();
    }

    public static final Creator<SelfAddress> CREATOR = new Creator<SelfAddress>() {
        @Override
        public SelfAddress createFromParcel(Parcel source) {
            return new SelfAddress(source);
        }

        @Override
        public SelfAddress[] newArray(int size) {
            return new SelfAddress[size];
        }
    };
}

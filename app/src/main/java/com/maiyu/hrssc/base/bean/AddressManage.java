package com.maiyu.hrssc.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 地址管理
 */

public class AddressManage implements Parcelable {
    private String name;//小张,    //收货人姓名
    private String id; //8,           //地址id
    private String uid;//1000,       //工号
    private String is_default;//1,   //1-默认收货地址   0-非默认
    private String area;//南山区,  //县/区
    private String city;//深圳市,  //市
    private String prov;//广东省,  //省
    private String addr;//深大科兴, //详细地址
    private String delete;//0,        //忽略
    private String phone;//13397100222, //手机号码
    private String create_time;//2017-03-10 20:36:15 //创建时间


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.uid);
        dest.writeString(this.is_default);
        dest.writeString(this.area);
        dest.writeString(this.city);
        dest.writeString(this.prov);
        dest.writeString(this.addr);
        dest.writeString(this.delete);
        dest.writeString(this.phone);
        dest.writeString(this.create_time);
    }

    public AddressManage() {
    }

    protected AddressManage(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.uid = in.readString();
        this.is_default = in.readString();
        this.area = in.readString();
        this.city = in.readString();
        this.prov = in.readString();
        this.addr = in.readString();
        this.delete = in.readString();
        this.phone = in.readString();
        this.create_time = in.readString();
    }

    public static final Creator<AddressManage> CREATOR = new Creator<AddressManage>() {
        @Override
        public AddressManage createFromParcel(Parcel source) {
            return new AddressManage(source);
        }

        @Override
        public AddressManage[] newArray(int size) {
            return new AddressManage[size];
        }
    };
}

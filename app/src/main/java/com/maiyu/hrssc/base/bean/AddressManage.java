package com.maiyu.hrssc.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/4/6.
 */

public class AddressManage implements Parcelable {
    private String name;
    private String tel;
    private String address;
    private boolean isSelect;

    public AddressManage() {
    }

    public AddressManage(String name, String tel, String address, boolean isSelect) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.tel);
        dest.writeString(this.address);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    protected AddressManage(Parcel in) {
        this.name = in.readString();
        this.tel = in.readString();
        this.address = in.readString();
        this.isSelect = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AddressManage> CREATOR = new Parcelable.Creator<AddressManage>() {
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

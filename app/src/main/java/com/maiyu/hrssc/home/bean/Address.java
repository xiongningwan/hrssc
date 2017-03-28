package com.maiyu.hrssc.home.bean;

/**
 * Created by Administrator on 2017/3/27.
 */

public class Address {
    private String addressName;
    private String addressDetail;

    public Address() {
    }

    public Address(String addressName, String addressDetail) {
        this.addressName = addressName;
        this.addressDetail = addressDetail;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}

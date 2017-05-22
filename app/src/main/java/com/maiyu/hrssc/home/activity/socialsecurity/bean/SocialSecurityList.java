package com.maiyu.hrssc.home.activity.socialsecurity.bean;

/**
 * 查看社保记录
 */

public class SocialSecurityList {
    private String total;//":1762,            //总金额
    private String data;//":"2017-05-20"      //日期

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

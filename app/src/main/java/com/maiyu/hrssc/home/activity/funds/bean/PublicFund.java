package com.maiyu.hrssc.home.activity.funds.bean;

/**
 * Created by Administrator on 2017/5/19.
 */

public class PublicFund {
    private String city;// "中国",          //缴纳地
    private String paytime;// "2016-05",    //缴纳月份
    private String base;// 111.11,          //缴纳基数
    private String payamount;// 10,         //缴纳实际
    private String percent_company;// 50,   //单位比例
    private String percent_personal;// 50    //个人比例

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getPayamount() {
        return payamount;
    }

    public void setPayamount(String payamount) {
        this.payamount = payamount;
    }

    public String getPercent_company() {
        return percent_company;
    }

    public void setPercent_company(String percent_company) {
        this.percent_company = percent_company;
    }

    public String getPercent_personal() {
        return percent_personal;
    }

    public void setPercent_personal(String percent_personal) {
        this.percent_personal = percent_personal;
    }
}

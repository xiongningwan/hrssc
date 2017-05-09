package com.maiyu.hrssc.base.bean;

/**
 * 获取某个二级业务的基础信息（官方网站，体检、报到地址，联系人，联系方式）
 */

public class CategoryBaseinfo {
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
}

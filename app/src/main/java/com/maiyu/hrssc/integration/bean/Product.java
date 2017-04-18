package com.maiyu.hrssc.integration.bean;

/**
 * 产品
 */

public class Product {
    private String id;//123456 ,              //商品id
    private String name;//"商品名称"   ,     //商品名称
    private String price;//100，              //价格
    private String worth;//120，             //价值
    private String lefts;//50，               //剩余数量
    private String brief;//"商品简介",         //商品简介
    private String description;//"详情"，      //商品详情。图文
    private String create_time;//"2017-03-07 01;//01;//10"  //发布时间
    private String status;// 0上架 1下架

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWorth() {
        return worth;
    }

    public void setWorth(String worth) {
        this.worth = worth;
    }

    public String getLefts() {
        return lefts;
    }

    public void setLefts(String lefts) {
        this.lefts = lefts;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

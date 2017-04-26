package com.maiyu.hrssc.integration.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 产品
 */

public class Product implements Parcelable {
    private String id;//123456 ,              //商品id
    private String name;//"商品名称"   ,     //商品名称
    private String price;//100，              //价格 实际价格
    private String worth;//120，             //价值 价值多少积分，这个值一般大于实际价格
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.worth);
        dest.writeString(this.lefts);
        dest.writeString(this.brief);
        dest.writeString(this.description);
        dest.writeString(this.create_time);
        dest.writeString(this.status);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.price = in.readString();
        this.worth = in.readString();
        this.lefts = in.readString();
        this.brief = in.readString();
        this.description = in.readString();
        this.create_time = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}

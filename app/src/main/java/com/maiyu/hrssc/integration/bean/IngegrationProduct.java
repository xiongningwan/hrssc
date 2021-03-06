package com.maiyu.hrssc.integration.bean;

/**
 * 积分产品
 */

public class IngegrationProduct {
    private String id;//123456 ,              //商品id
    private String name;//"商品名称"   ,     //商品名称
    private String price;//100，              //价格
    private String worth;//120，             //价值多少积分，这个值一般大于实际价格
    private String lefts;//50，               //剩余数量
    private String img_url; //"/2017/02/sss.png" //商品图片


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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }


    @Override
    public String toString() {
        return "IngegrationProduct{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", worth='" + worth + '\'' +
                ", lefts='" + lefts + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}

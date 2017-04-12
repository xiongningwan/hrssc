package com.maiyu.hrssc.base.bean;

/**
 * banners
 */

public class Banners {
    private long id;//1,        //banner   id
    private String name;//电影发布会, //banner名称
    private String link;//www.baidu.com, //跳转链接   为空或者null不跳转
    private String status;//0,       //状态   前端忽略，后端处理
    private String order;//1,       //排序字段   后端处理  前端忽略
    private String image;///gcrcsUploadFile/2017/03/17/63936/seaNews.jpg,//图片链接  相对路径  域名待确定
    private String create_time;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

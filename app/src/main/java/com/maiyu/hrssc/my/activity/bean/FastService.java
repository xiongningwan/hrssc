package com.maiyu.hrssc.my.activity.bean;

/**
 * 快捷服务
 */

public class FastService {
    private String name;//话费充值,   //服务名称
    private String id;//3,               //服务id
    private String status;//0,               //状态  0-正常  1-异常隐藏
    private String brief;//话费充值xxx,    //简介
    private String icon;//http;////www.ccvzb.cn/gcrcsUploadFile/2017/03/03/122731/3huafei.png,                     //图标
    private String url;//http;////touch.10086.cn/i/mobile/rechargecredit.html,//跳转路径  为空或null不跳转
    private String create_time;//2017-03-14 17;//28;//46.0, //创建时间
    private String order_number;//4

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
}

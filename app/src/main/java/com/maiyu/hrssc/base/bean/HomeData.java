package com.maiyu.hrssc.base.bean;

import java.util.List;

/**
 * 首页信息
 */

public class HomeData {
    private String myTransaction;//": 2,//待我办理
    private String myMessage;//":2,//我的未读消息数量
    private String myApply;//":3,//待我申请
    private List<News> news;
    private List<Banners> banners;
    private List<Messages> messages;

    public String getMyTransaction() {
        return myTransaction;
    }

    public void setMyTransaction(String myTransaction) {
        this.myTransaction = myTransaction;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }

    public String getMyApply() {
        return myApply;
    }

    public void setMyApply(String myApply) {
        this.myApply = myApply;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Banners> getBanners() {
        return banners;
    }

    public void setBanners(List<Banners> banners) {
        this.banners = banners;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }
}

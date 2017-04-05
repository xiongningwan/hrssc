package com.maiyu.hrssc.base.bean;

public class Adv {
    /** 广告id*/
    private long id;
    /** 广告图片路径*/
    private String image;
    /** 广告位置*/
    private String place;
    /** 广告标题*/
    private String title;
    /** 广告网页地址*/
    private String activityUrl;
    /** 分享地址*/
    private String shareUrl;
    /** 描述*/
    private String adDesc;
    /** 是否分享  1:是 0:否*/
    private String shareable;

    public Adv() {
    }

    public Adv(long id, String image, String place, String title, String activityUrl, String shareUrl, String adDesc, String shareable) {
        this.id = id;
        this.image = image;
        this.place = place;
        this.title = title;
        this.activityUrl = activityUrl;
        this.shareUrl = shareUrl;
        this.adDesc = adDesc;
        this.shareable = shareable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getAdDesc() {
        return adDesc;
    }

    public void setAdDesc(String adDesc) {
        this.adDesc = adDesc;
    }

    public String getShareable() {
        return shareable;
    }

    public void setShareable(String shareable) {
        this.shareable = shareable;
    }



}

package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * 附件图片
 */

public class AttachImage {
    private String imageUrl;
    private String imageDesc;

    public AttachImage() {
    }

    public AttachImage(String imageUrl, String imageDesc) {
        this.imageUrl = imageUrl;
        this.imageDesc = imageDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }
}

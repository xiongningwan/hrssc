package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * 附件文件
 */

public class AttachFile {
    private String url;
    private String desc;


    public AttachFile() {
    }

    public AttachFile(String url, String desc) {
        this.url = url;
        this.desc = desc;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * 附件文件
 */

public class AttachFile {
    private String type;
    private String url;
    private String desc;


    public AttachFile() {
    }

    public AttachFile(String type, String url, String desc) {
        this.type = type;
        this.url = url;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

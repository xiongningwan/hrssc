package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * 进度纯文本
 */

public class TypeText {
    private String content;
    private String time;

    public TypeText(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public TypeText() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

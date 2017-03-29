package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * 申请列表条目项
 */

public class Apply {

    private String title;
    private String satus;
    private String time;
    private String reason;

    public Apply() {
    }

    public Apply(String title, String satus, String time, String reason) {
        this.title = title;
        this.satus = satus;
        this.time = time;
        this.reason = reason;
    }

    public Apply(String title, String satus, String time) {
        this.title = title;
        this.satus = satus;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

package com.maiyu.hrssc.home.activity.information.bean;

/**
 * Created by Administrator on 2017/3/29.
 */

public class Info {
    private String titleName;
    private String time;

    public Info() {
    }

    public Info(String titleName, String time) {
        this.titleName = titleName;
        this.time = time;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

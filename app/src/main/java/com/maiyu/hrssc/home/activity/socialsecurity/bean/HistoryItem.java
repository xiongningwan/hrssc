package com.maiyu.hrssc.home.activity.socialsecurity.bean;

/**
 * Created by Administrator on 2017/3/31.
 */

public class HistoryItem {
    private String time;
    private String money;

    public HistoryItem() {
    }

    public HistoryItem(String time, String money) {
        this.time = time;
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}

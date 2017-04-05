package com.maiyu.hrssc.home.activity.funds.bean;

/**
 * 公积金
 */

public class Funds {
    private String time;
    private String money;

    public Funds() {
    }

    public Funds(String time, String money) {
        this.time = time;
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

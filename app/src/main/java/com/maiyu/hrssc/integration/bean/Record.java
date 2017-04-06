package com.maiyu.hrssc.integration.bean;

/**
 * 兑换记录
 * Created by Administrator on 2017/4/6.
 */

public class Record {
    private String title;
    private String imagUrl;
    private String jifen;
    private String number;
    private String time;

    public Record() {
    }

    public Record(String title, String imagUrl, String jifen, String number, String time) {
        this.title = title;
        this.imagUrl = imagUrl;
        this.jifen = jifen;
        this.number = number;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

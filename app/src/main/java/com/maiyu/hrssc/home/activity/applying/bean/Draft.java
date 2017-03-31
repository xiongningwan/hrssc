package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * 草稿箱
 */

public class Draft {
    private String title;
    private String time;

    public Draft() {
    }

    public Draft(String title, String time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Draft{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

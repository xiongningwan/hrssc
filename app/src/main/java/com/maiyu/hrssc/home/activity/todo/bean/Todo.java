package com.maiyu.hrssc.home.activity.todo.bean;

/**
 * Created by Administrator on 2017/3/28.
 */

public class Todo {
    private String title;
    private String status;
    private String time;

    public Todo() {
    }

    public Todo(String title, String status, String time) {
        this.title = title;
        this.status = status;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

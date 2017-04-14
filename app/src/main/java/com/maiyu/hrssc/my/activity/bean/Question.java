package com.maiyu.hrssc.my.activity.bean;

/**
 * 下级问题
 */

public class Question {
    private String id;//4,   //问题id
    private String content;//,//详情，已处理为空
    private String create_time;//,//时间，已处理为空
    private String cid;//0,         //分类id，已处理为0
    private String title;//年终奖多少啊？, //问题主题
    private String author;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

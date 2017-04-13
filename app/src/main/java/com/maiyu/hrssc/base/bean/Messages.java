package com.maiyu.hrssc.base.bean;

/**
 * 消息
 */

public class Messages {
    private String id;//2,           //消息id
    private String content;//,    //消息内容，已处理为空
    private String status;//0,      //0-未读  1-已读
    private String title;//关于2017XX号通知详情,  //主题
    private String create_time;//2017-03-12 14;//36;//16, //时间
    private String sender;//}]  //发送者名称 ，已处理为为空

    public Messages() {
    }


    public Messages(String id, String content, String status, String title, String create_time, String sender) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.title = title;
        this.create_time = create_time;
        this.sender = sender;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

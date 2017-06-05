package com.maiyu.hrssc.base.bean;

/**
 * Created by Administrator on 2017/5/24.
 */

public class Version {
    private String id;//1,
    private String name;//"1.0.0",     //版本名称
    private String comment;//"初始版本1.0.0",//更新内容
    private long code;//3,                  //版本号
    private String must;//0,                   //0-不强制  1-强制更新
    private String create_time;//"2017-05-23 17:24:39.0",
    private String url;//http://ol8zsqyk7.bkt.clouddn.com/app-release.apk//apk的下载链接，直接下载

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMust() {
        return must;
    }

    public void setMust(String must) {
        this.must = must;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

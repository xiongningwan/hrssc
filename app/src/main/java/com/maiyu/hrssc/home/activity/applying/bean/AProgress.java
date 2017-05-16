package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * Created by Administrator on 2017/5/16.
 */

public class AProgress {
    private String id;// 12,     //进度ID
    private String attachs;// ,//附件
    private String admin;// superadmin,//添加人员用户名（不展示）
    private String deal_name;// 超级管理员,//添加人员名称

    private String create_time;// 2017-05-11 17;//08;//30,
    private String images;// , //图片
    private String appid;// 67,//申请ID
    private String comment;// 测试添加进度。8个图片  8 个文件 //进度说明


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttachs() {
        return attachs;
    }

    public void setAttachs(String attachs) {
        this.attachs = attachs;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getDeal_name() {
        return deal_name;
    }

    public void setDeal_name(String deal_name) {
        this.deal_name = deal_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

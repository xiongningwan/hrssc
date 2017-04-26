package com.maiyu.hrssc.home.activity.todo.bean;

/**
 * 合同实体
 */

public class Todo {
    private String id;//42,                         //合同id
    private String status;//1,   //状态0-待签署  1-待盖章  2-已撤销  3-已完成
    private String city;//北京市,               //城市
    private String brief;//,                    //备注 （已处理为空，前端忽略）
    private String tpl_cid;//100025,              //模板id(前端忽略)
    private String tpl_name;//北京地区管理人员劳动合同（中文）,//业务名称
    private String tpl_form;//,                  //前端忽略
    private String sender;//,                   //前端忽略
    private String sign_way;//1,     //签署方式  0电子签署1当面签署2邮寄签署
    private String sign_time;//2017-04-21 18:12:04,//签署时间 （待盖章中显示）
    private String create_time;//2017-04-20 18:12:04,//创建时间（待签署中显示）
    private String work_order;//8f75f369cf754c,    //工单号
    private String tpl_document;//,                //合同保存路径
    private String finish_time;//,                 //完成时间（已完成中显示）
    private String uid;//1}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTpl_cid() {
        return tpl_cid;
    }

    public void setTpl_cid(String tpl_cid) {
        this.tpl_cid = tpl_cid;
    }

    public String getTpl_name() {
        return tpl_name;
    }

    public void setTpl_name(String tpl_name) {
        this.tpl_name = tpl_name;
    }

    public String getTpl_form() {
        return tpl_form;
    }

    public void setTpl_form(String tpl_form) {
        this.tpl_form = tpl_form;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSign_way() {
        return sign_way;
    }

    public void setSign_way(String sign_way) {
        this.sign_way = sign_way;
    }

    public String getSign_time() {
        return sign_time;
    }

    public void setSign_time(String sign_time) {
        this.sign_time = sign_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getWork_order() {
        return work_order;
    }

    public void setWork_order(String work_order) {
        this.work_order = work_order;
    }

    public String getTpl_document() {
        return tpl_document;
    }

    public void setTpl_document(String tpl_document) {
        this.tpl_document = tpl_document;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

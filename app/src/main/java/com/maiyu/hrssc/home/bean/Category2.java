package com.maiyu.hrssc.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 获取一级业务下的二级业务
 */

public class Category2 implements Parcelable {
    private String name;//薪资证明办理,//二级业务名称
    private String id;//29,              //二级业务id
    private String comment;//null,    //二级业务描述
    private String link;//null,      //二级业务帮助描述
    private String status;//0,      //0-正常  1-隐藏
    private String create_time;//2017-03-11 16;//22;//06.0,
    private String city;//深圳市, //业务所属城市
    private String can_agent;//1,  //是否允许代办  0-不允许   1-允许
    private String cid;//1,        //一级业务的id
    private String pageid;//1},    //关联提交页id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCan_agent() {
        return can_agent;
    }

    public void setCan_agent(String can_agent) {
        this.can_agent = can_agent;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.comment);
        dest.writeString(this.link);
        dest.writeString(this.status);
        dest.writeString(this.create_time);
        dest.writeString(this.city);
        dest.writeString(this.can_agent);
        dest.writeString(this.cid);
        dest.writeString(this.pageid);
    }

    public Category2() {
    }

    protected Category2(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.comment = in.readString();
        this.link = in.readString();
        this.status = in.readString();
        this.create_time = in.readString();
        this.city = in.readString();
        this.can_agent = in.readString();
        this.cid = in.readString();
        this.pageid = in.readString();
    }

    public static final Parcelable.Creator<Category2> CREATOR = new Parcelable.Creator<Category2>() {
        @Override
        public Category2 createFromParcel(Parcel source) {
            return new Category2(source);
        }

        @Override
        public Category2[] newArray(int size) {
            return new Category2[size];
        }
    };
}

package com.maiyu.hrssc.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 模板
 */

public class Template implements Parcelable {
    private String name;//:深圳地区薪资证明模板,    //模板名称
    private String id;//:12,                          //模板id
    private String comment;//:使用地区：深圳,      //使用地区
    private String status;//:0,                      //前端忽略
    private String create_time;//:2017-04-30 18:00:49.0,//创建时间
    private String order;//:5,           //排序字段，后端已经按倒序排列，排在第一的为默认 模板
    private String city;//:深圳市,     //模板所属城市
    private String cid1;//:1,           //
    private String cid2;//:1,            //模板所属二级业务
    private String template;//:<p align=\center\><font size=\5\><strong>工资收入   证明</strong></font></p><p>　　zx{姓名} (身份证号zx{身份证号码})系我单位员工,任职zx{职位},月收入zx{月收入}元人民币,特此证明.</p><p>　　主管单位(公章)</p><p>　　时间：zx{时间}</p><p><br></p>,
    private String form;//:姓名=zx{姓名};身份证号码=zx{身份证号码};职位=zx{职位};月收入=zx{月收入};时间=zx{时间}}]
    private String link;//:www.baidu.com

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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCid1() {
        return cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public String getCid2() {
        return cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        dest.writeString(this.status);
        dest.writeString(this.create_time);
        dest.writeString(this.order);
        dest.writeString(this.city);
        dest.writeString(this.cid1);
        dest.writeString(this.cid2);
        dest.writeString(this.template);
        dest.writeString(this.form);
        dest.writeString(this.link);
    }

    public Template() {
    }

    protected Template(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.comment = in.readString();
        this.status = in.readString();
        this.create_time = in.readString();
        this.order = in.readString();
        this.city = in.readString();
        this.cid1 = in.readString();
        this.cid2 = in.readString();
        this.template = in.readString();
        this.form = in.readString();
        this.link = in.readString();
    }

    public static final Parcelable.Creator<Template> CREATOR = new Parcelable.Creator<Template>() {
        @Override
        public Template createFromParcel(Parcel source) {
            return new Template(source);
        }

        @Override
        public Template[] newArray(int size) {
            return new Template[size];
        }
    };
}

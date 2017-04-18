package com.maiyu.hrssc.integration.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/4/18.
 */

public class RecordDetail implements Parcelable {
    private String address;//深圳                      //收货地址
    private String id;// 2                                //订单id
    private String count ;//  2                            //购买数量
    private String logistics_no ;// asda14a1s1d          //物流单号
    private String deliver_time;//2017-01-02 121212;//   //发货时间
    private String deliver_user;//小李子;//                //发货人员姓名
    private String status;//  1                            //0-待发货  1-已发货 2-已收货  3-退款中  4-已退款
    private String create_time;//2017-03-14 104900;//   //下单时间
    private String order_no;//20170314165926956716;//              //订单号
    private String pname;//下单现磨核桃粉黑芝麻粉黑米粉500g ;// //商品名称
    private String pimage;//https//gd3.alicdn.com/imgextra/i3/828509818/TB2fiVfXPUd61BjSZPcXXc6hXXa_!!828509818.jpg_50x50.jpg;// //商品头图
    private String points;// 90                             //所用积分数量
    private String receiver;//  小强                      //收货人姓名
    private String contact;// 13397100547                 //收货人电话
    private String lid;//1                                  //物流id
    private String logistics;// 顺丰速运                  //物流名称
    private String uid;// 1000                              //用户id
    private String pid;//1                                //商品id


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLogistics_no() {
        return logistics_no;
    }

    public void setLogistics_no(String logistics_no) {
        this.logistics_no = logistics_no;
    }

    public String getDeliver_time() {
        return deliver_time;
    }

    public void setDeliver_time(String deliver_time) {
        this.deliver_time = deliver_time;
    }

    public String getDeliver_user() {
        return deliver_user;
    }

    public void setDeliver_user(String deliver_user) {
        this.deliver_user = deliver_user;
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

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.id);
        dest.writeString(this.count);
        dest.writeString(this.logistics_no);
        dest.writeString(this.deliver_time);
        dest.writeString(this.deliver_user);
        dest.writeString(this.status);
        dest.writeString(this.create_time);
        dest.writeString(this.order_no);
        dest.writeString(this.pname);
        dest.writeString(this.pimage);
        dest.writeString(this.points);
        dest.writeString(this.receiver);
        dest.writeString(this.contact);
        dest.writeString(this.lid);
        dest.writeString(this.logistics);
        dest.writeString(this.uid);
        dest.writeString(this.pid);
    }

    public RecordDetail() {
    }

    protected RecordDetail(Parcel in) {
        this.address = in.readString();
        this.id = in.readString();
        this.count = in.readString();
        this.logistics_no = in.readString();
        this.deliver_time = in.readString();
        this.deliver_user = in.readString();
        this.status = in.readString();
        this.create_time = in.readString();
        this.order_no = in.readString();
        this.pname = in.readString();
        this.pimage = in.readString();
        this.points = in.readString();
        this.receiver = in.readString();
        this.contact = in.readString();
        this.lid = in.readString();
        this.logistics = in.readString();
        this.uid = in.readString();
        this.pid = in.readString();
    }

    public static final Parcelable.Creator<RecordDetail> CREATOR = new Parcelable.Creator<RecordDetail>() {
        @Override
        public RecordDetail createFromParcel(Parcel source) {
            return new RecordDetail(source);
        }

        @Override
        public RecordDetail[] newArray(int size) {
            return new RecordDetail[size];
        }
    };
}

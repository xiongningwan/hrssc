package com.maiyu.hrssc.integration.bean;

/**
 * 兑换记录
 * Created by Administrator on 2017/4/6.
 */

public class Record {
    private String img_url;//https;////gd3.alicdn.com/imgextra/i3/376860083/T2uZuKXhpaXXXXXXXX_!!376860083.jpg_400x400.jpg,  //商品图片
    private String id;//3,                                   //订单id
    private String count;//1,                               //购买数量
    private String status;//1,                               //0-待发货  1-已发货  2已收货  3-退款中 4-已退款
    private String create_time;//2017-03-14 10;//50;//39,        //下单时间
    private String pname;//陇南特产山核桃仁/生核桃仁500克,      //商品名称
    private String pid;//2,                                        //商品id
    private String points;//35 //所使用的积分

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}

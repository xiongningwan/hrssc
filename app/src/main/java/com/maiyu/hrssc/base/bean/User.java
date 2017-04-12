package com.maiyu.hrssc.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    /**
     * 用户实体信息
     */
    private static final long serialVersionUID = 1L;

    private String name;//小明,                 //姓名
    private long id = -1;//1000,                      //工号
    private String signature; //http;//qiniu.ccvrzb.com//Desert.jpg,  //签名图片路径
    private String head; //http;//qiniu.ccvrzb.com//Desert.jpg,       //头像路径
    private String password; //***,              //密码已处理
    private String status; //0,                    //0-试用期  1-正式员工  2-离职员工
    private String sex; //0,                         //0-男 1-女
    private String birthday;//2017-05-12,         //生日
    private String marry;//0,                       //0-未婚  1-已婚  2-离异
    private String minority;//汉族,               //性别
    private String id_card;//420625XXXXXXXX0000,  //身份证
    private String amount;//100,                    //积分
    private String phone;//13397100547,           //手机
    private String email;//null,                    //邮箱
    private String login_time;//1489142526144,       //登录时间  毫秒值 如使用需处理
    private String create_time;//1489041248000,      //注册时间  使用需处理
    private String token;//1ffd6169d33546b7bb552c36f250c4ca  //token
    private String last_loginTime;//2017-01-01 04;02;02  //上一次登录时间，pc端使用
    private String last_loginIp;//192.168.1.1            //上一次登录ip，pc端使用
    private String signed;//1                              //0-未签到   1-已签到


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getMinority() {
        return minority;
    }

    public void setMinority(String minority) {
        this.minority = minority;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLast_loginTime() {
        return last_loginTime;
    }

    public void setLast_loginTime(String last_loginTime) {
        this.last_loginTime = last_loginTime;
    }

    public String getLast_loginIp() {
        return last_loginIp;
    }

    public void setLast_loginIp(String last_loginIp) {
        this.last_loginIp = last_loginIp;
    }

    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }


    public User() {
    }

    public User(String name, long id, String signature, String head, String password, String status, String sex, String birthday, String marry, String minority, String id_card, String amount, String phone, String email, String login_time, String create_time, String token, String last_loginTime, String last_loginIp, String signed) {
        this.name = name;
        this.id = id;
        this.signature = signature;
        this.head = head;
        this.password = password;
        this.status = status;
        this.sex = sex;
        this.birthday = birthday;
        this.marry = marry;
        this.minority = minority;
        this.id_card = id_card;
        this.amount = amount;
        this.phone = phone;
        this.email = email;
        this.login_time = login_time;
        this.create_time = create_time;
        this.token = token;
        this.last_loginTime = last_loginTime;
        this.last_loginIp = last_loginIp;
        this.signed = signed;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", signature='" + signature + '\'' +
                ", head='" + head + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", marry='" + marry + '\'' +
                ", minority='" + minority + '\'' +
                ", id_card='" + id_card + '\'' +
                ", amount='" + amount + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", login_time='" + login_time + '\'' +
                ", create_time='" + create_time + '\'' +
                ", token='" + token + '\'' +
                ", last_loginTime='" + last_loginTime + '\'' +
                ", last_loginIp='" + last_loginIp + '\'' +
                ", signed='" + signed + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.id);
        dest.writeString(this.signature);
        dest.writeString(this.head);
        dest.writeString(this.password);
        dest.writeString(this.status);
        dest.writeString(this.sex);
        dest.writeString(this.birthday);
        dest.writeString(this.marry);
        dest.writeString(this.minority);
        dest.writeString(this.id_card);
        dest.writeString(this.amount);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.login_time);
        dest.writeString(this.create_time);
        dest.writeString(this.token);
        dest.writeString(this.last_loginTime);
        dest.writeString(this.last_loginIp);
        dest.writeString(this.signed);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.id = in.readLong();
        this.signature = in.readString();
        this.head = in.readString();
        this.password = in.readString();
        this.status = in.readString();
        this.sex = in.readString();
        this.birthday = in.readString();
        this.marry = in.readString();
        this.minority = in.readString();
        this.id_card = in.readString();
        this.amount = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.login_time = in.readString();
        this.create_time = in.readString();
        this.token = in.readString();
        this.last_loginTime = in.readString();
        this.last_loginIp = in.readString();
        this.signed = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

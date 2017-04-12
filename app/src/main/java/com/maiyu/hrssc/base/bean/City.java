package com.maiyu.hrssc.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 城市
 */

public class City implements Parcelable {
    private long id;//1,
    private String create_time;//"2017-03-27 15:15:22.0",
    private String defaultOrNot;//:1  //1-默认城市 0-非默认最多只有一个默认城市
    private String word;//":"s";       //城市拼音首字母
    private String city;//深圳市"  //参数名称


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDefaultOrNot() {
        return defaultOrNot;
    }

    public void setDefaultOrNot(String defaultOrNot) {
        this.defaultOrNot = defaultOrNot;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.create_time);
        dest.writeString(this.defaultOrNot);
        dest.writeString(this.word);
        dest.writeString(this.city);
    }

    public City() {
    }

    protected City(Parcel in) {
        this.id = in.readLong();
        this.create_time = in.readString();
        this.defaultOrNot = in.readString();
        this.word = in.readString();
        this.city = in.readString();
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}

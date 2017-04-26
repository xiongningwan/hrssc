package com.maiyu.hrssc.integration.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 产品img
 */

public class Image implements Parcelable {
    private String url;// "/2017/01/asd/png"

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}

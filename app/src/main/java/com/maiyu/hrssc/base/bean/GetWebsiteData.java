package com.maiyu.hrssc.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 获取某个二级业务的基础信息（官方网站，体检、报到地址，联系人，联系方式）
 */

public class GetWebsiteData implements Parcelable {
    private CategoryBaseinfo categoryBaseinfo;
    private String link;//"http://asssss"  //帮助链接（办理说明） ，为空或null则不跳转

    public CategoryBaseinfo getCategoryBaseinfo() {
        return categoryBaseinfo;
    }

    public void setCategoryBaseinfo(CategoryBaseinfo categoryBaseinfo) {
        this.categoryBaseinfo = categoryBaseinfo;
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
        dest.writeParcelable(this.categoryBaseinfo, flags);
        dest.writeString(this.link);
    }

    public GetWebsiteData() {
    }

    protected GetWebsiteData(Parcel in) {
        this.categoryBaseinfo = in.readParcelable(CategoryBaseinfo.class.getClassLoader());
        this.link = in.readString();
    }

    public static final Parcelable.Creator<GetWebsiteData> CREATOR = new Parcelable.Creator<GetWebsiteData>() {
        @Override
        public GetWebsiteData createFromParcel(Parcel source) {
            return new GetWebsiteData(source);
        }

        @Override
        public GetWebsiteData[] newArray(int size) {
            return new GetWebsiteData[size];
        }
    };
}

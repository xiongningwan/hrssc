package com.maiyu.hrssc.home.activity.applying.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 查询业务详情
 */

public class FindApplyDetailData implements Parcelable {
    private ApplyDetail apply;
    private List<AProgress> progressList;

    public ApplyDetail getApply() {
        return apply;
    }

    public void setApply(ApplyDetail apply) {
        this.apply = apply;
    }

    public List<AProgress> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<AProgress> progressList) {
        this.progressList = progressList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.apply, flags);
        dest.writeTypedList(this.progressList);
    }

    public FindApplyDetailData() {
    }

    protected FindApplyDetailData(Parcel in) {
        this.apply = in.readParcelable(ApplyDetail.class.getClassLoader());
        this.progressList = in.createTypedArrayList(AProgress.CREATOR);
    }

    public static final Parcelable.Creator<FindApplyDetailData> CREATOR = new Parcelable.Creator<FindApplyDetailData>() {
        @Override
        public FindApplyDetailData createFromParcel(Parcel source) {
            return new FindApplyDetailData(source);
        }

        @Override
        public FindApplyDetailData[] newArray(int size) {
            return new FindApplyDetailData[size];
        }
    };
}

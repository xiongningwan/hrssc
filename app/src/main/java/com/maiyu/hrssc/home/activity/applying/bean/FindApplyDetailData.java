package com.maiyu.hrssc.home.activity.applying.bean;

import java.util.List;

/**
 * 查询业务详情
 */

public class FindApplyDetailData {
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
}

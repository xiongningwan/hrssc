package com.maiyu.hrssc.home.activity.applying.bean;

import java.util.List;

/**
 * 返回的查询我的申请 ，草稿箱中查询草稿也使用该接口数据
 */

public class GetApplysData {
    private List<Apply> applys;
    private int recordsTotal;//:2,          //查询到的记录数量
    private int drafts;//:2//草稿数量

    public List<Apply> getApplys() {
        return applys;
    }

    public void setApplys(List<Apply> applys) {
        this.applys = applys;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getDrafts() {
        return drafts;
    }

    public void setDrafts(int drafts) {
        this.drafts = drafts;
    }
}

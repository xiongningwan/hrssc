package com.maiyu.hrssc.base.bean;

/**
 * 获取某个二级业务的基础信息（官方网站，体检、报到地址，联系人，联系方式）
 */

public class GetWebsiteData {
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
}

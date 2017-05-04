package com.maiyu.hrssc.home.bean;

import java.util.List;

/**
 * 申请表单数据
 */

public class FormData {
    private List<Template> templates;

    private List<SelfAddress> addrs;

    private String link;//":"www.baidu.com"},   //帮助连接（办理说明）

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public List<SelfAddress> getAddrs() {
        return addrs;
    }

    public void setAddrs(List<SelfAddress> addrs) {
        this.addrs = addrs;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

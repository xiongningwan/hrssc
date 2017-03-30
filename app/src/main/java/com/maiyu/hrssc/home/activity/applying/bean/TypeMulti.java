package com.maiyu.hrssc.home.activity.applying.bean;

import java.util.List;

/**
 *   进度 多类型
 */

public class TypeMulti {
    private String conentText;
    private List<AttachFile> files;


    public TypeMulti() {
    }

    public TypeMulti(String conenttext, List<AttachFile> files) {
        conentText = conenttext;
        this.files = files;
    }

    public String getConenttext() {
        return conentText;
    }

    public void setConenttext(String conenttext) {
        conentText = conenttext;
    }

    public List<AttachFile> getFiles() {
        return files;
    }

    public void setFiles(List<AttachFile> files) {
        this.files = files;
    }
}

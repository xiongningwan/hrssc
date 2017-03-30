package com.maiyu.hrssc.home.activity.applying.bean;

import java.util.List;

/**
 * 进度
 */

public class Schedule {
    private String content;
    private List<AttachImage> images;
    private List<AttachFile> files;
    private String time;


    public Schedule() {
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AttachImage> getImages() {
        return images;
    }

    public void setImages(List<AttachImage> images) {
        this.images = images;
    }

    public List<AttachFile> getFiles() {
        return files;
    }

    public void setFiles(List<AttachFile> files) {
        this.files = files;
    }
}

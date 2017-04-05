package com.maiyu.hrssc.integration.bean;

/**
 * Created by Administrator on 2017/4/5.
 */

public class IngegrationProduct {
    private long id;
    private String name;
    private String jifen;
    private String imageUrl;

    public IngegrationProduct() {
    }

    public IngegrationProduct(long id, String name, String jifen) {
        this.id = id;
        this.name = name;
        this.jifen = jifen;
    }

    public IngegrationProduct(long id, String name, String jifen, String imageUrl) {
        this.id = id;
        this.name = name;
        this.jifen = jifen;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

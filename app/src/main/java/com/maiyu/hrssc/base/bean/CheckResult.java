package com.maiyu.hrssc.base.bean;

/**
 * 查询体检结果
 */

public class CheckResult {
    private String uid;// 2,
    private String result;// 测试1,
    private String create_time;// 2017-05-06 17:10:14,
    private String images;// [\/uploadFiles/uploadImgs/20170517/11/8eadab2e25d040f6b7f846c059a9fc3e.jpg\]

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}

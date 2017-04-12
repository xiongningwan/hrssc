package com.maiyu.hrssc.base.bean;

/**
 * 资讯
 */

public class News {
    private long id;//5,          //资讯id
    private String content;//,    //资讯内容，已处理为空
    private String count;//0,       //阅读次数
    private String status;//0,      //状态  无效字段
    private String city;//,       //城市   已处理为空
    private String aid;//0,         //发布人员id
    private String title;//李克强总理在澳大利亚议会大厦发表演说,
    private String cid;//0,         //资讯分类id
    private String create_time;//2017-03-29 10;//29;//46}],

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

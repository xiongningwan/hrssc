package com.maiyu.hrssc.home.activity.socialsecurity.bean;

/**
 * Created by Administrator on 2017/5/19.
 */

public class SocialSecurity {
    private String city;//"北京",                        //缴纳地
    private String paytime;//"2016-02",                  //缴纳月份
    private String yanglao;//"100.11,1.11,1.11,1.11",    //养老
    private String yiliao;//"100.11,1.11,1.11,1.11",     //医疗
    private String gongshang;//"100.11,1.11,1.11,1.11",  //工伤
    private String shiye;//"100.11,1.11,1.11,1.11",      //失业
    private String shengyu;//"100.11,1.11,1.11,1.11"     //生育

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getYanglao() {
        return yanglao;
    }

    public void setYanglao(String yanglao) {
        this.yanglao = yanglao;
    }

    public String getYiliao() {
        return yiliao;
    }

    public void setYiliao(String yiliao) {
        this.yiliao = yiliao;
    }

    public String getGongshang() {
        return gongshang;
    }

    public void setGongshang(String gongshang) {
        this.gongshang = gongshang;
    }

    public String getShiye() {
        return shiye;
    }

    public void setShiye(String shiye) {
        this.shiye = shiye;
    }

    public String getShengyu() {
        return shengyu;
    }

    public void setShengyu(String shengyu) {
        this.shengyu = shengyu;
    }
}

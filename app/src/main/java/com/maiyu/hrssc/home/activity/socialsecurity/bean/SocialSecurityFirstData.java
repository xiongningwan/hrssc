package com.maiyu.hrssc.home.activity.socialsecurity.bean;

/**
 * 首次加载个人社保
 */

public class SocialSecurityFirstData {
    private String total;//"11.10",                        //总缴纳
    private SocialSecurity socialSecurity;//
    private String personalTotal;//5.55,                   //个人缴纳
    private String showDates;//[                           //缴交的所有日期 2017-05-11",2017-05-12"],
    private String compantTotal;//5.55                    //单位缴纳

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public SocialSecurity getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(SocialSecurity socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getPersonalTotal() {
        return personalTotal;
    }

    public void setPersonalTotal(String personalTotal) {
        this.personalTotal = personalTotal;
    }

    public String getShowDates() {
        return showDates;
    }

    public void setShowDates(String showDates) {
        this.showDates = showDates;
    }

    public String getCompantTotal() {
        return compantTotal;
    }

    public void setCompantTotal(String compantTotal) {
        this.compantTotal = compantTotal;
    }
}

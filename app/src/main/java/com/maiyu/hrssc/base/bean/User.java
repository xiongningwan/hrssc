package com.maiyu.hrssc.base.bean;

import java.io.Serializable;

public class User implements Serializable {

    /**
     * 用户实体信息
     */
    private static final long serialVersionUID = 1L;

    /** 
     *登录关键字
     *-1为还未登录
     */
    private long id = -1;
    /** 电话号码*/
    private String mobile;
    /** token*/
    private String token;
    /** 用户等级*/
    private String level;
    /** 真实姓名*/
    private String realityName;
    /** 邀请码*/
    private String code;
    /** 设置回款方式 0银行卡 1余额账户*/
    private int incomeSpace;
    /** 是否进入我的奖励*/
    private int canGoRewards;
    /** 0未评测，1已评测*/
    private int isAssess = 3;
    /** 0不需要身份证，1需要身份证 */
    private int isIdCard;

    public int getIsIdCard() {
        return isIdCard;
    }

    public void setIsIdCard(int isIdCard) {
        this.isIdCard = isIdCard;
    }

    public int getIsAssess() {
        return isAssess;
    }

    public void setIsAssess(int isAssess) {
        this.isAssess = isAssess;
    }

    public int getCanGoRewards() {
        return canGoRewards;
    }

    public void setCanGoRewards(int canGoRewards) {
        this.canGoRewards = canGoRewards;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRealityName() {
        return realityName;
    }

    public void setRealityName(String realityName) {
        this.realityName = realityName;
    }

    public int getIncomeSpace() {
        return incomeSpace;
    }

    public void setIncomeSpace(int incomeSpace) {
        this.incomeSpace = incomeSpace;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", mobile=" + mobile + ", token=" + token +  ", level=" + level
                + ", realityName=" + realityName + ", code=" + code + ", incomeSpace=" + incomeSpace
                + ", canGoRewards=" + canGoRewards + "]";
    }
    
    

}

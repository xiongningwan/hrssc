package com.maiyu.hrssc.base.engine;

import android.content.Context;

import com.maiyu.hrssc.base.bean.AddressManage;
import com.maiyu.hrssc.base.bean.City;
import com.maiyu.hrssc.base.bean.HomeData;
import com.maiyu.hrssc.base.bean.Messages;
import com.maiyu.hrssc.base.bean.News;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.information.bean.NewsClass;
import com.maiyu.hrssc.my.activity.bean.MyInfo;

import java.util.List;


public interface IUserEngine {

    /**
     * 登录
     *
     * @param context
     * @param type      1-正式员工登录入口  0-新/离职员工登录入口
     * @param account    帐号
     * @param password  前端校验长度6~16位
     * @param mac       Mac地址，如果获取不到给空
     * @param version   版本号，如1.2.1
     * @param login_way ios/android/web  保证一样
     * @return
     * @throws NetException
     */
    public User login(Context context, String type, String account, String password, String mac, String version, String login_way) throws NetException;


    /**
     * 获取城市列表
     *
     * @param context
     * @param page
     * @param rows
     * @param token
     * @return
     * @throws NetException
     */
    public List<City> getCitys(Context context, String page, String rows, String token) throws NetException;


    /**
     * 获取首页信息
     *
     * @param context
     * @param city
     * @param token
     * @return
     * @throws NetException
     */
    public HomeData getHomeData(Context context, String city, String token) throws NetException;


    /**
     * 获取消息列表
     *
     * @param context
     * @param token
     * @param page
     * @param rows
     * @return
     * @throws NetException
     */
    public List<Messages> getMessageList(Context context, String token, String page, String rows) throws NetException;

    /**
     * 获取消息详情
     *
     * @param context
     * @param token
     * @param mid
     * @return
     * @throws NetException
     */
    public Messages getMessageDetial(Context context, String token, String mid) throws NetException;


    /**
     * 删除一条消息
     *
     * @param context
     * @param token
     * @param mid
     * @return
     * @throws NetException
     */
    public String deleteMessageItem(Context context, String token, String mid) throws NetException;


    /**
     * 获取资讯分类
     *
     * @param context
     * @param token
     * @return
     * @throws NetException
     */
    public List<NewsClass> getNewsClass(Context context, String token) throws NetException;


    /**
     * 获取资讯列表
     *
     * @param context
     * @param token
     * @param cid
     * @param city
     * @param page
     * @param rows
     * @return
     * @throws NetException
     */
    public List<News> getNewsList(Context context, String token, String cid, String city, String page, String rows) throws NetException;


    /**
     * 获取资讯详情
     *
     * @param context
     * @param token
     * @param nid
     * @return
     * @throws NetException
     */
    public News getInfoDetail(Context context, String token, String nid) throws NetException;


    /**
     * 签到
     *
     * @param context
     * @param token
     * @return
     * @throws NetException
     */
    public int sign(Context context, String token) throws NetException;

    /**
     * 检查是否签到
     * @param context
     * @param token
     * @return
     * @throws NetException
     */
    public int signOrNot(Context context, String token) throws NetException;

    /**
     * 修改密码
     *
     * @param context
     * @param token
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws NetException
     */
    public String updatePwd(Context context, String token, String oldPassword, String newPassword) throws NetException;

    /**
     * 反馈
     *
     * @param context
     * @param token
     * @param title
     * @param content
     * @return
     * @throws NetException
     */
    public String feedBack(Context context, String token, String title, String content) throws NetException;


    /**
     * 获取地址管理列表
     *
     * @param context
     * @param token
     * @param page
     * @param rows
     * @return
     * @throws NetException
     */
    public List<AddressManage> getManageAddressList(Context context, String token, String page, String rows) throws NetException;

    /**
     * 添加收件地址
     *
     * @param context
     * @param token
     * @param name
     * @param phone
     * @param prov
     * @param city
     * @param area
     * @param addr
     * @return
     * @throws NetException
     */
    public String addAddress(Context context, String token, String name, String phone, String prov, String city, String area, String addr) throws NetException;

    /**
     * 添加收件地址
     *
     * @param context
     * @param token
     * @param aid
     * @param name
     * @param phone
     * @param prov
     * @param city
     * @param area
     * @param addr
     * @return
     * @throws NetException
     */
    public String editAddress(Context context, String token, String aid, String name, String phone, String prov, String city, String area, String addr) throws NetException;

    /**
     * 设置默认地址
     *
     * @param context
     * @param token
     * @param aid
     * @return
     * @throws NetException
     */
    public String setDefaultAddress(Context context, String token, String aid) throws NetException;

    /**
     * 删除地址
     *
     * @param context
     * @param token
     * @param aid
     * @return
     * @throws NetException
     */
    public String delAddress(Context context, String token, String aid) throws NetException;


    /**
     * 找回密码第一步
     *
     * @param context
     * @param userId
     * @param phone
     * @return
     * @throws NetException
     */
    public String findBackPwd1(Context context, String userId, String phone) throws NetException;

    /**
     * 找回密码第二步
     * @param context
     * @param userId
     * @param code
     * @param newPassword
     * @param phone
     * @return
     * @throws NetException
     */
    public String findBackPwd2(Context context, String userId, String code, String newPassword, String phone) throws NetException;

    /**
     * 发送短信验证码
     * @param context
     * @param phone
     * @param token
     * @return
     * @throws NetException
     */
    public String getMsgCode(Context context, String phone, String token) throws NetException;
    /**
     * 发送短信验证码
     * @param context
     * @param phone
     * @return
     * @throws NetException
     */
    public String getMsgCode(Context context, String phone) throws NetException;

    /**
     * 查询个人资料信息
     * @param context
     * @param token
     * @return
     * @throws NetException
     */
    public MyInfo getMyInfo(Context context,  String token) throws NetException;


}

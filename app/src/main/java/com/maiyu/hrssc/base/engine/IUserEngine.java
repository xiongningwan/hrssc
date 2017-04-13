package com.maiyu.hrssc.base.engine;

import android.content.Context;

import com.maiyu.hrssc.base.bean.City;
import com.maiyu.hrssc.base.bean.HomeData;
import com.maiyu.hrssc.base.bean.Messages;
import com.maiyu.hrssc.base.bean.News;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.information.bean.NewsClass;

import java.util.List;


public interface IUserEngine {

    /**
     * 登录
     *
     * @param context
     * @param type      1-正式员工登录入口  0-新/离职员工登录入口
     * @param userId    工号
     * @param password  前端校验长度6~16位
     * @param mac       Mac地址，如果获取不到给空
     * @param version   版本号，如1.2.1
     * @param login_way ios/android/web  保证一样
     * @return
     * @throws NetException
     */
    public User login(Context context, String type, String userId, String password, String mac, String version, String login_way) throws NetException;


    /**
     * 获取城市列表
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
     * @param context
     * @param city
     * @param token
     * @return
     * @throws NetException
     */
    public HomeData getHomeData(Context context, String city, String token) throws NetException;


    /**
     * 获取消息列表
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
     * @param context
     * @param token
     * @param mid
     * @return
     * @throws NetException
     */
    public Messages getMessageDetial(Context context, String token, String mid) throws NetException;


    /**
     * 删除一条消息
     * @param context
     * @param token
     * @param mid
     * @return
     * @throws NetException
     */
    public String deleteMessageItem(Context context, String token, String mid) throws NetException;


    /**
     * 获取资讯分类
     * @param context
     * @param token
     * @return
     * @throws NetException
     */
    public List<NewsClass> getNewsClass(Context context, String token) throws NetException;


    /**
     * 获取资讯列表
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
     * @param context
     * @param token
     * @param nid
     * @return
     * @throws NetException
     */
    public News getInfoDetail(Context context, String token, String nid) throws NetException;


    /**
     * 签到
     * @param context
     * @param token
     * @return
     * @throws NetException
     */
    public String sign(Context context, String token) throws NetException;




}

package com.maiyu.hrssc.base.engine;

import android.content.Context;

import com.maiyu.hrssc.base.bean.City;
import com.maiyu.hrssc.base.bean.HomeData;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.exception.NetException;

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

}

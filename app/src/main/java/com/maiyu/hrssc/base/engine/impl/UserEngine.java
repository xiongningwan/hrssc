package com.maiyu.hrssc.base.engine.impl;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.bean.City;
import com.maiyu.hrssc.base.bean.HomeData;
import com.maiyu.hrssc.base.bean.Messages;
import com.maiyu.hrssc.base.bean.News;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.information.bean.NewsClass;
import com.maiyu.hrssc.util.LogHelper;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

import static com.alibaba.fastjson.JSON.parseObject;

public class UserEngine extends BaseEngine implements IUserEngine {

    @Override
    public User login(Context context, String type, String userId, String password, String mac, String version, String login_way) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_login_user_login;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("userId", userId);
        params.put("password", password);
        params.put("mac", mac);
        params.put("version", version);
        params.put("login_way", login_way);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
            LogHelper.d("HTTPResult", "json:" + json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        User user = null;
        try {
            json = parseObject(json).getString("data");
            String json_user = JSON.parseObject(json).getString("user");

            user = JSON.parseObject(json_user, User.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<City> getCitys(Context context, String page, String rows, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_fragment_home_get_citys;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<City> citys = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("cities");
            citys = JSON.parseArray(json, City.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return citys;
    }

    @Override
    public HomeData getHomeData(Context context, String city, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_fragment_home_get_page_data;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("city", city);
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        HomeData data = null;
        try {
            json = parseObject(json).getString("data");
            data = JSON.parseObject(json, HomeData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Messages> getMessageList(Context context, String token, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_message_list;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("page", page);
        params.put("rows", rows);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<Messages> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("messages");
            list = JSON.parseArray(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Messages getMessageDetial(Context context, String token, String mid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_message_detail;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("mid", mid);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        Messages data = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("message");
            data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String deleteMessageItem(Context context, String token, String mid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_message_delete_item;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("mid", mid);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        String data = null;
        try {
            data = parseObject(json).getString("msg");
            // json = parseObject(json).getString("message");
            // data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<NewsClass> getNewsClass(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_information_get_news_class;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<NewsClass> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("newsClass");
            list = JSON.parseArray(json, NewsClass.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<News> getNewsList(Context context, String token, String cid, String city, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_fragment_information_page_get_news_list;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("cid", cid);
        params.put("city", city);
        params.put("page", page);
        params.put("rows", rows);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<News> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("news");
            list = JSON.parseArray(json, News.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public News getInfoDetail(Context context, String token, String nid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_information_detail;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("nid", nid);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        News data = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("news");
            data = JSON.parseObject(json, News.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String sign(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_fragment_my_sign;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = OkHttpUtils.post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        String data = null;
        try {
            json = parseObject(json).getString("msg");
            data = parseObject(json).getString("sign_integral");
            // data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }


}

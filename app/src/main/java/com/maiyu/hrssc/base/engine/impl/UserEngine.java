package com.maiyu.hrssc.base.engine.impl;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.bean.AddressManage;
import com.maiyu.hrssc.base.bean.City;
import com.maiyu.hrssc.base.bean.HomeData;
import com.maiyu.hrssc.base.bean.Messages;
import com.maiyu.hrssc.base.bean.News;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.information.bean.NewsClass;
import com.maiyu.hrssc.my.activity.bean.MyInfo;
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
    public User login(Context context, String type, String account, String password, String mac, String version, String login_way) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_login_user_login;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("account", account);
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
    public int sign(Context context, String token) throws NetException {
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
        int data = 0;
        try {
            json = parseObject(json).getString("msg");
            data = parseObject(json).getInteger("sign_integral");
            // data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public int signOrNot(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_fragment_my_signOrNot;
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
        int data = 0;
        try {
            json = parseObject(json).getString("data");
            data = parseObject(json).getInteger("signed");
            // data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String updatePwd(Context context, String token, String oldPassword, String newPassword) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_modify_pwd;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("oldPassword", oldPassword);
        params.put("newPassword", newPassword);
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
            //json = parseObject(json).getString("msg");
            data = parseObject(json).getString("msg");
            // data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String feedBack(Context context, String token, String title, String content) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_feedback;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("title", title);
        params.put("content", content);
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
            //json = parseObject(json).getString("msg");
            data = parseObject(json).getString("msg");
            // data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<AddressManage> getManageAddressList(Context context, String token, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_manage_address_get_list;
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
        List<AddressManage> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("addrs");
            list = JSON.parseArray(json, AddressManage.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String addAddress(Context context, String token, String name, String phone, String prov, String city, String area, String addr) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_add_address;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("name", name);
        params.put("phone", phone);
        params.put("prov", prov);
        params.put("city", city);
        params.put("area", area);
        params.put("addr", addr);
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
            //json = parseObject(json).getString("msg");
            data = parseObject(json).getString("msg");
            // data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String editAddress(Context context, String token, String aid, String name, String phone, String prov, String city, String area, String addr) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_edit_address;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("aid", aid);
        params.put("name", name);
        params.put("phone", phone);
        params.put("prov", prov);
        params.put("city", city);
        params.put("area", area);
        params.put("addr", addr);
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
            //json = parseObject(json).getString("msg");
            data = parseObject(json).getString("msg");
            // data = JSON.parseObject(json, Messages.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String setDefaultAddress(Context context, String token, String aid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_manage_set_default_address;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("aid", aid);
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


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String delAddress(Context context, String token, String aid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_manage_del_address;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("aid", aid);
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String findBackPwd1(Context context, String account, String phone) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_forget_pwd_1;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("account", account);
        params.put("phone", phone);
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String findBackPwd2(Context context, String account, String code, String newPassword, String phone) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_forget_pwd_2;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("account", account);
        params.put("code", code);
        params.put("newPassword", newPassword);
        params.put("phone", phone);
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String getMsgCode(Context context, String phone, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_forget_pwd_2_get_msg_code;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
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
            data = parseObject(json).getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String getMsgCode(Context context, String phone) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_forget_pwd_2_get_msg_code;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public MyInfo getMyInfo(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_personal_info;
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
        MyInfo data = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("user");
            data = parseObject(json, MyInfo.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }


}

package com.maiyu.hrssc.base.engine.impl;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.bean.Banners;
import com.maiyu.hrssc.base.bean.CheckResult;
import com.maiyu.hrssc.base.bean.GetWebsiteData;
import com.maiyu.hrssc.base.bean.Version;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.applying.bean.FindApplyDetailData;
import com.maiyu.hrssc.home.activity.applying.bean.GetApplysData;
import com.maiyu.hrssc.home.activity.funds.bean.PublicFund;
import com.maiyu.hrssc.home.activity.socialsecurity.bean.SocialSecurityFirstData;
import com.maiyu.hrssc.home.activity.socialsecurity.bean.SocialSecurityList;
import com.maiyu.hrssc.home.activity.todo.bean.ContractFlow;
import com.maiyu.hrssc.home.activity.todo.bean.Todo;
import com.maiyu.hrssc.home.bean.Category1;
import com.maiyu.hrssc.home.bean.Category2;
import com.maiyu.hrssc.home.bean.FormData;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

import static com.alibaba.fastjson.JSON.parseArray;
import static com.alibaba.fastjson.JSON.parseObject;
import static com.zhy.http.okhttp.OkHttpUtils.post;

public class BizEngine extends BaseEngine implements IBizEngine {
    @Override
    public List<Todo> findMyContract(Context context, String token, String page, String rows, String status) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_todo_findmycontract;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("page", page);
        params.put("rows", rows);
        params.put("status", status);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<Todo> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("clist");
            list = parseArray(json, Todo.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Banners getBanner(Context context, String token, String location) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_fragment_banner;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("location", location);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        Banners banners = null;
        try {
            json = JSON.parseObject(json).getString("data");
            json = JSON.parseObject(json).getString("banner");
            banners = JSON.parseObject(json, Banners.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return banners;
    }

    @Override
    public ContractFlow getContractFlow(Context context, String token, String id) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_toto_detail;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("id", id);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        ContractFlow contractFlow = null;
        try {
            json = JSON.parseObject(json).getString("data");
            json = JSON.parseObject(json).getString("contractFlow");
            contractFlow = JSON.parseObject(json, ContractFlow.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contractFlow;
    }

    @Override
    public String signContractFlow(Context context, String token, String id, String sign_way) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_toto_detail_sign;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("id", id);
        params.put("sign_way", sign_way);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        String str = null;
        try {
            str = JSON.parseObject(json).getString("msg");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public List<Category1> getCategory1(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_fragment_home_get_category_1;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<Category1> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("category1List");
            list = parseArray(json, Category1.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Category2> getCategory2(Context context, String token, String cid, String city) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_business_get_category_2;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("cid", cid);
        params.put("city", city);

        Log.i("xndebug", "token:"+token+"cid:"+cid+"city"+city);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();

            PostFormBuilder p = OkHttpUtils.post();
            Log.i("xndebug", "OkHttpUtils.post():"+p);
            PostFormBuilder t = p.tag(context);
            Log.i("xndebug", "p.tag:"+t);
            PostFormBuilder u = t.url(urlString);
            Log.i("xndebug", "t.url:"+u);
            PostFormBuilder b = u.params(params);
            Log.i("xndebug", "u.params:"+b);
            RequestCall e = b.build();
            e.execute();


            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("xndebug", "json:"+json.toString());
        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<Category2> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("category2List");
            list = parseArray(json, Category2.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("xndebug", "list:"+list.toString());
        return list;
    }

    @Override
    public FormData getTemplates(Context context, String token, String cid2, String city) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_business_get_getTemplates;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("cid2", cid2);
        params.put("city", city);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        FormData data = null;
        try {
            json = JSON.parseObject(json).getString("data");
            //json = parseObject(json).getString("category2List");
            data = JSON.parseObject(json, FormData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public GetWebsiteData getWebsite(Context context, String token, String cid2, String city) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_business_get_getWebsite;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("cid2", cid2);
        params.put("city", city);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        GetWebsiteData data = null;
        try {
            json = JSON.parseObject(json).getString("data");
            data = JSON.parseObject(json, GetWebsiteData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String getLink(Context context, String token, String cid2) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_business_get_getLink;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("cid2", cid2);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
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
            json = JSON.parseObject(json).getString("data");
            data = JSON.parseObject(json).getString("link");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String submitApply(Context context, String aid, String token, String type, String city, String cid2, String get_way, String address, String address_info,
                              String recipient, String tpl_tid, String tpl_form, String brief, String comment, String language, String images, String attachs) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_business_submitApply;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("aid", aid);
        params.put("token", token);
        params.put("type", type);
        params.put("city", city);
        params.put("cid2", cid2);
        params.put("get_way", get_way);
        params.put("address", address);
        params.put("address_info", address_info);
        params.put("recipient", recipient);
        params.put("tpl_tid", tpl_tid);
        params.put("tpl_form", tpl_form);
        params.put("brief", brief);
        params.put("comment", comment);
        params.put("language", language);
        params.put("images", images);
        params.put("attachs", attachs);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
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
            data = JSON.parseObject(json).getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String uploadPicture(Context context, String token, File file) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.FILE_SERVER_URI + ConstantValue.path_uploadPicture;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).addFile("file", file.getName(), file).build().execute();
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
            json = JSON.parseObject(json).getString("data");
            data = JSON.parseObject(json).getString("path");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String uploadFile(Context context, String token, File filr) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.FILE_SERVER_URI + ConstantValue.path_uploadFile;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).addFile("filr", filr.getName(), filr).build().execute();
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
            json = JSON.parseObject(json).getString("data");
            data = JSON.parseObject(json).getString("path");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public GetApplysData getApplys(Context context, String token, String status, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getApplys;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("status", status);
        params.put("page", page);
        params.put("rows", rows);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        GetApplysData data = null;
        try {
            json = JSON.parseObject(json).getString("data");
            data = JSON.parseObject(json, GetApplysData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String gain(Context context, String token, String aid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_gain;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("aid", aid);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
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
            data = JSON.parseObject(json).getString("msg");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String deleteBusiness(Context context, String token, String aid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_deleteBusiness;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("aid", aid);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
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
            data = JSON.parseObject(json).getString("msg");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public FindApplyDetailData findApplyDetail(Context context, String token, String aid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_findApplyDetail;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("aid", aid);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        FindApplyDetailData data = null;
        try {
            json = JSON.parseObject(json).getString("data");
            data = JSON.parseObject(json, FindApplyDetailData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public CheckResult getHealthCheck(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getHealthCheck;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        CheckResult data = null;
        try {
            json = JSON.parseObject(json).getString("data");
            json = JSON.parseObject(json).getString("checkResult");
            data = JSON.parseObject(json, CheckResult.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public SocialSecurityFirstData getSocialSecurityFirst(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getSocialSecurityFirst;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        SocialSecurityFirstData data = null;
        try {
            json = JSON.parseObject(json).getString("data");
            data = JSON.parseObject(json, SocialSecurityFirstData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public SocialSecurityFirstData getSocialSecurityByDate(Context context, String token, String qryDate) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getSocialSecurityByDate;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("qryDate", qryDate);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        SocialSecurityFirstData data = null;
        try {
            json = JSON.parseObject(json).getString("data");
            data = JSON.parseObject(json, SocialSecurityFirstData.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }


    @Override
    public PublicFund getPublicFundFirst(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getPublicFundFirst;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        PublicFund data = null;
        try {
            json = JSON.parseObject(json).getString("data");
            json = JSON.parseObject(json).getString("publicFund");
            data = JSON.parseObject(json, PublicFund.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<PublicFund> getPublicFunds(Context context, String token, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getPublicFunds;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("page", page);
        params.put("rows", rows);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<PublicFund> list = null;
        try {
            json = JSON.parseObject(json).getString("data");
            json = JSON.parseObject(json).getString("publicFunds");
            list = parseArray(json, PublicFund.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SocialSecurityList> getSocialSecurityDateWithTotalList(Context context, String token, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getSocialSecurityDateWithTotalList;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("page", page);
        params.put("rows", rows);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<SocialSecurityList> list = null;
        try {
            json = JSON.parseObject(json).getString("data");
            json = JSON.parseObject(json).getString("socialSecurityList");
            list = parseArray(json, SocialSecurityList.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> getEvaluateTags(Context context, String token) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getEvaluateTags;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        List<String> list = null;
        try {
            json = JSON.parseObject(json).getString("data");
            json = JSON.parseObject(json).getString("tags");
            list = JSON.parseArray(json, String.class);
            //  arr = json.split(",");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String evaluateApply(Context context, String token, String aid, String star1, String star2, String comment1, String comment2, String tag) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_evaluateApply;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("aid", aid);
        params.put("star1", star1);
        params.put("star2", star2);
        params.put("comment1", comment1);
        params.put("comment2", comment2);
        params.put("tag", tag);
        Response response = null;
        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        String str = null;
        try {
            str = JSON.parseObject(json).getString("msg");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public Version getVersion(Context context) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_getVersion;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "0");
        Response response = null;

        String json = "";
        try {
            response = post().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        Version version = null;
        try {
            json = JSON.parseObject(json).getString("data");
            json = JSON.parseObject(json).getString("version");
            version = JSON.parseObject(json, Version.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return version;
    }

    @Override
    public String electronSign(Context context, String token, String aid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.FILE_SERVER_URI + ConstantValue.path_electronSign;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("aid", aid);
        Response response = null;

        String json = "";
        try {
            response = OkHttpUtils.get().tag(context).url(urlString).params(params).build().execute();
            json = new String(response.body().bytes(), ConstantValue.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 处理返回码
        dispatcherException(context, json);

        // 解析返回的数据并封装
        String str = null;
        try {
            str = JSON.parseObject(json).getString("data");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}

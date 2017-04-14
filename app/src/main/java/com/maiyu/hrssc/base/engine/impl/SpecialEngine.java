package com.maiyu.hrssc.base.engine.impl;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.engine.ISpecialEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.my.activity.bean.FastService;
import com.maiyu.hrssc.my.activity.bean.Question;
import com.maiyu.hrssc.my.activity.bean.QuestionClass;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

import static com.alibaba.fastjson.JSON.parseObject;

public class SpecialEngine extends BaseEngine implements ISpecialEngine {
    @Override
    public List<FastService> fastService(Context context, String token, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_fast_service_list;
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
        List<FastService> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("services");
            list = JSON.parseArray(json, FastService.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<QuestionClass> questClassList(Context context, String token, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_problem_class_list;
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
        List<QuestionClass> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("classes");
            list = JSON.parseArray(json, QuestionClass.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Question> getSubQuestionList(Context context, String token, String cid, String search, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_problem_class_sub_list;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("cid", cid);
        params.put("search", search);
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
        List<Question> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("questions");
            list = JSON.parseArray(json, Question.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Question getQuestiontDetail(Context context, String token, String qid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_problem_class_sub_get_question_detail;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("qid", qid);
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
        Question data = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("question");
            data = JSON.parseObject(json, Question.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

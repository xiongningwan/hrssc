package com.maiyu.hrssc.base.engine.impl;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.todo.bean.Todo;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

import static com.alibaba.fastjson.JSON.parseObject;

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
        List<Todo> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("clist");
            list = JSON.parseArray(json, Todo.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

package com.maiyu.hrssc.base.engine.impl;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.engine.IIntegrationEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.integration.bean.Image;
import com.maiyu.hrssc.integration.bean.IngegrationProduct;
import com.maiyu.hrssc.integration.bean.ProductDetail;
import com.maiyu.hrssc.integration.bean.Record;
import com.maiyu.hrssc.integration.bean.RecordDetail;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

import static com.alibaba.fastjson.JSON.parseArray;
import static com.alibaba.fastjson.JSON.parseObject;

/**
 * 积分
 */

public class IntegrationEngine extends BaseEngine implements IIntegrationEngine {
    @Override
    public List<IngegrationProduct> getProducts(Context context, String token, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_fragment_integration;
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
        List<IngegrationProduct> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("products");
            list = JSON.parseArray(json, IngegrationProduct.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ProductDetail getProductDetail(Context context, String token, String pid) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_product_item;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("pid", pid);
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
        ProductDetail data = null;
        try {
            json = parseObject(json).getString("data");
            data = parseObject(json, ProductDetail.class);
            json = parseObject(json).getString("iamges");
            List<Image> images = parseArray(json, Image.class);
            data.setImages(images);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Record> getMyProductOrder(Context context, String token, String page, String rows) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_product_order;
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
        List<Record> list = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("orders");
            list = JSON.parseArray(json, Record.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public RecordDetail getRecordDetail(Context context, String token, String orderId) throws NetException {
        // 发送请求地址到服务器
        String urlString = ConstantValue.SERVER_URI + ConstantValue.path_activity_product_order_detail;
        // 添加参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("orderId", orderId);
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
        RecordDetail data = null;
        try {
            json = parseObject(json).getString("data");
            json = parseObject(json).getString("order");
            data = parseObject(json, RecordDetail.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

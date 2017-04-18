package com.maiyu.hrssc.base.engine;

import android.content.Context;

import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.integration.bean.IngegrationProduct;
import com.maiyu.hrssc.integration.bean.ProductDetail;
import com.maiyu.hrssc.integration.bean.Record;
import com.maiyu.hrssc.integration.bean.RecordDetail;

import java.util.List;

/**
 * 积分接口
 */

public interface IIntegrationEngine {
    /**
     * 获取用户积分兑换的商品列表
     *
     * @param context
     * @param token
     * @param page
     * @param rows
     * @return
     * @throws NetException
     */
    public List<IngegrationProduct> getProducts(Context context, String token, String page, String rows) throws NetException;

    /**
     * 查询商品详情
     * @param context
     * @param token
     * @param pid
     * @return
     * @throws NetException
     */
    public ProductDetail getProductDetail(Context context, String token, String pid) throws NetException;

    /**
     * 获取我的兑换记录   按照下单时间倒序排列
     * @param context
     * @param token
     * @param page
     * @param rows
     * @return
     * @throws NetException
     */
    public List<Record> getMyProductOrder(Context context, String token, String page, String rows) throws NetException;

    /**
     * 获取订单详情
     * @param context
     * @param token
     * @param orderId
     * @return
     * @throws NetException
     */
    public RecordDetail getRecordDetail(Context context, String token, String orderId) throws NetException;


}

package com.maiyu.hrssc.base.engine;

import android.content.Context;

import com.maiyu.hrssc.base.bean.Banners;
import com.maiyu.hrssc.base.bean.GetWebsiteData;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.todo.bean.ContractFlow;
import com.maiyu.hrssc.home.activity.todo.bean.Todo;
import com.maiyu.hrssc.home.bean.Category1;
import com.maiyu.hrssc.home.bean.Category2;
import com.maiyu.hrssc.home.bean.FormData;

import java.io.File;
import java.util.List;

/**
 * 特别接口
 */

public interface IBizEngine {
    /**
     * 待我办理列表（即合同列表）
     *
     * @param context 上下文
     * @param token   token
     * @param page    页
     * @param rows    数
     * @param status  状态：0-待签署  1-待盖章  2-已撤销  3-已完成
     * @return 合同列表
     * @throws NetException 网络异常
     */
    List<Todo> findMyContract(Context context, String token, String page, String rows, String status) throws NetException;

    /**
     * 获取banner  正常只有1条数据 ，特殊情况时没有数据，返回banner=null，这时显示默认图片。
     *
     * @return
     * @throws NetException
     */
    Banners getBanner(Context context, String token, String location) throws NetException;


    /**
     * @param context 上下文
     * @param token   token
     * @param id      合同ID
     * @return
     * @throws NetException
     */
    ContractFlow getContractFlow(Context context, String token, String id) throws NetException;

    /**
     * 签署合同方式
     *
     * @param context
     * @param token
     * @param id
     * @param sign_way
     * @return
     * @throws NetException
     */
    String signContractFlow(Context context, String token, String id, String sign_way) throws NetException;


    /**
     * 获取全部一级业务
     *
     * @param context
     * @param token
     * @return
     * @throws NetException
     */
    List<Category1> getCategory1(Context context, String token) throws NetException;

    /**
     * 获取一级业务下的二级业务
     *
     * @param context
     * @param token
     * @param cid
     * @param city
     * @return
     * @throws NetException
     */
    List<Category2> getCategory2(Context context, String token, String cid, String city) throws NetException;

    /**
     * 获取该业务 可以使用的全部模板   2.获取该城市的全部自取地址
     *
     * @param context
     * @param token
     * @param cid2
     * @param city
     * @return
     * @throws NetException
     */
    FormData getTemplates(Context context, String token, String cid2, String city) throws NetException;


    /**
     * 获取某个二级业务的基础信息（官方网站，体检、报到地址，联系人，联系方式）
     *
     * @param context
     * @param token
     * @param cid2
     * @param city
     * @return
     * @throws NetException
     */
    GetWebsiteData getWebsite(Context context, String token, String cid2, String city) throws NetException;

    /**
     * 获取某个二级业务的办理说明（适用学位证明、工卡照片、预约入职）
     * @param context
     * @param token
     * @param cid2
     * @return
     * @throws NetException
     */
    String getLink(Context context, String token, String cid2) throws NetException;

    /**
     * 提交申请（适用证明类、学位证明、工卡照片、预约入职）
     * @param context
     * @param token
     * @param type
     * @param city
     * @param cid2
     * @param get_way
     * @param address
     * @param address_info
     * @param recipient
     * @param tpl_tid
     * @param tpl_form
     * @param brief
     * @param comment
     * @param language
     * @param images
     * @param attachs
     * @return
     * @throws NetException
     */
    String submitApply(Context context, String token, String type, String city, String cid2, String get_way,
                       String address, String address_info, String recipient, String tpl_tid, String tpl_form,
                       String brief, String comment, String language, String images, String attachs) throws NetException;

    /**
     * 上传图片的统一调用此接口
     * @param context
     * @param token
     * @param file
     * @return
     * @throws NetException
     */
    String uploadPicture(Context context, String token, File file) throws NetException;

    /**
     * 用于上传文件，如压缩包，文档，mp3等其他文件（图片除外）
     * @param context
     * @param token
     * @param filr
     * @return
     * @throws NetException
     */
    String uploadFile(Context context, String token, File filr) throws NetException;
}

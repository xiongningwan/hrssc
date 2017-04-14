package com.maiyu.hrssc.base.engine;

import android.content.Context;

import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.my.activity.bean.FastService;
import com.maiyu.hrssc.my.activity.bean.Question;
import com.maiyu.hrssc.my.activity.bean.QuestionClass;

import java.util.List;

/**
 * 特别接口
 */

public interface ISpecialEngine {
    /**
     * 快捷服务
     *
     * @param context
     * @param token
     * @param page
     * @param rows
     * @return
     * @throws NetException
     */
    public List<FastService> fastService(Context context, String token, String page, String rows) throws NetException;

    /**
     * 问题分类
     *
     * @param context
     * @param token
     * @param page
     * @param rows
     * @return
     * @throws NetException
     */
    public List<QuestionClass> questClassList(Context context, String token, String page, String rows) throws NetException;


    /**
     * 根据分类/关键词   获取问题列表  已经按照创建时间倒序排列
     * @param context
     * @param token
     * @param cid
     * @param search
     * @param page
     * @param rows
     * @return
     * @throws NetException
     */
    public List<Question> getSubQuestionList(Context context, String token, String cid, String search, String page, String rows) throws NetException;


    /**
     * 根据id查找详情
     * @param context
     * @param token
     * @param qid
     * @return
     * @throws NetException
     */
    public Question getQuestiontDetail(Context context, String token, String qid) throws NetException;
}

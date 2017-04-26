package com.maiyu.hrssc.base.engine;

import android.content.Context;

import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.todo.bean.Todo;

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


}

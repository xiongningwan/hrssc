package com.jhj.common.permission.biz;


import com.jhj.common.permission.dispatcher.PermissionRequest;

/**
 * 描述：照相机接口
 * 作者： xiongning
 * 日期： 2016/8/22
 * 时间： 10:53
 */
public interface IContactsBiz {
    void showContacts();
    void showRationaleForContact(PermissionRequest request);
    void onContactDenied();
    void onContactNeverAskAgain();
}

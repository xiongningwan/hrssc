package com.jhj.common.permission.biz;

import com.jhj.common.permission.dispatcher.PermissionRequest;

/**
 * Created by Spirit on 2016/8/23 16:38.
 */
public interface ICallPhoneBiz {
    void showCallPhone();
    void showRationaleForCallPhone(PermissionRequest request);
    void onCallPhoneDenied();
    void onCallPhoneNeverAskAgain();
}

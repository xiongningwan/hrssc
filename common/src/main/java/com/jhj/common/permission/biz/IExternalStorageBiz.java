package com.jhj.common.permission.biz;

import com.jhj.common.permission.dispatcher.PermissionRequest;

/**
 * Created by Spirit on 2016/8/24 9:48.
 */
public interface IExternalStorageBiz {
    void showExternalStorage();
    void showRationaleForExternalStorage(PermissionRequest request);
    void onExternalStorageDenied();
    void onExternalStorageNeverAskAgain();
}

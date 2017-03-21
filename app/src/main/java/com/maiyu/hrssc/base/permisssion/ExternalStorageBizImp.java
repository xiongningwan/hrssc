package com.maiyu.hrssc.base.permisssion;

import android.content.Context;
import android.widget.Toast;

import com.jhj.common.R;
import com.jhj.common.permission.biz.IExternalStorageBiz;
import com.jhj.common.permission.dispatcher.PermissionRequest;
import com.jhj.common.permission.dispatcher.PermissionsDispatcher;
/**
 * 描述： 外存储器的读写操作动态权限接口实现
 * 作者： xiongning
 * 日期： 2016/8/25
 * 时间： 9:40
 */
public abstract class ExternalStorageBizImp implements IExternalStorageBiz {
    private Context mContext;
    private PermissionsDispatcher mPemissionsDispatcher;
    public ExternalStorageBizImp(Context context, PermissionsDispatcher permissionDispatcher ) {
        mContext = context;
        mPemissionsDispatcher = permissionDispatcher;

    }


    @Override
    public void showExternalStorage() {

    }

    @Override
    public void showRationaleForExternalStorage(PermissionRequest request) {
        mPemissionsDispatcher.showRationaleDialog(mContext, R.string.permission_external_storage_rationale, request);
    }

    @Override
    public void onExternalStorageDenied() {
        Toast.makeText(mContext, R.string.permission_external_storage_denied, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExternalStorageNeverAskAgain() {
        Toast.makeText(mContext, R.string.permission_external_storage_never_askagain, Toast.LENGTH_SHORT).show();
    }

}

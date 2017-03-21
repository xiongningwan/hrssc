package com.maiyu.hrssc.base.permisssion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.jhj.common.permission.biz.ICallPhoneBiz;
import com.jhj.common.permission.dispatcher.PermissionRequest;
import com.jhj.common.permission.dispatcher.PermissionsDispatcher;
import com.maiyu.hrssc.R;


/**
 * 描述： 对打电话的业务实现以及权限动态分配
 * 作者： xiongning
 * 日期： 2016/8/25
 * 时间： 9:40
 */
public class CallPhoneBizImp implements ICallPhoneBiz {
    private Context mContext;
    private PermissionsDispatcher mPemissionsDispatcher;
    public CallPhoneBizImp(Context context, PermissionsDispatcher permissionDispatcher ) {
        mContext = context;
        mPemissionsDispatcher = permissionDispatcher;

    }


    @Override
    public void showCallPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + mContext.getResources().getString(R.string.phone_number));
        intent.setData(data);
        mContext.startActivity(intent);
    }

    @Override
    public void showRationaleForCallPhone(PermissionRequest request) {
        mPemissionsDispatcher.showRationaleDialog(mContext, R.string.permission_callphone_rationale, request);
    }

    @Override
    public void onCallPhoneDenied() {
        Toast.makeText(mContext, R.string.permission_callphone_denied, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallPhoneNeverAskAgain() {
        Toast.makeText(mContext, R.string.permission_callphone_never_askagain, Toast.LENGTH_SHORT).show();
    }
}

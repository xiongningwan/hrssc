// This file was generated by PermissionsDispatcher. Do not modify!
package com.jhj.common.permission.dispatcher;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.jhj.common.R;
import com.jhj.common.permission.biz.ICallPhoneBiz;
import com.jhj.common.permission.biz.ICameraBiz;
import com.jhj.common.permission.biz.IContactsBiz;
import com.jhj.common.permission.biz.IExternalStorageBiz;

import java.lang.ref.WeakReference;

public class PermissionsDispatcher {


    private static final int REQUEST_SHOWCAMERA = 0;

    private static final String[] PERMISSION_SHOWCAMERA = new String[] {"android.permission.CAMERA"};

    private static final int REQUEST_SHOWCONTACTS = 1;

    private  final String[] PERMISSION_SHOWCONTACTS = new String[] {"android.permission.READ_CONTACTS","android.permission.WRITE_CONTACTS"};

    private static final int REQUEST_SHOWCALLPHONE = 2;
    private static final String[] PERMISSION_SHOWCALLPHONE = new String[] {Manifest.permission.CALL_PHONE};//拨打电话权限

    private static final int REQUEST_SHOW_EXTERNAL_STORAGE = 3;
    private static final String[] PERMISSION_SHOW_EXTERNAL_STORAGE = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};//读写SD卡权限

    private  ICameraBiz mCameraBizListener = null;
    private  IContactsBiz mContactsBizListener = null;
    private ICallPhoneBiz mCallPhoneBizListener = null;
    private IExternalStorageBiz mExternalStorageBizListener = null;

    public PermissionsDispatcher() {
    }


    /**
     * 添加执行业务回调接口
     * @param l
     */
    public void setCameraBizListener(ICameraBiz l) {
            this.mCameraBizListener = l;
    }
    public void setContactsBizListener(IContactsBiz l) {
            this.mContactsBizListener = l;
    }

    public void setCallPhoneBizListener(ICallPhoneBiz l) {
        this.mCallPhoneBizListener = l;
    }

    public void setExternalStorageBizListener(IExternalStorageBiz l) {
        this.mExternalStorageBizListener = l;
    }


    /**
     * 检查拨打电话
     * @param target
     */
    public  void showCallPhoneWithCheck(Activity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCALLPHONE)) {
            mCallPhoneBizListener.showCallPhone();
        } else {
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCALLPHONE)) {
                mCallPhoneBizListener.showRationaleForCallPhone(new ShowCallPhonePermissionRequest(target));
            } else {
                ActivityCompat.requestPermissions(target, PERMISSION_SHOWCALLPHONE, REQUEST_SHOWCALLPHONE);
            }
        }
    }

    /**
     * 检查读写SD卡
     * @param target
     */
    public  void showExternalStorageWithCheck(Activity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOW_EXTERNAL_STORAGE)) {
            mExternalStorageBizListener.showExternalStorage();
        } else {
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOW_EXTERNAL_STORAGE)) {
                mExternalStorageBizListener.showRationaleForExternalStorage(new ShowExternalStoragePermissionRequest(target));
            } else {
                ActivityCompat.requestPermissions(target, PERMISSION_SHOW_EXTERNAL_STORAGE, REQUEST_SHOW_EXTERNAL_STORAGE);
            }
        }
    }


    public  void showCameraWithCheck(Activity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCAMERA)) {
            mCameraBizListener.showCamera();
        } else {
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {
                mCameraBizListener.showRationaleForCamera(new ShowCallPhonePermissionRequest(target));
            } else {
                ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, REQUEST_SHOWCAMERA);
            }
        }
    }

    public  void showContactsWithCheck(Activity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCONTACTS)) {
            mContactsBizListener.showContacts();
        } else {
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCONTACTS)) {
                mContactsBizListener.showRationaleForContact(new ShowContactsPermissionRequest(target));
            } else {
                ActivityCompat.requestPermissions(target, PERMISSION_SHOWCONTACTS, REQUEST_SHOWCONTACTS);
            }
        }
    }

    public void onRequestPermissionsResult(Activity target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SHOWCAMERA:
                if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCAMERA)) {
                    mCameraBizListener.onCameraDenied();
                    return;
                }
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    mCameraBizListener.showCamera();
                } else {
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {
                        mCameraBizListener.onCameraNeverAskAgain();
                    } else {
                        mCameraBizListener.onCameraDenied();
                    }
                }
                break;
            case REQUEST_SHOWCONTACTS:
                if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCONTACTS)) {
                    return;
                }
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    mContactsBizListener.showContacts();
                } else {
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCONTACTS)) {
                        mContactsBizListener.onContactNeverAskAgain();
                    } else {
                        mContactsBizListener.onContactDenied();
                    }
                }
                break;

            case REQUEST_SHOWCALLPHONE://拨打电话
                if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCALLPHONE)) {
                    return;
                }
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    mCallPhoneBizListener.showCallPhone();
                } else {
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCALLPHONE)) {
                        mCallPhoneBizListener.onCallPhoneNeverAskAgain();
                    } else {
                        mCallPhoneBizListener.onCallPhoneDenied();
                    }
                }
                break;

            case REQUEST_SHOW_EXTERNAL_STORAGE://读写SD卡
                if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOW_EXTERNAL_STORAGE)) {
                    return;
                }
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    mExternalStorageBizListener.showExternalStorage();
                } else {
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOW_EXTERNAL_STORAGE)) {
                        mExternalStorageBizListener.onExternalStorageNeverAskAgain();
                    } else {
                        mExternalStorageBizListener.onExternalStorageDenied();
                    }
                }
                break;
            default:
                break;
        }
    }

    private  final class ShowCameraPermissionRequest implements PermissionRequest {
        private final WeakReference<Activity> weakTarget;

        private ShowCameraPermissionRequest(Activity target) {
            this.weakTarget = new WeakReference<>(target);
        }

        @Override
        public void proceed() {
            Activity target = weakTarget.get();
            if (target == null) return;
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, REQUEST_SHOWCAMERA);
        }

        @Override
        public void cancel() {
            Activity target = weakTarget.get();
            if (target == null) return;
            mCameraBizListener.onCameraDenied();
        }
    }

    private  final class ShowContactsPermissionRequest implements PermissionRequest {
        private final WeakReference<Activity> weakTarget;

        private ShowContactsPermissionRequest(Activity target) {
            this.weakTarget = new WeakReference<>(target);
        }

        @Override
        public void proceed() {
            Activity target = weakTarget.get();
            if (target == null) return;
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCONTACTS, REQUEST_SHOWCONTACTS);
        }

        @Override
        public void cancel() {
            Activity target = weakTarget.get();
            if (target == null) return;
            mContactsBizListener.onContactDenied();
        }
    }


    /**
     * 拨打电话权限请求
     */
    private  final class ShowCallPhonePermissionRequest implements PermissionRequest {
        private final WeakReference<Activity> weakTarget;

        private ShowCallPhonePermissionRequest(Activity target) {
            this.weakTarget = new WeakReference<>(target);
        }

        @Override
        public void proceed() {
            Activity target = weakTarget.get();
            if (target == null) return;
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCALLPHONE, REQUEST_SHOWCALLPHONE);
        }

        @Override
        public void cancel() {
            Activity target = weakTarget.get();
            if (target == null) return;
            mCallPhoneBizListener.onCallPhoneDenied();
        }
    }


    /**
     * 读写SD卡权限请求
     */
    private  final class ShowExternalStoragePermissionRequest implements PermissionRequest {
        private final WeakReference<Activity> weakTarget;

        private ShowExternalStoragePermissionRequest(Activity target) {
            this.weakTarget = new WeakReference<>(target);
        }

        @Override
        public void proceed() {
            Activity target = weakTarget.get();
            if (target == null) return;
            ActivityCompat.requestPermissions(target, PERMISSION_SHOW_EXTERNAL_STORAGE, REQUEST_SHOW_EXTERNAL_STORAGE);
        }

        @Override
        public void cancel() {
            Activity target = weakTarget.get();
            if (target == null) return;
            mExternalStorageBizListener.onExternalStorageDenied();
        }
    }


    public void showRationaleDialog(Context context, @StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(context)
                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }
}

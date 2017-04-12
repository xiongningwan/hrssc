package com.maiyu.hrssc.base.view.dialog;

import android.content.Context;
import android.widget.TextView;

import com.maiyu.hrssc.R;


public class LoadingDialog {

    public CreateBaseDialog mLoadingDialog;
    private static TextView mText;

    public LoadingDialog(Context context) {
        createLoadingDialog(context);
    }

    public CreateBaseDialog getDialog() {
        return mLoadingDialog;
    }

    /**
     * 创建loadingdailog
     * @return 
     */
    public void createLoadingDialog(Context context) {
        mLoadingDialog = new CreateBaseDialog(context, R.style.SettingDialog);
        mLoadingDialog.setContentView(R.layout.view_page_loading);
        mText = (TextView) mLoadingDialog.findViewById(R.id.pro_text);
        //mText.setText("Loading ...");
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setCanceledOnTouchOutside(false);
    }

    public void showDialog() {
        if(mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void closeDialog() {
        if(mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }


}

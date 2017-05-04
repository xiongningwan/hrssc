package com.maiyu.hrssc.home.activity.todo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.maiyu.hrssc.R;


public class ConfirmDialog {
    private CreateBaseDialog mTipDialog;
    private TextView mTitle;
    private TextView mContent;
    private TextView mCancleBtn;
    private TextView mConfirmBtn;

    public ConfirmDialog(Context context, OnClickListener listener) {
        createTipDialog(context, listener);
    }

    public CreateBaseDialog getDialog() {
        return mTipDialog;
    }

    public void createTipDialog(final Context context, OnClickListener listener) {
        mTipDialog = new CreateBaseDialog(context, R.style.TipDialog);
        mTipDialog.setContentView(R.layout.view_ordinary);
        mTitle = (TextView) mTipDialog.findViewById(R.id.title);
        mContent = (TextView) mTipDialog.findViewById(R.id.content);
        mCancleBtn = (TextView) mTipDialog.findViewById(R.id.cancle_btn);
        mConfirmBtn = (TextView) mTipDialog.findViewById(R.id.confirm_btn);

        mCancleBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mTipDialog != null && mTipDialog.isShowing()) {
                    mTipDialog.dismiss();
                }

            }
        });
        mConfirmBtn.setOnClickListener(listener);

    }

    public class CreateBaseDialog extends Dialog {

        private Dialog baseDialog;

        public CreateBaseDialog(Context context, int theme) {
            super(context, theme);
            baseDialog = new Dialog(context, theme);
            Window win = baseDialog.getWindow();
            WindowManager.LayoutParams params = win.getAttributes();
            params.dimAmount = 0.0f;
            params.gravity = Gravity.CENTER_VERTICAL;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
    }

    public void setTitleText(String title) {
        mTitle.setText(title);
    }

    public void setContentText(String content) {
        mContent.setText(content);
    }

    public void show() {
        mTipDialog.show();
    }

    public void dissmiss() {
        mTipDialog.dismiss();
    }


    public void closeDialog() {
        if (mTipDialog != null && mTipDialog.isShowing()) {
            mTipDialog.dismiss();
        }
    }
}

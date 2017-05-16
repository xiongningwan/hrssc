package com.maiyu.hrssc.home.activity.applying.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.maiyu.hrssc.R;


public class DraftsDialog {
    private CreateBaseDialog mTipDialog;
    private TextView mQuitNoDraft;
    private TextView mQuitHaveDraft;
    private TextView mCancleBtn;
    private Context mContext;

    public DraftsDialog(Context context, OnClickListener listener) {
        createTipDialog(context, listener);
    }

    public CreateBaseDialog getDialog() {
        return mTipDialog;
    }

    public void createTipDialog(final Context context, OnClickListener listener) {
        mContext = context;
        mTipDialog = new CreateBaseDialog(context, R.style.TipDialog);
        mTipDialog.setContentView(R.layout.view_drafts_dialog);
        mQuitNoDraft = (TextView) mTipDialog.findViewById(R.id.quit_no_draft);
        mQuitHaveDraft = (TextView) mTipDialog.findViewById(R.id.quit_have_draft);
        mCancleBtn = (TextView) mTipDialog.findViewById(R.id.cancle_btn);

        mQuitNoDraft.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mTipDialog != null && mTipDialog.isShowing()) {
                    mTipDialog.dismiss();
                    ((Activity) mContext).finish();
                }

            }
        });
        mQuitHaveDraft.setOnClickListener(listener);

        mCancleBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
            }
        });
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

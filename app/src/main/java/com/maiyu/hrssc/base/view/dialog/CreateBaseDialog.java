package com.maiyu.hrssc.base.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * 创建BaseDialog类
 */
public class CreateBaseDialog extends Dialog {

    private Dialog baseDialog;

    public CreateBaseDialog(Context context, int theme) {
        super(context, theme);
        baseDialog = new Dialog(context, theme);
        Window win = baseDialog.getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        params.dimAmount = 0.0f;
        params.gravity = Gravity.CENTER_VERTICAL;
    }
}

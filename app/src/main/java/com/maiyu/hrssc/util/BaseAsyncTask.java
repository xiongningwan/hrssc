package com.maiyu.hrssc.util;

import android.content.Context;
import android.os.AsyncTask;

import com.maiyu.hrssc.base.exception.NetException;


public abstract class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    protected NetException exception;

    protected boolean checkException(Context context) {
        if (context != null && exception != null) {
            HintUitl.toastShort(context, exception.getMessage());
            return true;
        }
        return false;
    }
}

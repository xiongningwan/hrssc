package com.maiyu.hrssc.my.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.bean.Version;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.DownloadService;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.ImageLoaderUtil;
import com.maiyu.hrssc.util.PackageInfoUtil;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.clear_cache_rl)
    RelativeLayout mClearCacheRl;
    @BindView(R.id.about_us_rl)
    RelativeLayout mAboutUsRl;
    @BindView(R.id.update_lable)
    TextView mUpdateLable;
    @BindView(R.id.arraw_iv)
    ImageView mArrawIv;
    @BindView(R.id.update_hint_tv)
    TextView mUpdateHintTv;
    @BindView(R.id.cache_hint_tv)
    TextView mCacheHintTv;
    @BindView(R.id.update_rl)
    RelativeLayout mUpdateRl;
    @BindView(R.id.logout_rl)
    RelativeLayout mLogoutRL;
    private LoadingDialog mLoadingDialog;
    private Version mVersion;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("设置", true, false);
        mLoadingDialog = new LoadingDialog(this);
        try {
            String size = ImageLoaderUtil.getTotalCacheSize(this);
            mCacheHintTv.setText(size);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initData() {
        new VersionCheckAsyncTask().execute();
    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.clear_cache_rl, R.id.about_us_rl, R.id.update_rl, R.id.logout_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_cache_rl:
                clearData();
                break;

            case R.id.about_us_rl:
                startActivity(new Intent(this, AboutActivity.class));
                break;

            case R.id.update_rl:
                // 更新
                if (mVersion != null) {

                    showDialog(mVersion);
                } else {
                    HintUitl.toastShort(this, "没有可更新的");
                }
                break;

            case R.id.logout_rl:
                // 清除 datacenter  shareperence 中getLoginNamePwd  sveUserBaseInfo getLoginNamePwd

                User user = new User();
                user.setId(-1);
                user.setToken("-1");
                DataCenter.getInstance().setUser(user);

                SharedPreferencesUtil.saveLoginNamePwd(this, null, null, "1");

                SharedPreferencesUtil.saveUserBaseInfo(this, user);


                setResult(RESULT_OK);
                finish();
                break;
        }
    }

    private void clearData() {
        new ClearCacheAsyncTask().execute();
       /* new Thread(new Runnable() {

            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }).start();*/
    }

    class ClearCacheAsyncTask extends BaseAsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ImageLoaderUtil.clearCache(SettingActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            try {
                String size = ImageLoaderUtil.getTotalCacheSize(SettingActivity.this);
                mCacheHintTv.setText(size);
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }


    private void showDialog(final Version version) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("升级提示");
        builder.setMessage(version.getComment());

        //0-不强制  1-强制更新
        if ("1".equals(version.getMust())) {
            builder.setCancelable(false);

        } else {

            builder.setNegativeButton("稍后再说", new AlertDialog.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
        }


        builder.setPositiveButton("立刻更新", new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(SettingActivity.this, DownloadService.class);
                intent.putExtra("url", version.getUrl());
                startService(intent);
            }
        });
        builder.show();
    }


    /**
     * 加载数据异步任务
     */
    class VersionCheckAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private Version version;

        public VersionCheckAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                version = engine.getVersion(SettingActivity.this);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(SettingActivity.this)) {
                return;
            }
            if (version != null) {
                if (version.getCode() > PackageInfoUtil.getVersionCode(SettingActivity.this)) {
                    mUpdateHintTv.setText("有最新版本!");
                    mVersion = version;
                } else {
                    mUpdateHintTv.setText("已是最新版本!");
                }
            }
            super.onPostExecute(result);
        }
    }
}

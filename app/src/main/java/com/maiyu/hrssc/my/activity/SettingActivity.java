package com.maiyu.hrssc.my.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.ImageLoaderUtil;
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
}

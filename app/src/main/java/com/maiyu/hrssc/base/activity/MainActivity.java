package com.maiyu.hrssc.base.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.Version;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.CustomViewPager;
import com.maiyu.hrssc.home.frament.HomeFragment;
import com.maiyu.hrssc.integration.frament.IntegrationFragment;
import com.maiyu.hrssc.my.frament.MyFragment;
import com.maiyu.hrssc.service.frament.ServiceFragment;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.DownloadService;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.PackageInfoUtil;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.util.ArrayList;

public class MainActivity extends CheckPermissionsActivity {
    private static final String TAG = "MainActivity";
    private CustomViewPager mContentView;
    private RelativeLayout mTabHomeView;
    private RelativeLayout mTabJifenView;
    private RelativeLayout mTabKefuView;
    private RelativeLayout mTabMyView;
    /**
     * activity 中的 fragment
     */
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private ArrayList<RelativeLayout> mTabViews = new ArrayList<RelativeLayout>();
    private TabFragmentPagerAdapter mTabAdapter;
    private long mExitTime = 0;
    /**
     * 更新时间间隔  12小时
     */
    private long period = 86400000 / 2;
   /* private PermissionsDispatcher mPemissionsDispatcher;
    private UpdateExternalStorageBizImp mExternalStorageBizImp;*/

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_main);
        //   checkPermission();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initViews() {
        //viewPager
        mContentView = (CustomViewPager) findViewById(R.id.activity_main_content);
        //3个tab
        mTabHomeView = (RelativeLayout) findViewById(R.id.activity_main_bottom_tab_home);
        mTabJifenView = (RelativeLayout) findViewById(R.id.activity_main_bottom_tab_jifen);
        mTabKefuView = (RelativeLayout) findViewById(R.id.activity_main_bottom_tab_kefu);
        mTabMyView = (RelativeLayout) findViewById(R.id.activity_main_bottom_tab_my);
        mTabHomeView.setSelected(true);


        mTabHomeView.setOnClickListener(this);
        mTabJifenView.setOnClickListener(this);
        mTabKefuView.setOnClickListener(this);
        mTabMyView.setOnClickListener(this);

        //将tab加入到列表中
        mTabViews.add(mTabHomeView);
        mTabViews.add(mTabJifenView);
        mTabViews.add(mTabKefuView);
        mTabViews.add(mTabMyView);

        // 把fragment加入到列表中
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(IntegrationFragment.newInstance());
        mFragments.add(ServiceFragment.newInstance());
        mFragments.add(MyFragment.newInstance());

        // 给mContentView设置tabAdapter
        mContentView.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), mFragments));
        mContentView.setOnPageChangeListener(new TabPageChangeListener());
        mContentView.setOffscreenPageLimit(3);
        mContentView.setScanScroll(false);
        mTabViews.get(0).setSelected(true);
        // StatusBarCompat.translucentStatusBar(this, false); // 状态栏半透明
    }

    public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public TabFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }

    /**
     * pager 改变的时候tab产生变化
     */
    public class TabPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int currentItem) {

            for (int i = 0; i < mTabViews.size(); i++) {
                if (i == currentItem) {
                    mTabViews.get(i).setSelected(true);
                } else {
                    mTabViews.get(i).setSelected(false);
                }
            }
        }
    }

    @Override
    public void initData() {
        initXG();
        autoUpdate();
    }

    @Override
    public void initOnClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_bottom_tab_home:
                mContentView.setCurrentItem(0, true);
                break;

            case R.id.activity_main_bottom_tab_jifen:
                mContentView.setCurrentItem(1, true);
                break;

            case R.id.activity_main_bottom_tab_kefu:
                mContentView.setCurrentItem(2, true);
                break;

            case R.id.activity_main_bottom_tab_my:
                mContentView.setCurrentItem(3, true);
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                HintUitl.toastShort(this, "再按一次退出");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void goBackHome() {
        mContentView.setCurrentItem(0, true);
    }

    public void finishHome() {
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (resultCode == 100) {
            goBackHome();
        }*/



       /* if(InfoRequestCode == requestCode) {
            HomeFragment2 hf2 = ((HomeFragment2)mFragments.get(0));
            if(hf2 != null) {
                hf2.updateInfo();
            }
        }
*/
    }


    /**
     * 根据一定的时间间隔自动更新
     */
    private void autoUpdate() {
        long code = PackageInfoUtil.getVersionCode(MainActivity.this);
      /*  if (AppUtil.networkConnected(this)) {
            
            new VersionCheckAsyncTask(ConstantValue.CLIENT_TYPE_ANDROID, String.valueOf(code)).execute();
        }*/
        // 关闭每天2次检查间隔，使用每次进入主界面进行检查
       /* if (SharedPreferencesUtil.isPopUp(this, period)) {
        }*/

        new VersionCheckAsyncTask().execute();
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
                Intent intent = new Intent(MainActivity.this, DownloadService.class);
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
                version = engine.getVersion(MainActivity.this);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(MainActivity.this)) {
                return;
            }
            if (version != null) {
                if (version.getCode() > PackageInfoUtil.getVersionCode(MainActivity.this)) {
                    // 更新
                    showDialog(version);
                }
            }
            super.onPostExecute(result);
        }
    }

   /* private void checkPermission() {
        // 动态权限
        mPemissionsDispatcher =  new PermissionsDispatcher();
        mExternalStorageBizImp = new UpdateExternalStorageBizImp(this, mPemissionsDispatcher);
        mPemissionsDispatcher.setExternalStorageBizListener(mExternalStorageBizImp);
        mPemissionsDispatcher.showExternalStorageWithCheck(this);
    }*/



/*class UpdateExternalStorageBizImp extends ExternalStorageBizImp {

    public UpdateExternalStorageBizImp(Context context, PermissionsDispatcher permissionDispatcher) {
        super(context, permissionDispatcher);
    }

    @Override
    public void showExternalStorage() {
        super.showExternalStorage();
        autoUpdate(); // 自动更新
    }
}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPemissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }*/


    /*********一下是信鸽推送初始化**************/
    private void initXG() {

        // 开启logcat输出，方便debug，发布时请关闭
        //XGPushConfig.enableDebug(this, true);
        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
        // 具体可参考详细的开发指南
        // 传递的参数为ApplicationContext
        final String account = DataCenter.getInstance().getuser().getAccount();
        XGPushConfig.enableDebug(this, true);
        XGPushManager.registerPush(this, account, new XGIOperateCallback() {

            @Override
            public void onSuccess(Object data, int flag) {
                Log.d("TPush", "注册成功，设备token为：" + data + "account:" + account);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }

        });

        // 2.36（不包括）之前的版本需要调用以下2行代码
        /*  Intent service = new Intent(context, XGPushServiceV3.class);
          context.startService(service);*/

        // 其它常用的API：
        // 绑定账号（别名）注册：registerPush(context,account)或registerPush(context,account, XGIOperateCallback)，其中account为APP账号，可以为任意字符串（qq、openid或任意第三方），业务方一定要注意终端与后台保持一致。
        // 取消绑定账号（别名）：registerPush(context,"*")，即account="*"为取消绑定，解绑后，该针对该账号的推送将失效
        // 反注册（不再接收消息）：unregisterPush(context)
        // 设置标签：setTag(context, tagName)
        // 删除标签：deleteTag(context, tagName)

    }

    // 信鸽推送需要
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);// 必须要调用这句
    }


}

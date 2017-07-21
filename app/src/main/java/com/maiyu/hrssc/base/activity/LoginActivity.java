package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.util.AppUtil;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.PackageInfoUtil;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.zte.android.common.log.Log;
import cn.com.zte.android.securityauth.config.SSOAuthConfig;
import cn.com.zte.android.securityauth.interfaces.SSOAuthCheckCallBack;
import cn.com.zte.android.securityauth.manager.SSOAuthCheckManager;
import cn.com.zte.android.securityauth.manager.SSOAuthLoginManager;
import cn.com.zte.android.securityauth.model.UserInfo;
import cn.com.zte.android.widget.dialog.DialogManager;

public class LoginActivity extends BaseActivity {
    String TAG = "LoginActivity";
    @BindView(R.id.work_no_et)
    EditText mWorkNoEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;
    @BindView(R.id.new_employee_tv)
    TextView mNewEmployeeTv;
    @BindView(R.id.forget_pwd_tv)
    TextView mForgetPwdTv;
    @BindView(R.id.sso_btn)
    Button mSSOBtn;
    private LoadingDialog mLoadingDialog;
    private SSOAuthCheckManager acm;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    public void initData() {
        Map<String, String> map = SharedPreferencesUtil.getLoginNamePwd(this);
        String loginName = map.get("loginName");
        mWorkNoEt.setText(loginName);
        // 自动登录
        autoLogin();
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.confirm_btn, R.id.new_employee_tv, R.id.forget_pwd_tv, R.id.sso_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_btn:
                // 登录
                doLogin();
                break;
            case R.id.new_employee_tv:
                startActivity(new Intent(this, NewEmployeeLoginActivity.class));
                // finish();
                break;
            case R.id.forget_pwd_tv:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.sso_btn:
                checkSSOLogin();
                break;
        }
    }

    private void doLogin() {
        String mWorkNo = mWorkNoEt.getText().toString();
        String mPwd = mPwdEt.getText().toString();

        if (mWorkNo == null || "".equals(mWorkNo) || mPwd == null || "".equals(mPwd)) {
            HintUitl.toastShort(this, "输入框不能为空");
            return;
        }

       /* if (mPwd.length() < 6 || mPwd.length() > 16) {
            HintUitl.toastShort(this, "密码长度应为6-16位");
            return;
        }*/

        String type = "1";
        String account = mWorkNo;
        // String password = AppUtil.md5(mPwd);
        String password = mPwd;
        String mac = AppUtil.getMac(LoginActivity.this);
        String version = PackageInfoUtil.getVersionName(this);
        String login_way = ConstantValue.CLIENT_TYPE_ANDROID;
        new LoginAsyncTask(type, account, password, mac, version, login_way).execute();
    }

    private void doMOALogin(UserInfo moaUserInfo) {
        if (moaUserInfo == null) {
            return;
        }

        String account = moaUserInfo.getUID();
        // String password = AppUtil.md5(mPwd);
        String name = moaUserInfo.getCNM();
        String mac = AppUtil.getMac(LoginActivity.this);
        String version = PackageInfoUtil.getVersionName(this);
        String login_way = ConstantValue.CLIENT_TYPE_ANDROID;
        new MOALoginAsyncTask(account, name, mac, version, login_way).execute();
    }


    class LoginAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String type;
        private String account;
        private String password;
        private String mac;
        private String version;
        private String login_way;
        private User user;

        public LoginAsyncTask(String type, String account, String password, String mac, String version, String login_way) {
            super();

            this.type = type;
            this.account = account;
            this.password = password;
            this.mac = mac;
            this.version = version;
            this.login_way = login_way;
        }

        @Override
        protected void onPreExecute() {
            mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                user = engine.login(LoginActivity.this, type, account, password, mac, version, login_way);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(LoginActivity.this)) {
                return;
            }
            if (user != null) {
                // 保存帐号
                SharedPreferencesUtil.saveLoginNamePwd(LoginActivity.this, account, password, type);
                // 将数据存入数据中心
                DataCenter.getInstance().setUser(user);
                // 将数据存入缓存
                SharedPreferencesUtil.saveUserBaseInfo(LoginActivity.this, user);
                // 保存登录帐号和密码

                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                finish();
            }

            super.onPostExecute(result);
        }
    }


    class MOALoginAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String account;
        private String name;
        private String mac;
        private String version;
        private String login_way;
        private User user;

        public MOALoginAsyncTask(String account, String name, String mac, String version, String login_way) {
            super();

            this.account = account;
            this.name = name;
            this.mac = mac;
            this.version = version;
            this.login_way = login_way;
        }

        @Override
        protected void onPreExecute() {
            mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                user = engine.userLoginMOA(LoginActivity.this, account, name, mac, version, login_way);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(LoginActivity.this)) {
                return;
            }
            if (user != null) {
                // 保存帐号
                // SharedPreferencesUtil.saveLoginNamePwd(LoginActivity.this, account, password, type);
                // 将数据存入数据中心
                DataCenter.getInstance().setUser(user);
                // 将数据存入缓存
                SharedPreferencesUtil.saveUserBaseInfo(LoginActivity.this, user);
                // 保存登录帐号和密码

                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                finish();
            }

            super.onPostExecute(result);
        }
    }


    void autoLogin() {
        Map<String, String> map = SharedPreferencesUtil.getLoginNamePwd(this);
        String loginName = map.get("loginName");
        String loginPwd = map.get("loginPwd");
        String loginType = map.get("loginType");
        if (loginName != null && loginPwd != null && !loginName.equals("") && !loginPwd.equals("")) {
            // 登录
            String type = loginType;
            String mac = AppUtil.getMac(LoginActivity.this);
            String version = PackageInfoUtil.getVersionName(this);
            String login_way = ConstantValue.CLIENT_TYPE_ANDROID;
            new LoginAsyncTask(type, loginName, loginPwd, mac, version, login_way).execute();
        }

    }

   /* static {
        {
            try {
                System.loadLibrary("modp-algorithm");
               // return;
            } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
                Log.e("AlgorithmJni", localUnsatisfiedLinkError.getMessage());
                //return;
            } catch (Exception localException) {
                Log.e("AlgorithmJni", localException.getMessage());
            }
        }

    }*/

    /**
     * @return void 返回类型
     * @throws
     * @Title: checkSSOLogin
     * @Description: SSL登录检查
     */

    private void checkSSOLogin() {

       /* try {
           System.loadLibrary("modp-algorithm");
           System.loadLibrary("ssl");
            // return;
        } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
            Log.e("AlgorithmJni", localUnsatisfiedLinkError.getMessage());
            //return;
        } catch (Exception localException) {
            Log.e("AlgorithmJni", localException.getMessage());
        }*/

        SSOAuthCheckCallBack authCheckCallBack = new SSOAuthCheckCallBack() {

            /**
             * 应用关闭前回调，可以做一些现场保护数据操作.
             *
             * @see cn.com.zte.android.securityauth.interfaces.SSOAuthCheckCallBack#onAppClosePre()
             */
            @Override
            public void onAppClosePre() {
                // 应用关闭前回调，可以做一些现场保护数据操作.
                mLoadingDialog.getDialog().dismiss();
                // SplashActivity.this.finish();
              //  Toast.makeText(LoginActivity.this, "onAppClosePre()", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMOANotInstalled() {
                mLoadingDialog.getDialog().dismiss();
                // 弹出安装MOA的提醒
                DialogManager.showToast(LoginActivity.this, getResources()

                        .getString(R.string.app_text_need_install_moa));

            }

            @Override
            public void onAuthSuccess() {
                mLoadingDialog.getDialog().dismiss();
                Log.i(TAG, "onAuthSuccess...");
                DialogManager.showToast(getApplicationContext(), getResources()

                        .getString(R.string.app_text_sso_success));
                //   goToMainActivity();
                // 去主页面

                // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                // finish();
                if (acm != null) {
                    UserInfo userinfo = acm.getUserInfo();
                    Log.i(TAG, userinfo.toString());
                    doMOALogin(userinfo);
                }
            }

            /**
             * Http通讯错误回调.
             *
             * @see  //cn.com.zte.android.securityauth.sso.client.AuthCheckCallBack#onHttpError()
             */
            @Override
            public void onHttpError(String strCode, String strMsg) {
                mLoadingDialog.getDialog().dismiss();
                // 弹出错误信息
                DialogManager.showToast(getApplicationContext(), strMsg);

                // 阻止进入下一界面
                //  finish();
            }


            @Override
            public void onAuthFailureTrans() {
                mLoadingDialog.getDialog().dismiss();
                Toast.makeText(LoginActivity.this, "token 校验失败 ...", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String content) {
                mLoadingDialog.getDialog().dismiss();
                Toast.makeText(LoginActivity.this, "校验token 通信失败 ...", Toast.LENGTH_LONG).show();
            }
        };

        // 构造SSOAuthCheckManager
        acm = new SSOAuthCheckManager(this, "A00262", authCheckCallBack, false);
/*
        SSOAuthCheckManager acm = new SSOAuthCheckManager(this,
                MyApplication.getAppid(), authCheckCallBack, false);
*/

        // 配置IP和端口
        acm.config(R.xml.map_sso_config);
        SSOAuthLoginManager.IS_TEST_ENVIRONMENT = true;
        SSOAuthConfig.setMoaLauncherActvityName("com.zte.softda.StartActivity");
        // 执行SSO检查
        acm.check();
        mLoadingDialog.getDialog().show();
    }
}

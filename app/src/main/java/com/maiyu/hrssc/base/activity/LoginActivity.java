package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

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
    private LoadingDialog mLoadingDialog;


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
        String loginName = SharedPreferencesUtil.getLoginName(this);
        mWorkNoEt.setText(loginName);

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.confirm_btn, R.id.new_employee_tv, R.id.forget_pwd_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_btn:
                // 登录
                doLogin();
                break;
            case R.id.new_employee_tv:
                startActivity(new Intent(this, NewEmployeeLoginActivity.class));
                finish();
                break;
            case R.id.forget_pwd_tv:
                startActivity(new Intent(this, ForgetPwdActivity.class));
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
        String userId = mWorkNo;
        // String password = AppUtil.md5(mPwd);
        String password = mPwd;
        String mac = AppUtil.getMac(LoginActivity.this);
        String version = PackageInfoUtil.getVersionName(this);
        String login_way = ConstantValue.CLIENT_TYPE_ANDROID;
        new LoginAsyncTask(type, userId, password, mac, version, login_way).execute();
    }


    class LoginAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String type;
        private String userId;
        private String password;
        private String mac;
        private String version;
        private String login_way;
        private User user;

        public LoginAsyncTask(String type, String userId, String password, String mac, String version, String login_way) {
            super();

            this.type = type;
            this.userId = userId;
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
                user = engine.login(LoginActivity.this, type, userId, password, mac, version, login_way);
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
                SharedPreferencesUtil.saveLoginName(LoginActivity.this, userId);
                // 将数据存入数据中心
                DataCenter.getInstance().setUser(user);
                // 将数据存入缓存
                SharedPreferencesUtil.saveUserBaseInfo(LoginActivity.this, user);

                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                finish();
            }

            super.onPostExecute(result);
        }
    }

}

package com.maiyu.hrssc.base.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPwd2Activity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.verify_code_et)
    EditText mVerifyCodeEt;
    @BindView(R.id.get_verify_code_btn)
    Button mGetVerifyCodeBtn;
    @BindView(R.id.work_no_et)
    EditText mWorkNoEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;
    private LoadingDialog mLoadingDialog;
    private String mCode;
    private String mNewPwd;
    private String mConfirmPwd;
    private String userId;
    private String phone;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_forget_pwd2);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("忘记密码", true, false);
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    public void initData() {
        userId = getIntent().getStringExtra("idCard");
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.get_verify_code_btn, R.id.confirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_verify_code_btn:
                doCode();
                break;
            case R.id.confirm_btn:
                doResetPwd();
                break;
        }
    }


    private void doCode() {
        TimeCount time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        time.start();//开始计时
        new MsgCodeAsyncTask(userId, phone).execute();
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            mGetVerifyCodeBtn.setText("重新获取");
            mGetVerifyCodeBtn.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            mGetVerifyCodeBtn.setClickable(false);
            mGetVerifyCodeBtn.setText(millisUntilFinished / 1000 + "s");
        }
    }


    private void doResetPwd() {
        if (!Validate()) {
            return;
        }

        new TwoStepAsyncTask(userId, mCode, mNewPwd, phone).execute();
    }

    private boolean Validate() {
        mCode = mVerifyCodeEt.getText().toString();
        mNewPwd = mWorkNoEt.getText().toString();
        mConfirmPwd = mPwdEt.getText().toString();

        if (mCode == null || mNewPwd == null || mConfirmPwd == null ||
                "".equals(mCode) || "".equals(mNewPwd) || "".equals(mConfirmPwd)) {
            HintUitl.toastShort(this, "输入框不能为空");
            return false;
        }

        if (!mNewPwd.equals(mConfirmPwd)) {
            HintUitl.toastShort(this, "两次密码不一致");
            return false;
        }

        if (mNewPwd.length() < 6 || mNewPwd.length() > 16) {
            HintUitl.toastShort(this, "密码长度应在6-16位");
            return false;
        }


        return true;
    }

    /**
     * 短信验证码
     */
    class MsgCodeAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String phone;
        private String token;
        private String str;

        public MsgCodeAsyncTask(String phone, String token) {
            super();

            this.phone = phone;
            this.token = token;
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
                str = engine.getMsgCode(ForgetPwd2Activity.this, phone, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(ForgetPwd2Activity.this)) {
                return;
            }
            if (str != null && "操作成功".equals(str)) {
                // 进入下一步
                HintUitl.toastShort(ForgetPwd2Activity.this, "短信验证码已发送");
            }

            super.onPostExecute(result);
        }
    }


    /**
     * 找回密码
     */
    class TwoStepAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String userId;
        private String code;
        private String newPassword;
        private String phone;
        private String str;

        public TwoStepAsyncTask(String userId, String code, String newPassword, String phone) {
            super();

            this.userId = userId;
            this.code = code;
            this.newPassword = newPassword;
            this.phone = phone;
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
                str = engine.findBackPwd2(ForgetPwd2Activity.this, userId, code, newPassword, phone);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(ForgetPwd2Activity.this)) {
                return;
            }
            if (str != null && "操作成功".equals(str)) {
                // 进入下一步
                finish();
            }

            super.onPostExecute(result);
        }
    }

}

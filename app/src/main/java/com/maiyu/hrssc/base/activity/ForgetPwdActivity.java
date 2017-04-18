package com.maiyu.hrssc.base.activity;

import android.content.Intent;
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

public class ForgetPwdActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.work_no_et)
    EditText mWorkNoEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;
    private LoadingDialog mLoadingDialog;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("忘记密码", true, false);
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick(R.id.confirm_btn)
    public void onClick() {
        doFindBackPWdFirstStep();

    }

    private void doFindBackPWdFirstStep() {
        String mWorkNo = mWorkNoEt.getText().toString(); // 帐号
        String mPwd = mPwdEt.getText().toString(); // 手机号

        if (mWorkNo == null || "".equals(mWorkNo) || mPwd == null || "".equals(mPwd)) {
            HintUitl.toastShort(this, "输入框不能为空");
            return;
        }

        if (mPwd.length() < 6 || mPwd.length() > 16) {
            HintUitl.toastShort(this, "密码长度应为6-16位");
            return;
        }

        String account = mWorkNo;
        String password = mPwd;

        new OneStepAsyncTask(account, password).execute();
    }

    class OneStepAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String account;
        private String password;
        private String str;

        public OneStepAsyncTask(String account, String password) {
            super();

            this.account = account;
            this.password = password;
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
                str = engine.findBackPwd1(ForgetPwdActivity.this, account, password);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(ForgetPwdActivity.this)) {
                return;
            }
            if (str != null && "操作成功".equals(str)) {
                // 进入下一步
                Intent intent = new Intent(ForgetPwdActivity.this, ForgetPwd2Activity.class);
                intent.putExtra("account", account);
                intent.putExtra("phone", password); //  shoujihao
                startActivity(intent);
                finish();
            }

            super.onPostExecute(result);
        }
    }
}

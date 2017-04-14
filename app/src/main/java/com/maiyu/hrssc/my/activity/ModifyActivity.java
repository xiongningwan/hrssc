package com.maiyu.hrssc.my.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
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

/**
 * 修改密码
 */
public class ModifyActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.old_pwd_et)
    EditText mOldPwdEt;
    @BindView(R.id.new_pwd_et)
    EditText mNewPwdEt;
    @BindView(R.id.new_pwd_confirm_et)
    EditText mNewPwdConfirmEt;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;
    private LoadingDialog mLoadingDialog;
    private String mToken;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_modify_pwd);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("修改密码", true, false);
        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick(R.id.confirm_btn)
    public void onClick() {
        doUpdatePwd();

    }

    private void doUpdatePwd() {
        String oldPwd = mOldPwdEt.getText().toString();
        String newPwd = mNewPwdEt.getText().toString();
        String confirmPwd = mNewPwdConfirmEt.getText().toString();

        if (oldPwd == null || newPwd == null || confirmPwd == null ||
                "".equals(newPwd) || "".equals(newPwd) || "".equals(confirmPwd)) {
            HintUitl.toastShort(this, "输入框不能为空");
            return;
        }

        if (!newPwd.equals(confirmPwd)) {
            HintUitl.toastShort(this, "两次输入密码不一致");
            return;
        }

        if (newPwd.length() < 6 || newPwd.length() > 16) {
            HintUitl.toastShort(this, "密码长度应为6-16位");
            return;
        }

        new UpdatePwdAsyncTask(mToken, oldPwd, newPwd).execute();

    }


    /**
     * 修改密码
     */
    class UpdatePwdAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String oldPwd;
        private String newPwd;
        private String str;

        public UpdatePwdAsyncTask(String token, String oldPwd, String newPwd) {
            super();
            this.token = token;
            this.oldPwd = oldPwd;
            this.newPwd = newPwd;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingDialog.getDialog().show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                str = engine.updatePwd(ModifyActivity.this, token, oldPwd, newPwd);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(ModifyActivity.this)) {
                return;
            }
            if (str != null && !str.equals("")) {
                HintUitl.toastShort(ModifyActivity.this, str);
                finish();
            }

            super.onPostExecute(result);
        }
    }
}

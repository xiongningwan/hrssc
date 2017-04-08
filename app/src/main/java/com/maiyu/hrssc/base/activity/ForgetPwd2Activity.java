package com.maiyu.hrssc.base.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.view.HeadView;

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

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_forget_pwd2);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("忘记密码", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.get_verify_code_btn, R.id.confirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_verify_code_btn:
                break;
            case R.id.confirm_btn:
                break;
        }
    }
}

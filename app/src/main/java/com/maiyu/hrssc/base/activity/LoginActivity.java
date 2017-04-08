package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.maiyu.hrssc.R;

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


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.confirm_btn, R.id.new_employee_tv, R.id.forget_pwd_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_btn:
                break;
            case R.id.new_employee_tv:
                startActivity(new Intent(this, NewEmployeeLoginActivity.class));
                break;
            case R.id.forget_pwd_tv:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
        }
    }
}

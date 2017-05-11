package com.maiyu.hrssc.home.activity;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提交成功
 */
public class SucceedActivity extends BaseActivity {


    @BindView(R.id.Left_button_rl)
    RelativeLayout mLeftButtonRl;
    @BindView(R.id.btn_back_home_work)
    Button mBtnBackHomeWork;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_succeed);
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


    @OnClick({R.id.Left_button_rl, R.id.btn_back_home_work})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Left_button_rl:
                finish();
                break;
            case R.id.btn_back_home_work:
                finish();
                break;
        }
    }
}

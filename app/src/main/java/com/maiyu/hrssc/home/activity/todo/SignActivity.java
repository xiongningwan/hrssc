package com.maiyu.hrssc.home.activity.todo;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.sign_iv)
    ImageView mSignIv;
    @BindView(R.id.no_sign_ll)
    LinearLayout mNoSignLl;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("我的签名", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }
}

package com.maiyu.hrssc.my.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 帮助中心
 */
public class HelpCenterActivity extends BaseActivity {
    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.search_et)
    EditText mSearchEt;
    @BindView(R.id.search_btn)
    TextView mSearchBtn;
    @BindView(R.id.wentifenlei_rl)
    RelativeLayout mWentifenleiRl;
    @BindView(R.id.gongzilei_rl)
    RelativeLayout mGongzileiRl;
    @BindView(R.id.zhengmingbanlilei_rl)
    RelativeLayout mZhengmingbanlileiRl;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_help_center);
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


    @OnClick({R.id.back_iv, R.id.search_et, R.id.search_btn, R.id.wentifenlei_rl, R.id.gongzilei_rl, R.id.zhengmingbanlilei_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                break;
            case R.id.search_et:
                break;
            case R.id.search_btn:
                break;
            case R.id.wentifenlei_rl:
                break;
            case R.id.gongzilei_rl:
                break;
            case R.id.zhengmingbanlilei_rl:
                break;
        }
    }
}

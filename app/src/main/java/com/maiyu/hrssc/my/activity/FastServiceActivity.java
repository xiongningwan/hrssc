package com.maiyu.hrssc.my.activity;

import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 快捷服务
 */
public class FastServiceActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeadView mHeadView;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_fast_service);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("快捷服务", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.xiecheng_goto_btn, R.id.qunaer__goto_btn, R.id.damai__goto_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xiecheng_goto_btn:
                break;
            case R.id.qunaer__goto_btn:
                break;
            case R.id.damai__goto_btn:
                break;
        }
    }
}

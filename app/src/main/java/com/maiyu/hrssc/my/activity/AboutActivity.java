package com.maiyu.hrssc.my.activity;

import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于我们
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeadView mHeadView;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("关于我们", true, false);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }
}

package com.maiyu.hrssc.home.activity.information;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationDetialActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.title_name)
    TextView mTitleName;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.have_content_rl)
    RelativeLayout mHaveContentRl;
    @BindView(R.id.have_no_content_iv)
    ImageView mHaveNoContentIv;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_information_detial);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("资讯详情", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }
}

package com.maiyu.hrssc.home.activity.funds;

import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FundsDetailActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.point)
    View mPoint;
    @BindView(R.id.heji_lable_tv)
    TextView mHejiLableTv;
    @BindView(R.id.heji_value_tv)
    TextView mHejiValueTv;
    @BindView(R.id.lable_jfd)
    TextView mLableJfd;
    @BindView(R.id.value_jfd)
    TextView mValueJfd;
    @BindView(R.id.lable_jnyf)
    TextView mLableJnyf;
    @BindView(R.id.value_jnyf)
    TextView mValueJnyf;
    @BindView(R.id.lable_jfjs)
    TextView mLableJfjs;
    @BindView(R.id.value_jfjs)
    TextView mValueJfjs;
    @BindView(R.id.lable_dwbl)
    TextView mLableDwbl;
    @BindView(R.id.value_dwbl)
    TextView mValueDwbl;
    @BindView(R.id.lable_grbl)
    TextView mLableGrbl;
    @BindView(R.id.value_grbl)
    TextView mValueGrbl;
    @BindView(R.id.chakan_lishi_jilu_tv)
    TextView mChakanLishiJiluTv;
    @BindView(R.id.wangzhi_tv)
    TextView mWangzhiTv;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_funds_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("公积金缴交详情", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.chakan_lishi_jilu_tv, R.id.wangzhi_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chakan_lishi_jilu_tv:
                break;
            case R.id.wangzhi_tv:
                break;
        }
    }
}

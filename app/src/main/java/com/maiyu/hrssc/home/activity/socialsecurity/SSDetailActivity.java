package com.maiyu.hrssc.home.activity.socialsecurity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 社保缴交详情
 */

public class SSDetailActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.point)
    View mPoint;
    @BindView(R.id.heji_lable_tv)
    TextView mHejiLableTv;
    @BindView(R.id.heji_value_tv)
    TextView mHejiValueTv;
    @BindView(R.id.danweijiaona_btn)
    Button mDanweijiaonaBtn;
    @BindView(R.id.gerenjiaona_btn)
    Button mGerenjiaonaBtn;
    @BindView(R.id.address_1)
    TextView mAddress1;
    @BindView(R.id.month_1)
    TextView mMonth1;
    @BindView(R.id.jishu_1)
    TextView mJishu1;
    @BindView(R.id.money_1)
    TextView mMoney1;
    @BindView(R.id.address_2)
    TextView mAddress2;
    @BindView(R.id.month_2)
    TextView mMonth2;
    @BindView(R.id.jishu_2)
    TextView mJishu2;
    @BindView(R.id.money_2)
    TextView mMoney2;
    @BindView(R.id.address_3)
    TextView mAddress3;
    @BindView(R.id.month_3)
    TextView mMonth3;
    @BindView(R.id.jishu_3)
    TextView mJishu3;
    @BindView(R.id.money_3)
    TextView mMoney3;
    @BindView(R.id.address_4)
    TextView mAddress4;
    @BindView(R.id.month_4)
    TextView mMonth4;
    @BindView(R.id.jishu_4)
    TextView mJishu4;
    @BindView(R.id.money_4)
    TextView mMoney4;
    @BindView(R.id.address_5)
    TextView mAddress5;
    @BindView(R.id.month_5)
    TextView mMonth5;
    @BindView(R.id.jishu_5)
    TextView mJishu5;
    @BindView(R.id.money_5)
    TextView mMoney5;
    @BindView(R.id.jiaona_heji_value)
    TextView mJiaonaHejiValue;
    @BindView(R.id.chakan_lishi_jilu_tv)
    TextView mChakanLishiJiluTv;
    @BindView(R.id.wangzhi_tv)
    TextView mWangzhiTv;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_ss_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("社保缴交详情", true , false);
        mDanweijiaonaBtn.setSelected(true);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.danweijiaona_btn, R.id.gerenjiaona_btn, R.id.chakan_lishi_jilu_tv, R.id.wangzhi_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.danweijiaona_btn:
                break;
            case R.id.gerenjiaona_btn:
                startActivity(new Intent(this, SSHistoryActivity.class));
                break;
            case R.id.chakan_lishi_jilu_tv:
                startActivity(new Intent(this, SSHistoryActivity.class));
                break;
            case R.id.wangzhi_tv:
                break;
        }
    }
}

package com.maiyu.hrssc.home.activity.socialsecurity;

import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 社保
 */
public class SSHistoryDetailActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.heji_jin_e_value)
    TextView mHejiJinEValue;
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
    @BindView(R.id.p_address_1)
    TextView mPAddress1;
    @BindView(R.id.p_month_1)
    TextView mPMonth1;
    @BindView(R.id.p_jishu_1)
    TextView mPJishu1;
    @BindView(R.id.p_money_1)
    TextView mPMoney1;
    @BindView(R.id.p_address_2)
    TextView mPAddress2;
    @BindView(R.id.p_month_2)
    TextView mPMonth2;
    @BindView(R.id.p_jishu_2)
    TextView mPJishu2;
    @BindView(R.id.p_money_2)
    TextView mPMoney2;
    @BindView(R.id.p_address_3)
    TextView mPAddress3;
    @BindView(R.id.p_month_3)
    TextView mPMonth3;
    @BindView(R.id.p_jishu_3)
    TextView mPJishu3;
    @BindView(R.id.p_money_3)
    TextView mPMoney3;
    @BindView(R.id.p_address_4)
    TextView mPAddress4;
    @BindView(R.id.p_month_4)
    TextView mPMonth4;
    @BindView(R.id.p_jishu_4)
    TextView mPJishu4;
    @BindView(R.id.p_money_4)
    TextView mPMoney4;
    @BindView(R.id.p_address_5)
    TextView mPAddress5;
    @BindView(R.id.p_month_5)
    TextView mPMonth5;
    @BindView(R.id.p_jishu_5)
    TextView mPJishu5;
    @BindView(R.id.p_money_5)
    TextView mPMoney5;
    @BindView(R.id.p_jiaona_heji_value)
    TextView mPJiaonaHejiValue;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_ss_detail2);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
           mHeadView.setTitle("社保缴交详情", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


}

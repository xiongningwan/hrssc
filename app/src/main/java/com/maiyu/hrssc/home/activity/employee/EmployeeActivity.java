package com.maiyu.hrssc.home.activity.employee;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新员工
 */
public class EmployeeActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.city_name_icon)
    ImageView mCityNameIcon;
    @BindView(R.id.city_name)
    TextView mCityName;
    @BindView(R.id.line_divider)
    View mLineDivider;
    @BindView(R.id.shuoming_tv)
    TextView mShuomingTv;
    @BindView(R.id.shuoming_ll)
    RelativeLayout mShuomingLl;
    @BindView(R.id.ditu_iv)
    ImageView mDituIv;
    @BindView(R.id.dizhi_iv)
    ImageView mDizhiIv;
    @BindView(R.id.jiantou_iv)
    ImageView mJiantouIv;
    @BindView(R.id.dizhi_tv)
    TextView mDizhiTv;
    @BindView(R.id.dizhi_rl)
    RelativeLayout mDizhiRl;
    @BindView(R.id.lianxiren_iv)
    ImageView mLianxirenIv;
    @BindView(R.id.lianxiren_jiantou_iv)
    ImageView mLianxirenJiantouIv;
    @BindView(R.id.lianxiren_tv)
    TextView mLianxirenTv;
    @BindView(R.id.lianxiren_rl)
    RelativeLayout mLianxirenRl;
    @BindView(R.id.dianhua_iv)
    ImageView mDianhuaIv;
    @BindView(R.id.dianhua_jiantou_iv)
    ImageView mDianhuaJiantouIv;
    @BindView(R.id.dianhua_tv)
    TextView mDianhuaTv;
    @BindView(R.id.lianxidianhau_rl)
    RelativeLayout mLianxidianhauRl;
    @BindView(R.id.tijian_btn)
    TextView mTijianBtn;
    @BindView(R.id.xwyz_btn)
    TextView mXwyzBtn;
    @BindView(R.id.gkzp_btn)
    TextView mGkzpBtn;
    @BindView(R.id.yyrz_btn)
    TextView mYyrzBtn;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_employee);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("新员工", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.shuoming_ll, R.id.dizhi_rl, R.id.lianxiren_rl, R.id.lianxidianhau_rl, R.id.tijian_btn, R.id.xwyz_btn, R.id.gkzp_btn, R.id.yyrz_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shuoming_ll:
                break;
            case R.id.dizhi_rl:
                break;
            case R.id.lianxiren_rl:
                break;
            case R.id.lianxidianhau_rl:
                break;
            case R.id.tijian_btn:
                startActivity(new Intent(this, TijianActivity.class));
                break;
            case R.id.xwyz_btn:
                break;
            case R.id.gkzp_btn:
                break;
            case R.id.yyrz_btn:
                break;
        }
    }
}

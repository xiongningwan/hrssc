package com.maiyu.hrssc.integration.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.AddressManageActivity;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DuihuanActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.tianjia_btn)
    ImageView mTianjiaBtn;
    @BindView(R.id.no_address_ll)
    LinearLayout mNoAddressLl;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.tel)
    TextView mTel;
    @BindView(R.id.dingwei_iv)
    ImageView mDingweiIv;
    @BindView(R.id.dizhi_lable)
    TextView mDizhiLable;
    @BindView(R.id.address_detail_tv)
    TextView mAddressDetailTv;
    @BindView(R.id.have_address_ll)
    LinearLayout mHaveAddressLl;
    @BindView(R.id.product_iv)
    ImageView mProductIv;
    @BindView(R.id.product_desc_tv)
    TextView mProductDescTv;
    @BindView(R.id.product_jifen_tv)
    TextView mProductJifenTv;
    @BindView(R.id.product_no_tv)
    TextView mProductNoTv;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_duihuan);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("兑换", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.tianjia_btn, R.id.confirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tianjia_btn:
                startActivity(new Intent(this, AddressManageActivity.class));
                break;
            case R.id.confirm_btn:
                startActivity(new Intent(this, DuihuanSuccessActivity.class));
                break;
        }
    }
}

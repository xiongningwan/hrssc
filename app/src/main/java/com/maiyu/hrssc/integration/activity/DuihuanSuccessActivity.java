package com.maiyu.hrssc.integration.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DuihuanSuccessActivity extends BaseActivity {

    @BindView(R.id.left_button_icon)
    ImageView mLeftButtonIcon;
    @BindView(R.id.title_left_button)
    RelativeLayout mTitleLeftButton;
    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.head_view_rl)
    RelativeLayout mHeadViewRl;
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
    @BindView(R.id.product_jifen_rl)
    RelativeLayout mProductJifenRl;
    @BindView(R.id.label_time_tv)
    TextView mLabelTimeTv;
    @BindView(R.id.time_tv)
    TextView mTimeTv;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_duihuan_success);
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

    @OnClick({R.id.title_left_button, R.id.confirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_button:
                finish();
                break;
            case R.id.confirm_btn:
                break;
        }
    }
}

package com.maiyu.hrssc.home.activity.socialsecurity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 社保
 */
public class SocialSecurityActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.chaxun_ll)
    LinearLayout mChaxunLl;
    @BindView(R.id.shebaozhuanru_ll)
    LinearLayout mShebaozhuanruLl;
    @BindView(R.id.shebaobaoxiao_ll)
    LinearLayout mShebaobaoxiaoLl;
    @BindView(R.id.shebaobiangeng_ll)
    LinearLayout mShebaobiangengLl;
    @BindView(R.id.shebaozhuanchu_ll)
    LinearLayout mShebaozhuanchuLl;
    @BindView(R.id.shebaocanbaozm_ll)
    LinearLayout mShebaocanbaozmLl;
    @BindView(R.id.shebaoyibaokabanli_ll)
    LinearLayout mShebaoyibaokabanliLl;
    @BindView(R.id.yiliaobeian_ll)
    LinearLayout mYiliaobeianLl;
    @BindView(R.id.wu_ll)
    LinearLayout mWuLl;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_social_security);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("社保", true, true);
        TextView rightBtnTv = mHeadView.getRightButtonText();
        rightBtnTv.setText("历史");
        rightBtnTv.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightBtnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SocialSecurityActivity.this, SSHistoryActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.chaxun_ll, R.id.shebaozhuanru_ll, R.id.shebaobaoxiao_ll, R.id.shebaobiangeng_ll, R.id.shebaozhuanchu_ll, R.id.shebaocanbaozm_ll, R.id.shebaoyibaokabanli_ll, R.id.yiliaobeian_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chaxun_ll:
                startActivity(new Intent(this, SSDetailActivity.class));
                break;
            case R.id.shebaozhuanru_ll:
                break;
            case R.id.shebaobaoxiao_ll:
                break;
            case R.id.shebaobiangeng_ll:
                break;
            case R.id.shebaozhuanchu_ll:
                break;
            case R.id.shebaocanbaozm_ll:
                break;
            case R.id.shebaoyibaokabanli_ll:
                break;
            case R.id.yiliaobeian_ll:
                break;
        }
    }
}

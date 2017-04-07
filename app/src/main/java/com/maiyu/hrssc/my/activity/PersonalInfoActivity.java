package com.maiyu.hrssc.my.activity;

import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人信息
 */
public class PersonalInfoActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.number)
    TextView mNumber;
    @BindView(R.id.tel)
    TextView mTel;
    @BindView(R.id.level)
    TextView mLevel;
    @BindView(R.id.hukou)
    TextView mHukou;
    @BindView(R.id.jinji_tel)
    TextView mJinjiTel;
    @BindView(R.id.email)
    TextView mEmail;
    @BindView(R.id.sex)
    TextView mSex;
    @BindView(R.id.id_card)
    TextView mIdCard;
    @BindView(R.id.date)
    TextView mDate;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("个人资料", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

}

package com.maiyu.hrssc.home.activity.applying;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.util.HintUitl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EvaluationActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.no_value)
    TextView mNoValue;
    @BindView(R.id.icon_xiangqing)
    ImageView mIconXiangqing;
    @BindView(R.id.title_name)
    TextView mTitleName;
    @BindView(R.id.shenqing_star_1)
    CheckBox mShenqingStar1;
    @BindView(R.id.shenqing_star_2)
    CheckBox mShenqingStar2;
    @BindView(R.id.shenqing_star_3)
    CheckBox mShenqingStar3;
    @BindView(R.id.shenqing_star_4)
    CheckBox mShenqingStar4;
    @BindView(R.id.shenqing_star_5)
    CheckBox mShenqingStar5;
    @BindView(R.id.shenqing_star_ll)
    LinearLayout mShenqingStarLl;
    @BindView(R.id.evaluation_to_shenqing_et)
    EditText mEvaluationToShenqingEt;
    @BindView(R.id.biz_person_tv)
    TextView mBizPersonTv;
    @BindView(R.id.person_star_1)
    CheckBox mPersonStar1;
    @BindView(R.id.person_star_2)
    CheckBox mPersonStar2;
    @BindView(R.id.person_star_3)
    CheckBox mPersonStar3;
    @BindView(R.id.person_star_4)
    CheckBox mPersonStar4;
    @BindView(R.id.person_star_5)
    CheckBox mPersonStar5;
    @BindView(R.id.person_star_ll)
    LinearLayout mPersonStarLl;
    @BindView(R.id.fuwutaidu_btn)
    Button mFuwutaiduBtn;
    @BindView(R.id.yirongtaidu_btn)
    Button mYirongtaiduBtn;
    @BindView(R.id.yewushuliandu_btn)
    Button mYewushulianduBtn;
    @BindView(R.id.lijialiyi_btn)
    Button mLijialiyiBtn;
    @BindView(R.id.evaluation_to_person_et)
    EditText mEvaluationToPersonEt;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_evaluation);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("评价", true, true);
        TextView rightButtonText = mHeadView.getRightButtonText();
        rightButtonText.setText("提交");
        rightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HintUitl.toastShort(EvaluationActivity.this, "提交");
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.fuwutaidu_btn, R.id.yirongtaidu_btn, R.id.yewushuliandu_btn, R.id.lijialiyi_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fuwutaidu_btn:
                if(mFuwutaiduBtn.isSelected()) {
                    mFuwutaiduBtn.setSelected(false);
                } else {
                    mFuwutaiduBtn.setSelected(true);
                }

                break;
            case R.id.yirongtaidu_btn:
                if(mYirongtaiduBtn.isSelected()) {
                    mYirongtaiduBtn.setSelected(false);
                } else {
                    mYirongtaiduBtn.setSelected(true);
                }
                break;
            case R.id.yewushuliandu_btn:
                if(mYewushulianduBtn.isSelected()) {
                    mYewushulianduBtn.setSelected(false);
                } else {
                    mYewushulianduBtn.setSelected(true);
                }
                break;
            case R.id.lijialiyi_btn:
                if(mLijialiyiBtn.isSelected()) {
                    mLijialiyiBtn.setSelected(false);
                } else {
                    mLijialiyiBtn.setSelected(true);
                }
                break;
        }
    }
}

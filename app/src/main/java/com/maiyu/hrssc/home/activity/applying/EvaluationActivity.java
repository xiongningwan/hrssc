package com.maiyu.hrssc.home.activity.applying;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maiyu.hrssc.R.id.fuwutaidu_btn;
import static com.maiyu.hrssc.R.id.shenqing_star_1;

public class EvaluationActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.no_value)
    TextView mNoValue;
    @BindView(R.id.icon_xiangqing)
    ImageView mIconXiangqing;
    @BindView(R.id.title_name)
    TextView mTitleName;
    @BindView(shenqing_star_1)
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
    @BindView(fuwutaidu_btn)
    Button mFuwutaiduBtn;
    @BindView(R.id.yirongtaidu_btn)
    Button mYirongtaiduBtn;
    @BindView(R.id.yewushuliandu_btn)
    Button mYewushulianduBtn;
    @BindView(R.id.lijialiyi_btn)
    Button mLijialiyiBtn;
    @BindView(R.id.tag_5_btn)
    Button mTag5Btn;
    @BindView(R.id.tag_6_btn)
    Button mTag6Btn;
    @BindView(R.id.room_ratingbar_person)
    RatingBar mRatingbarPerson;
    @BindView(R.id.room_ratingbar_shenqing)
    RatingBar mRatingbaShengqing;



    int mStart1 = 0;
    int mStart2 = 0;
    String mTag;


    @BindView(R.id.evaluation_to_person_et)
    EditText mEvaluationToPersonEt;
    private String mToken;
    private String mAid;


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
                evaluationApply();
            }
        });
    }

    private void evaluationApply() {
        mStart1 = (int) mRatingbaShengqing.getRating();
        mStart2 = (int) mRatingbarPerson.getRating();

        //getStart();
        getTag();
        String comment1 = mEvaluationToShenqingEt.getText().toString();
        String comment2 = mEvaluationToPersonEt.getText().toString();

        if (mToken == null) {
            HintUitl.toastShort(this, "token为空");
            return;
        }
        if (mAid == null) {
            HintUitl.toastShort(this, "申请aid为空");
            return;
        }
        if (mStart1 == 0) {
            HintUitl.toastShort(this, "请先评定业务星级");
            return;
        }
        if (mStart2 == 0) {
            HintUitl.toastShort(this, "请先评定业务员星级");
            return;
        }
        if (mTag == null || mTag.equals("")) {
            HintUitl.toastShort(this, "评价标签不能为空");
            return;
        }


        new EvaluateApplyAsyncTask(mToken, mAid, String.valueOf(mStart1), String.valueOf(mStart2), comment1, comment2, mTag).execute();
    }

    @Override
    public void initData() {
        mAid = getIntent().getStringExtra("aid");
        String name = getIntent().getStringExtra("name");
        String orderNo = getIntent().getStringExtra("orderNo");

        mNoValue.setText("NO." + orderNo);
        mTitleName.setText(name);


        mToken = DataCenter.getInstance().getuser().getToken();
        new EvaluateTagsAsyncTask(mToken).execute();
    }


    void getStart() {
        if (mShenqingStar1.isSelected()) {
            mStart1++;
        } else {
            mStart1--;
        }

        if (mShenqingStar2.isSelected()) {
            mStart1++;
        } else {
            mStart1--;
        }

        if (mShenqingStar3.isSelected()) {
            mStart1++;
        } else {
            mStart1--;
        }

        if (mShenqingStar4.isSelected()) {
            mStart1++;
        } else {
            mStart1--;
        }

        if (mShenqingStar5.isSelected()) {
            mStart1++;
        } else {
            mStart1--;
        }

        if (mPersonStar1.isSelected()) {
            mStart2++;
        } else {
            mStart2--;
        }
        if (mPersonStar2.isSelected()) {
            mStart2++;
        } else {
            mStart2--;
        }
        if (mPersonStar3.isSelected()) {
            mStart2++;
        } else {
            mStart2--;
        }
        if (mPersonStar4.isSelected()) {
            mStart2++;
        } else {
            mStart2--;
        }
        if (mPersonStar5.isSelected()) {
            mStart2++;
        } else {
            mStart2--;
        }

        if (mStart1 < 0) {
            mStart1 = 0;
        }
        if (mStart1 > 5) {
            mStart1 = 5;
        }

        if (mStart2 < 0) {
            mStart2 = 0;
        }
        if (mStart2 > 5) {
            mStart2 = 5;
        }
    }

    void getTag() {
        StringBuilder sb = new StringBuilder();

        if (mFuwutaiduBtn.isSelected()) {
            sb.append(mFuwutaiduBtn.getText());
            sb.append("/");
        }

        if (mYirongtaiduBtn.isSelected()) {
            sb.append(mYirongtaiduBtn.getText());
            sb.append("/");
        }

        if (mYewushulianduBtn.isSelected()) {
            sb.append(mYewushulianduBtn.getText());
            sb.append("/");
        }

        if (mLijialiyiBtn.isSelected()) {
            sb.append(mLijialiyiBtn.getText());
            sb.append("/");
        }
        if (mTag5Btn.isSelected()) {
            sb.append(mTag5Btn.getText());
            sb.append("/");
        }
        if (mTag6Btn.isSelected()) {
            sb.append(mTag6Btn.getText());
            sb.append("/");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); //调用 字符串的deleteCharAt() 方法,删除最后一个多余的
        }


        mTag = sb.toString();
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({fuwutaidu_btn, R.id.yirongtaidu_btn, R.id.yewushuliandu_btn, R.id.lijialiyi_btn, R.id.tag_5_btn, R.id.tag_6_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case fuwutaidu_btn:
                if (mFuwutaiduBtn.isSelected()) {
                    mFuwutaiduBtn.setSelected(false);
                } else {
                    mFuwutaiduBtn.setSelected(true);
                }

                break;
            case R.id.yirongtaidu_btn:
                if (mYirongtaiduBtn.isSelected()) {
                    mYirongtaiduBtn.setSelected(false);
                } else {
                    mYirongtaiduBtn.setSelected(true);
                }
                break;
            case R.id.yewushuliandu_btn:
                if (mYewushulianduBtn.isSelected()) {
                    mYewushulianduBtn.setSelected(false);
                } else {
                    mYewushulianduBtn.setSelected(true);
                }
                break;
            case R.id.lijialiyi_btn:
                if (mLijialiyiBtn.isSelected()) {
                    mLijialiyiBtn.setSelected(false);
                } else {
                    mLijialiyiBtn.setSelected(true);
                }
                break;
            case R.id.tag_5_btn:
                if (mTag5Btn.isSelected()) {
                    mTag5Btn.setSelected(false);
                } else {
                    mTag5Btn.setSelected(true);
                }
                break;

            case R.id.tag_6_btn:
                if (mTag6Btn.isSelected()) {
                    mTag6Btn.setSelected(false);
                } else {
                    mTag6Btn.setSelected(true);
                }
                break;
        }
    }

    /**
     * 获取评价的几个标签
     */
    class EvaluateTagsAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private List<String> tagArr;

        public EvaluateTagsAsyncTask(String token) {
            super();

            this.token = token;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                tagArr = engine.getEvaluateTags(EvaluationActivity.this, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(EvaluationActivity.this)) {
                return;
            }
            if (tagArr != null) {
                setData(tagArr);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<String> tagArr) {
        int size = tagArr.size();
        if (size == 6) {
            mFuwutaiduBtn.setVisibility(View.VISIBLE);
            mYirongtaiduBtn.setVisibility(View.VISIBLE);
            mYewushulianduBtn.setVisibility(View.VISIBLE);
            mLijialiyiBtn.setVisibility(View.VISIBLE);
            mTag5Btn.setVisibility(View.VISIBLE);
            mTag6Btn.setVisibility(View.VISIBLE);

            mFuwutaiduBtn.setText(tagArr.get(0));
            mYirongtaiduBtn.setText(tagArr.get(1));
            mYewushulianduBtn.setText(tagArr.get(2));
            mLijialiyiBtn.setText(tagArr.get(3));
            mTag5Btn.setText(tagArr.get(4));
            mTag6Btn.setText(tagArr.get(5));


        } else if (size == 5) {
            mFuwutaiduBtn.setVisibility(View.VISIBLE);
            mYirongtaiduBtn.setVisibility(View.VISIBLE);
            mYewushulianduBtn.setVisibility(View.VISIBLE);
            mLijialiyiBtn.setVisibility(View.VISIBLE);
            mTag5Btn.setVisibility(View.VISIBLE);
            mTag6Btn.setVisibility(View.GONE);

            mFuwutaiduBtn.setText(tagArr.get(0));
            mYirongtaiduBtn.setText(tagArr.get(1));
            mYewushulianduBtn.setText(tagArr.get(2));
            mLijialiyiBtn.setText(tagArr.get(3));
            mTag5Btn.setText(tagArr.get(4));

        } else if (size == 4) {
            mFuwutaiduBtn.setVisibility(View.VISIBLE);
            mYirongtaiduBtn.setVisibility(View.VISIBLE);
            mYewushulianduBtn.setVisibility(View.VISIBLE);
            mLijialiyiBtn.setVisibility(View.VISIBLE);
            mTag5Btn.setVisibility(View.GONE);
            mTag6Btn.setVisibility(View.GONE);

            mFuwutaiduBtn.setText(tagArr.get(0));
            mYirongtaiduBtn.setText(tagArr.get(1));
            mYewushulianduBtn.setText(tagArr.get(2));
            mLijialiyiBtn.setText(tagArr.get(3));

        } else if (size == 3) {
            mFuwutaiduBtn.setVisibility(View.VISIBLE);
            mYirongtaiduBtn.setVisibility(View.VISIBLE);
            mYewushulianduBtn.setVisibility(View.VISIBLE);
            mLijialiyiBtn.setVisibility(View.GONE);
            mTag5Btn.setVisibility(View.GONE);
            mTag6Btn.setVisibility(View.GONE);

            mFuwutaiduBtn.setText(tagArr.get(0));
            mYirongtaiduBtn.setText(tagArr.get(1));
            mYewushulianduBtn.setText(tagArr.get(2));

        } else if (size == 2) {
            mFuwutaiduBtn.setVisibility(View.VISIBLE);
            mYirongtaiduBtn.setVisibility(View.VISIBLE);
            mYewushulianduBtn.setVisibility(View.GONE);
            mLijialiyiBtn.setVisibility(View.GONE);
            mTag5Btn.setVisibility(View.GONE);
            mTag6Btn.setVisibility(View.GONE);

            mFuwutaiduBtn.setText(tagArr.get(0));
            mYirongtaiduBtn.setText(tagArr.get(1));

        } else if (size == 1) {
            mFuwutaiduBtn.setVisibility(View.VISIBLE);
            mYirongtaiduBtn.setVisibility(View.GONE);
            mYewushulianduBtn.setVisibility(View.GONE);
            mLijialiyiBtn.setVisibility(View.GONE);
            mTag5Btn.setVisibility(View.GONE);
            mTag6Btn.setVisibility(View.GONE);

            mFuwutaiduBtn.setText(tagArr.get(0));
        }

    }


    /**
     * 提交评价
     */
    class EvaluateApplyAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String aid;
        private String star1;
        private String star2;
        private String comment1;
        private String comment2;
        private String tag;
        private String str;

        public EvaluateApplyAsyncTask(String token, String aid, String star1, String star2, String comment1, String comment2, String tag) {
            super();

            this.token = token;
            this.aid = aid;
            this.star1 = star1;
            this.star2 = star2;
            this.comment1 = comment1;
            this.comment2 = comment2;
            this.tag = tag;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                str = engine.evaluateApply(EvaluationActivity.this, token, aid, star1, star2, comment1, comment2, tag);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(EvaluationActivity.this)) {
                return;
            }
            if (str != null) {
                HintUitl.toastShort(EvaluationActivity.this, str);
                finish();
            }

            super.onPostExecute(result);
        }
    }


}

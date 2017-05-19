package com.maiyu.hrssc.home.activity.socialsecurity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.socialsecurity.bean.SocialSecurity;
import com.maiyu.hrssc.home.activity.socialsecurity.bean.SocialSecurityFirstData;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maiyu.hrssc.R.id.address_1;
import static com.maiyu.hrssc.R.id.danweijiaona_btn;
import static com.maiyu.hrssc.R.id.gerenjiaona_btn;
import static com.maiyu.hrssc.R.id.jiaona_heji_value;
import static com.maiyu.hrssc.R.id.jishu_1;
import static com.maiyu.hrssc.R.id.money_1;
import static com.maiyu.hrssc.R.id.month_1;
import static com.maiyu.hrssc.R.id.month_2;

/**
 * 社保缴交详情
 */

public class SSDetailActivity extends BaseActivity {
    private static final int COMPANY = 1;
    private static final int PERSONAL = 2;
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.point)
    View mPoint;
    @BindView(R.id.heji_lable_tv)
    TextView mHejiLableTv;
    @BindView(R.id.heji_value_tv)
    TextView mHejiValueTv;
    @BindView(danweijiaona_btn)
    Button mDanweijiaonaBtn;
    @BindView(gerenjiaona_btn)
    Button mGerenjiaonaBtn;
    @BindView(address_1)
    TextView mAddress1;
    @BindView(month_1)
    TextView mMonth1;
    @BindView(jishu_1)
    TextView mJishu1;
    @BindView(money_1)
    TextView mMoney1;
    @BindView(R.id.address_2)
    TextView mAddress2;
    @BindView(month_2)
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
    @BindView(jiaona_heji_value)
    TextView mJiaonaHejiValue;
    @BindView(R.id.chakan_lishi_jilu_tv)
    TextView mChakanLishiJiluTv;
    @BindView(R.id.wangzhi_tv)
    TextView mWangzhiTv;
    private String mToken;
    private SocialSecurityFirstData mSocialSecurityFirstData;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_ss_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("社保缴交详情", true, false);
        mDanweijiaonaBtn.setSelected(true);
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        new GetSocialSecurityFirstAsyncTask(mToken).execute();
    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({danweijiaona_btn, gerenjiaona_btn, R.id.chakan_lishi_jilu_tv, R.id.wangzhi_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case danweijiaona_btn:
                setCompanyPersonalData(mSocialSecurityFirstData, COMPANY);
                break;
            case gerenjiaona_btn:
                setCompanyPersonalData(mSocialSecurityFirstData, PERSONAL);
                //startActivity(new Intent(this, SSHistoryActivity.class));
                break;
            case R.id.chakan_lishi_jilu_tv:
                startActivity(new Intent(this, SSHistoryActivity.class));
                break;
            case R.id.wangzhi_tv:
                break;
        }
    }


    /**
     * 首次加载个人社保
     */
    class GetSocialSecurityFirstAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private SocialSecurityFirstData socialSecurityFirstData;

        public GetSocialSecurityFirstAsyncTask(String token) {
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
                socialSecurityFirstData = engine.getSocialSecurityFirst(SSDetailActivity.this, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(SSDetailActivity.this)) {
                return;
            }
            if (socialSecurityFirstData != null) {
                setData(socialSecurityFirstData);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(SocialSecurityFirstData socialSecurityFirstData) {
        mSocialSecurityFirstData = socialSecurityFirstData;
        mHejiValueTv.setText(socialSecurityFirstData.getTotal() + "元");
        SocialSecurity socialSecurity = socialSecurityFirstData.getSocialSecurity();
        if (socialSecurity != null) {
            setCompanyPersonalData(mSocialSecurityFirstData, COMPANY);
        }


    }


    void setCompanyPersonalData(SocialSecurityFirstData socialSecurityFirstData, int type) {
        // 改变状态
        if (COMPANY == type) {
            mDanweijiaonaBtn.setSelected(true);
            mGerenjiaonaBtn.setSelected(false);
        } else {
            mDanweijiaonaBtn.setSelected(false);
            mGerenjiaonaBtn.setSelected(true);
        }


        if (mSocialSecurityFirstData != null) {
            SocialSecurity socialSecurity = socialSecurityFirstData.getSocialSecurity();


            // 年月
            mMonth1.setText(socialSecurity.getPaytime());
            mMonth2.setText(socialSecurity.getPaytime());
            mMonth3.setText(socialSecurity.getPaytime());
            mMonth4.setText(socialSecurity.getPaytime());
            mMonth5.setText(socialSecurity.getPaytime());


            // 地址
            mAddress1.setText(socialSecurity.getCity());
            mAddress2.setText(socialSecurity.getCity());
            mAddress3.setText(socialSecurity.getCity());
            mAddress4.setText(socialSecurity.getCity());
            mAddress5.setText(socialSecurity.getCity());

            String[] yanglao = socialSecurity.getYanglao().split(",");
            String[] yiliao = socialSecurity.getYiliao().split(",");
            String[] gonshang = socialSecurity.getGongshang().split(",");
            String[] shiye = socialSecurity.getShiye().split(",");
            String[] shengyu = socialSecurity.getShengyu().split(",");


            if (COMPANY == type) {
                // 公司基数
                mJishu1.setText(yanglao[0]);
                mJishu2.setText(yiliao[0]);
                mJishu3.setText(gonshang[0]);
                mJishu4.setText(shiye[0]);
                mJishu5.setText(shengyu[0]);


                // 公司缴纳
                mMoney1.setText(yanglao[1]);
                mMoney1.setText(yiliao[1]);
                mMoney1.setText(gonshang[1]);
                mMoney1.setText(shiye[1]);
                mMoney1.setText(shengyu[1]);

                // 公司缴纳合计
                mJiaonaHejiValue.setText(mSocialSecurityFirstData.getCompantTotal());

            } else {
                // 个人基数
                mJishu1.setText(yanglao[2]);
                mJishu2.setText(yiliao[2]);
                mJishu3.setText(gonshang[2]);
                mJishu4.setText(shiye[2]);
                mJishu5.setText(shengyu[2]);

                // 个人缴纳
                mMoney1.setText(yanglao[3]);
                mMoney1.setText(yiliao[3]);
                mMoney1.setText(gonshang[3]);
                mMoney1.setText(shiye[3]);
                mMoney1.setText(shengyu[3]);

                // 个人缴纳合计
                mJiaonaHejiValue.setText(mSocialSecurityFirstData.getPersonalTotal());
            }

        } else {

            HintUitl.toastShort(this, "没有数据!");
        }


    }

}

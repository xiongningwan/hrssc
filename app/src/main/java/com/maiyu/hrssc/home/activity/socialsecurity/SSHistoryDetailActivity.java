package com.maiyu.hrssc.home.activity.socialsecurity;

import android.view.View;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.maiyu.hrssc.R.id.heji_jin_e_value;

/**
 * 社保
 */
public class SSHistoryDetailActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(heji_jin_e_value)
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
    private String mToken;

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
        String qryDate = getIntent().getStringExtra("qryDate");
        mToken = DataCenter.getInstance().getuser().getToken();

        if (qryDate != null) {
            new GetSocialSecurityByDateAsyncTask(mToken, qryDate).execute();
        }
    }

    @Override
    public void initOnClick(View v) {

    }

    /**
     * 根据日期加载个人社保
     */
    class GetSocialSecurityByDateAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String qryDate;
        private SocialSecurityFirstData socialSecurityFirstData;

        public GetSocialSecurityByDateAsyncTask(String token, String qryDate) {
            super();
            this.qryDate = qryDate;
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
                socialSecurityFirstData = engine.getSocialSecurityByDate(SSHistoryDetailActivity.this, token, qryDate);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(SSHistoryDetailActivity.this)) {
                return;
            }
            if (socialSecurityFirstData != null) {
                setData(socialSecurityFirstData);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(SocialSecurityFirstData socialSecurityFirstData) {
        mHejiJinEValue.setText("合计金额：" + socialSecurityFirstData.getTotal() + "元");

        SocialSecurity socialSecurity = socialSecurityFirstData.getSocialSecurity();
        if (socialSecurity != null) {
            setCompanyPersonalData(socialSecurityFirstData);
        }


    }


    void setCompanyPersonalData(SocialSecurityFirstData socialSecurityFirstData) {


        if (socialSecurityFirstData != null) {
            SocialSecurity socialSecurity = socialSecurityFirstData.getSocialSecurity();


            // 年月
            mMonth1.setText(socialSecurity.getPaytime());
            mMonth2.setText(socialSecurity.getPaytime());
            mMonth3.setText(socialSecurity.getPaytime());
            mMonth4.setText(socialSecurity.getPaytime());
            mMonth5.setText(socialSecurity.getPaytime());
            mPMonth1.setText(socialSecurity.getPaytime());
            mPMonth2.setText(socialSecurity.getPaytime());
            mPMonth3.setText(socialSecurity.getPaytime());
            mPMonth4.setText(socialSecurity.getPaytime());
            mPMonth5.setText(socialSecurity.getPaytime());

            // 地址
            mAddress1.setText(socialSecurity.getCity());
            mAddress2.setText(socialSecurity.getCity());
            mAddress3.setText(socialSecurity.getCity());
            mAddress4.setText(socialSecurity.getCity());
            mAddress5.setText(socialSecurity.getCity());
            mPAddress1.setText(socialSecurity.getCity());
            mPAddress2.setText(socialSecurity.getCity());
            mPAddress3.setText(socialSecurity.getCity());
            mPAddress4.setText(socialSecurity.getCity());
            mPAddress5.setText(socialSecurity.getCity());


            String[] yanglao = socialSecurity.getYanglao().split(",");
            String[] yiliao = socialSecurity.getYiliao().split(",");
            String[] gonshang = socialSecurity.getGongshang().split(",");
            String[] shiye = socialSecurity.getShiye().split(",");
            String[] shengyu = socialSecurity.getShengyu().split(",");


            // 公司基数
            mJishu1.setText(yanglao[0]);
            mJishu2.setText(yiliao[0]);
            mJishu3.setText(gonshang[0]);
            mJishu4.setText(shiye[0]);
            mJishu5.setText(shengyu[0]);


            // 公司缴纳
            mMoney1.setText(yanglao[1]);
            mMoney2.setText(yiliao[1]);
            mMoney3.setText(gonshang[1]);
            mMoney4.setText(shiye[1]);
            mMoney5.setText(shengyu[1]);


            Double conpanyheji =   Double.parseDouble(yanglao[1]) + Double.parseDouble(yiliao[1]) + Double.parseDouble(gonshang[1])
                    + Double.parseDouble(shiye[1]) + Double.parseDouble(shengyu[1]);

            // 公司缴纳合计
            mJiaonaHejiValue.setText(String.valueOf(conpanyheji));

            // 个人基数
            mPJishu1.setText(yanglao[2]);
            mPJishu2.setText(yiliao[2]);
            mPJishu3.setText(gonshang[2]);
            mPJishu4.setText(shiye[2]);
            mPJishu5.setText(shengyu[2]);

            // 个人缴纳
            mPMoney1.setText(yanglao[3]);
            mPMoney2.setText(yiliao[3]);
            mPMoney3.setText(gonshang[3]);
            mPMoney4.setText(shiye[3]);
            mPMoney5.setText(shengyu[3]);

            Double personalheji = Double.parseDouble(yanglao[3]) + Double.parseDouble(yiliao[3]) + Double.parseDouble(gonshang[3])
                    + Double.parseDouble(shiye[3]) + Double.parseDouble(shengyu[3]);
            // 个人缴纳合计
            mPJiaonaHejiValue.setText(String.valueOf(personalheji));
        }
    }

}

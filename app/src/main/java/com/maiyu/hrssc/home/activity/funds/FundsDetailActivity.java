package com.maiyu.hrssc.home.activity.funds;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.funds.bean.PublicFund;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maiyu.hrssc.R.id.value_jnyf;

public class FundsDetailActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.point)
    View mPoint;
    @BindView(R.id.heji_lable_tv)
    TextView mHejiLableTv;
    @BindView(R.id.heji_value_tv)
    TextView mHejiValueTv;
    @BindView(R.id.lable_jfd)
    TextView mLableJfd;
    @BindView(R.id.value_jfd)
    TextView mValueJfd;
    @BindView(R.id.lable_jnyf)
    TextView mLableJnyf;
    @BindView(value_jnyf)
    TextView mValueJnyf;
    @BindView(R.id.lable_jfjs)
    TextView mLableJfjs;
    @BindView(R.id.value_jfjs)
    TextView mValueJfjs;
    @BindView(R.id.lable_dwbl)
    TextView mLableDwbl;
    @BindView(R.id.value_dwbl)
    TextView mValueDwbl;
    @BindView(R.id.lable_grbl)
    TextView mLableGrbl;
    @BindView(R.id.value_grbl)
    TextView mValueGrbl;
    @BindView(R.id.chakan_lishi_jilu_tv)
    TextView mChakanLishiJiluTv;
    @BindView(R.id.wangzhi_tv)
    TextView mWangzhiTv;
    private String mToken;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_funds_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("公积金缴交详情", true, false);
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        new GetPublicFundFirstAsyncTask(mToken).execute();
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.chakan_lishi_jilu_tv, R.id.wangzhi_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chakan_lishi_jilu_tv:
                startActivity(new Intent(FundsDetailActivity.this, FundsHistoryActivity.class));
                break;
            case R.id.wangzhi_tv:
                break;
        }
    }


    /**
     * 首次加载个人社保
     */
    class GetPublicFundFirstAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private PublicFund publicFund;

        public GetPublicFundFirstAsyncTask(String token) {
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
                publicFund = engine.getPublicFundFirst(FundsDetailActivity.this, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(FundsDetailActivity.this)) {
                return;
            }
            if (publicFund != null) {
                setData(publicFund);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(PublicFund publicFund) {

        mHejiValueTv.setText(publicFund.getPayamount() + "元");
        mValueJfd.setText(publicFund.getCity());
        mValueJnyf.setText(publicFund.getPaytime());
        mValueJfjs.setText(publicFund.getBase());
        mValueDwbl.setText(publicFund.getPercent_company());
        mValueGrbl.setText(publicFund.getPercent_personal());
    }
}

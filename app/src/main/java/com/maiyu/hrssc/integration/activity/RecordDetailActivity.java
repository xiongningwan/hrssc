package com.maiyu.hrssc.integration.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IIntegrationEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.integration.bean.RecordDetail;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordDetailActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.product_iv)
    ImageView mProductIv;
    @BindView(R.id.product_desc_tv)
    TextView mProductDescTv;
    @BindView(R.id.product_jifen_tv)
    TextView mProductJifenTv;
    @BindView(R.id.product_no_tv)
    TextView mProductNoTv;
    @BindView(R.id.order_no)
    TextView mOrderNo;
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
    @BindView(R.id.logistics)
    TextView mLogistics;
    @BindView(R.id.logistics_no)
    TextView mLogisticsNo;
    @BindView(R.id.deliver_user)
    TextView mDeliverUser;
    @BindView(R.id.deliver_time)
    TextView mDeliverTime;
    @BindView(R.id.status)
    TextView mStatus;
    @BindView(R.id.have_address_ll)
    LinearLayout mHaveAddressLl;
    private String mToken;
    private LoadingDialog mLoadingDialog;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_record_detail);
        ButterKnife.bind(this);

    }

    @Override
    public void initViews() {
        mHeadView.setTitle("兑换记录", true, false);
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        String orderId = getIntent().getStringExtra("orderId");

        if(orderId != null) {
            new RecordDetialAsyncTask(mToken, orderId).execute();
        }

    }

    @Override
    public void initOnClick(View v) {

    }



    /**
     * 兑换产品记录详情
     */
    class RecordDetialAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String orderId;
        private RecordDetail mRecordDetail;

        public RecordDetialAsyncTask(String token, String orderId) {
            super();

            this.token = token;
            this.orderId = orderId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingDialog.getDialog().show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IIntegrationEngine engine = EngineFactory.get(IIntegrationEngine.class);
            try {
                mRecordDetail = engine.getRecordDetail(RecordDetailActivity.this, token, orderId);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(RecordDetailActivity.this)) {
                return;
            }
            if (mRecordDetail != null) {
                setData(mRecordDetail);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(RecordDetail recordDetail) {

    }
}

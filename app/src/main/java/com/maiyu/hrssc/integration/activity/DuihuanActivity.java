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
    private LoadingDialog mLoadingDialog;
    private String mToken;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_duihuan);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("兑换", true, false);
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();

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
                new RecordDetialAsyncTask(mToken, count, pid, aid).execute();
                break;
        }
    }


    /**
     * 兑换产品详情
     */
    class RecordDetialAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String count;
        private String pid;
        private String aid;
        private RecordDetail mRecordDetail;

        public RecordDetialAsyncTask(String token, String count, String pid, String aid) {
            super();

            this.token = token;
            this.count = count;
            this.pid = pid;
            this.aid = aid;
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
                mRecordDetail = engine.exchangeProduct(DuihuanActivity.this, token, count, pid, aid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(DuihuanActivity.this)) {
                return;
            }
            if (mRecordDetail != null) {
                setData(mRecordDetail);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(RecordDetail recordDetail) {
        startActivity(new Intent(this, DuihuanSuccessActivity.class));
    }
}

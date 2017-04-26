package com.maiyu.hrssc.integration.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.AddressManageActivity;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.AddressManage;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.engine.IIntegrationEngine;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.integration.bean.Image;
import com.maiyu.hrssc.integration.bean.Product;
import com.maiyu.hrssc.integration.bean.ProductDetail;
import com.maiyu.hrssc.integration.bean.RecordDetail;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import java.util.List;

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
    private String mPid;
    private String mAid;
    private String count = "1";

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
        ProductDetail productDetail = (ProductDetail) getIntent().getParcelableExtra(ProductItemActivity.PRODUCT_DETAIIL);
        initExchangeProductData(productDetail);

        mToken = DataCenter.getInstance().getuser().getToken();
        new AddressListAsyncTask(mToken, "1", "1000").execute();
    }

    private void initExchangeProductData(ProductDetail productDetail) {
        if (productDetail == null) {
            return;
        }
        Product product = productDetail.getProduct();
        List<Image> images = productDetail.getImages();

        if (product != null) {
            mPid = product.getId();
            mProductDescTv.setText(product.getName());
            mProductJifenTv.setText(product.getWorth());
            mProductNoTv.setText("×" + product.getLefts());
        }

        if (images != null && images.size() > 0) { // 第一张图片
            ImageLoaderUtil.loadImage(mProductIv, ConstantValue.FILE_SERVER_URI + images.get(0).getUrl(), R.mipmap.user_profile_image_default);
        }

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
                doExchange();
                break;
        }
    }

    private void doExchange() {
        if (mPid != null && mAid != null) {
            new RecordDetialAsyncTask(mToken, count, mPid, mAid).execute();
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
        User user = DataCenter.getInstance().getuser();
        int amount = Integer.parseInt(user.getAmount()) - Integer.parseInt(recordDetail.getPoints());
        user.setAmount(String.valueOf(amount));
        DataCenter.getInstance().setUser(user);
        DataCenter.getInstance().notifyIntegralChange();

        Intent intent = new Intent(this, DuihuanSuccessActivity.class);
        intent.putExtra("RecordDetail", recordDetail);
        startActivity(intent);
        finish();
    }


    /**
     * 获取地址列表
     */
    class AddressListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String page;
        private String rows;
        private String token;
        private List<AddressManage> list;

        public AddressListAsyncTask(String token, String page, String rows) {
            super();

            this.page = page;
            this.rows = rows;
            this.token = token;
        }

        @Override
        protected void onPreExecute() {
            mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                list = engine.getManageAddressList(DuihuanActivity.this, token, page, rows);
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
            if (list != null && list.size() > 0) {
                mNoAddressLl.setVisibility(View.GONE);
                mHaveAddressLl.setVisibility(View.VISIBLE);
                setData(list);
            } else {
                mNoAddressLl.setVisibility(View.VISIBLE);
                mHaveAddressLl.setVisibility(View.GONE);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<AddressManage> list) {
        for (AddressManage am : list) {
            if ("1".equals(am.getIs_default())) {
                // 设置数据
                mName.setText(am.getName());
                mTel.setText(am.getPhone());
                mAddressDetailTv.setText(am.getAddr());
                mAid = am.getId();
            }
        }
    }
}

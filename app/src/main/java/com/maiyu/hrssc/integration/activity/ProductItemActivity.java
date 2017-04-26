package com.maiyu.hrssc.integration.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.Banners;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IIntegrationEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.AdvertisementImageBanner;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.integration.bean.Image;
import com.maiyu.hrssc.integration.bean.Product;
import com.maiyu.hrssc.integration.bean.ProductDetail;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maiyu.hrssc.R.id.duihuan_btn;

public class ProductItemActivity extends BaseActivity {

    @BindView(R.id.ads)
    AdvertisementImageBanner mAds;
    @BindView(R.id.back_btn_1)
    ImageView mBackBtn1;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.jifen)
    TextView mJifen;
    @BindView(R.id.shuliang)
    TextView mShuliang;
    @BindView(R.id.jiazhi)
    TextView mJiazhi;
    @BindView(duihuan_btn)
    TextView mDuihuanBtn;

    @BindView(R.id.shangjia_ll)
    LinearLayout mShangjiaLL;

    @BindView(R.id.duihuan_rl)
    RelativeLayout mDuihuanRl;

    @BindView(R.id.shanchu_rl)
    RelativeLayout mShanchuRL;

    @BindView(R.id.back_btn_2)
    ImageButton mBackBtn2;
    @BindView(R.id.yixiajia_tv)
    TextView mYixiajiatv;

    private LoadingDialog mLoadingDialog;
    private String mToken;
    public static final String PRODUCT_DETAIIL = "ProductDetail";
    private ProductDetail mProductDetail;
    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_product_item);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
       // StatusBarCompat.translucentStatusBar(this, false); // 状态栏半透明
        //mAds.setViewData(list);
    }

    @Override
    public void initData() {
        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();
        String pid = getIntent().getStringExtra("productId");

        if (pid != null) {
            new ProductsDetailAsyncTask(mToken, pid).execute();
        }
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.back_btn_1, R.id.back_btn_2, duihuan_btn})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_btn_1:
                finish();
                break;

            case R.id.back_btn_2:
                finish();
                break;

            case duihuan_btn:
                Intent intent = new Intent(this, DuihuanActivity.class);
                intent.putExtra(PRODUCT_DETAIIL, mProductDetail);
                startActivity(intent);
                break;
        }
    }


    /**
     * 获取产品详情
     */
    class ProductsDetailAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String pid;
        private ProductDetail mProductDetail;

        public ProductsDetailAsyncTask(String token, String pid) {
            super();

            this.token = token;
            this.pid = pid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IIntegrationEngine engine = EngineFactory.get(IIntegrationEngine.class);
            try {
                mProductDetail = engine.getProductDetail(ProductItemActivity.this, token, pid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(ProductItemActivity.this)) {
                return;
            }
            if (mProductDetail != null) {
                setData(mProductDetail);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(ProductDetail productDetail) {
        mProductDetail = productDetail;

        List<Image> images = productDetail.getImages();
        ArrayList bannerList = new ArrayList();
        if (images != null && images.size() > 0) {
            for (Image image : images) {
                Banners banners = new Banners();
                banners.setImage(image.getUrl());
                bannerList.add(banners);
            }
            mAds.setViewData(addImageServerData(bannerList));
            mAds.setAutoScroll(true);
        }


        Product product = productDetail.getProduct();
        if (product != null) {

            mName.setText(product.getName());
            mJifen.setText(product.getWorth());
            mShuliang.setText("数量:" + product.getLefts());
            mJiazhi.setText("价值:" + product.getPrice());
            if ("1".equals(product.getStatus())) {
                mDuihuanRl.setBackgroundResource(R.color.project_color_general_text_secondary);
                mDuihuanBtn.setVisibility(View.GONE);
                mYixiajiatv.setVisibility(View.VISIBLE);
            }

        }


    }

    private ArrayList<Banners> addImageServerData(ArrayList<Banners> arrayList) {
        ArrayList<Banners> list = new ArrayList<Banners>();
        for (Banners ad : arrayList) {
            ad.setImage(ConstantValue.FILE_SERVER_URI + ad.getImage());
            list.add(ad);
        }
        return list;
    }

}

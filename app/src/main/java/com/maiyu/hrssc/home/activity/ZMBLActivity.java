package com.maiyu.hrssc.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.Banners;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.bean.Category2;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.ImageLoaderUtil;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZMBLActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.xzzmbl)
    TextView mXzzmbl;
    @BindView(R.id.jhsyzmbl)
    TextView mJhsyzmbl;
    @BindView(R.id.xsbxzmbl)
    TextView mXsbxzmbl;
    @BindView(R.id.zzzmbl)
    TextView mZzzmbl;
    private String mToken;
    private String mId;
    private String mTitle;
    private List<Category2> mCateGory2List;
    private LoadingDialog mLoadingDialog;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_zmbl);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("name");
        mLoadingDialog = new LoadingDialog(this);
        mHeadView.setTitle(mTitle, true, false);
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        new BannerAsyncTask(mToken).execute();
        if (mId != null) {
            String city = SharedPreferencesUtil.getCityName(this);
            new Category2AsyncTask(mToken, mId, city).execute();
        }
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.head_view, R.id.imageView, R.id.xzzmbl, R.id.jhsyzmbl, R.id.xsbxzmbl, R.id.zzzmbl})
    public void onClick(View view) {
        if (mCateGory2List == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.head_view:
                break;
            case R.id.imageView:

                break;
            case R.id.xzzmbl:
                // 薪资证明办理
                // startActivity(new Intent(this, XZZMBLActivity.class));
                startRequestActivity(0, mCateGory2List.get(0));
                break;
            case R.id.jhsyzmbl:
                // 计划生育证明办理
                // startActivity(new Intent(this, JHSYBLActivity.class));
                startRequestActivity(1, mCateGory2List.get(1));
                break;
            case R.id.xsbxzmbl:
                startActivity(new Intent(this, XSBXZMBLActivity.class));
                break;
            case R.id.zzzmbl:
                startActivity(new Intent(this, ZZZMBLActivity.class));
                break;
        }
    }


    /**
     * 获取业务办理 、 商城的banner
     */
    class BannerAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private Banners mBanners;

        public BannerAsyncTask(String token) {
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
                mBanners = engine.getBanner(ZMBLActivity.this, token, "2");
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(ZMBLActivity.this)) {
                return;
            }
            if (mBanners != null) {
                if (mBanners.getImage() != null) {
                    ImageLoaderUtil.loadImage(mImageView, ConstantValue.FILE_SERVER_URI + mBanners.getImage(), R.mipmap.user_profile_image_default);
                }
            }

            super.onPostExecute(result);
        }
    }


    /**
     * 获取二级业务
     */
    class Category2AsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String cid;
        private String city;
        private List<Category2> list;

        public Category2AsyncTask(String token, String cid, String city) {
            super();
            this.token = token;
            this.cid = cid;
            this.city = city;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingDialog.getDialog().show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                list = engine.getCategory2(ZMBLActivity.this, token, cid, city);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(ZMBLActivity.this)) {
                return;
            }
            if (list != null) {

                setCatgory1(list);

            }

            super.onPostExecute(result);
        }
    }


    void setCatgory1(List<Category2> list) {
        mCateGory2List = list;
        Category2 item0 = list.get(0);
        Category2 item1 = list.get(1);
        Category2 item2 = list.get(2);
        Category2 item3 = list.get(3);
        if (item0 != null) {
            mXzzmbl.setText(item0.getName());
            mXzzmbl.setVisibility("0".equals(item0.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item0 != null) {
            mJhsyzmbl.setText(item1.getName());
            mJhsyzmbl.setVisibility("0".equals(item1.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item0 != null) {
            mXsbxzmbl.setText(item2.getName());
            mXsbxzmbl.setVisibility("0".equals(item2.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item0 != null) {
            mZzzmbl.setText(item3.getName());
            mZzzmbl.setVisibility("0".equals(item3.getStatus()) ? View.VISIBLE : View.GONE);
        }
    }


    void startRequestActivity(int location, Category2 category2) {
        if (category2 != null) {
            Intent intent = null;
            switch (location) {
                case 0:
                    intent = new Intent(this, XZZMBLActivity.class);
                    break;
                case 1:
                 //   intent = new Intent(this, SocialSecurityActivity.class);
                    intent = new Intent(this, XZZMBLActivity.class);
                    break;
                case 2:
                    intent = new Intent(this, ZMBLActivity.class);
                    break;
                case 3:
                    intent = new Intent(this, ZMBLActivity.class);
                    break;
            }

            intent.putExtra("id", category2.getId());
            intent.putExtra("name", category2.getName());
            startActivity(intent);
        }
    }
}

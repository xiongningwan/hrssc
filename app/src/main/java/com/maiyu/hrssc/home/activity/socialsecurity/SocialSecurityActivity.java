package com.maiyu.hrssc.home.activity.socialsecurity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.maiyu.hrssc.home.activity.XZZMBLActivity;
import com.maiyu.hrssc.home.bean.Category2;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.ImageLoaderUtil;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 社保
 */
public class SocialSecurityActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.chaxun_ll)
    LinearLayout mChaxunLl;
    @BindView(R.id.chaxun_tv)
    TextView mChaxunTv;
    @BindView(R.id.shebaozhuanru_ll)
    LinearLayout mShebaozhuanruLl;
    @BindView(R.id.shebaozhuanru_tv)
    TextView mShebaozhuanruTv;

    @BindView(R.id.shebaobaoxiao_ll)
    LinearLayout mShebaobaoxiaoLl;
    @BindView(R.id.shebaobaoxiao_tv)
    TextView mShebaobaoxiaoTv;


    @BindView(R.id.shebaobiangeng_ll)
    LinearLayout mShebaobiangengLl;
    @BindView(R.id.shebaobiangeng_tv)
    TextView mShebaobiangengTv;

    @BindView(R.id.shebaozhuanchu_ll)
    LinearLayout mShebaozhuanchuLl;
    @BindView(R.id.shebaozhuanchu_tv)
    TextView mShebaozhuanchuTv;


    @BindView(R.id.shebaocanbaozm_ll)
    LinearLayout mShebaocanbaozmLl;
    @BindView(R.id.shebaocanbaozm_Tv)
    TextView mShebaocanbaozmTv;


    @BindView(R.id.shebaoyibaokabanli_ll)
    LinearLayout mShebaoyibaokabanliLl;
    @BindView(R.id.shebaoyibaokabanli_tv)
    TextView mShebaoyibaokabanliTv;


    @BindView(R.id.yiliaobeian_ll)
    LinearLayout mYiliaobeianLl;
    @BindView(R.id.yiliaobeian_tv)
    TextView mYiliaobeianTv;

    @BindView(R.id.wu_ll)
    LinearLayout mWuLl;
    private String mToken;
    private String mId;
    private String mTitle;
    private List<Category2> mCateGory2List;
    private LoadingDialog mLoadingDialog;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_social_security);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("name");
        mLoadingDialog = new LoadingDialog(this);
        mHeadView.setTitle(mTitle, true, true);
        TextView rightBtnTv = mHeadView.getRightButtonText();
        rightBtnTv.setText("历史");
        rightBtnTv.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightBtnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SocialSecurityActivity.this, SSHistoryActivity.class));
            }
        });
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


    @OnClick({R.id.chaxun_ll, R.id.shebaozhuanru_ll, R.id.shebaobaoxiao_ll, R.id.shebaobiangeng_ll, R.id.shebaozhuanchu_ll, R.id.shebaocanbaozm_ll, R.id.shebaoyibaokabanli_ll, R.id.yiliaobeian_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chaxun_ll:
                startActivity(new Intent(this, SSDetailActivity.class));
                break;
            case R.id.shebaozhuanru_ll:
                startRequestActivity(mCateGory2List.get(1));
                break;
            case R.id.shebaobaoxiao_ll:
                startRequestActivity(mCateGory2List.get(2));
                break;
            case R.id.shebaobiangeng_ll:
                startRequestActivity(mCateGory2List.get(3));
                break;
            case R.id.shebaozhuanchu_ll:
                startRequestActivity(mCateGory2List.get(4));
                break;
            case R.id.shebaocanbaozm_ll:
                startRequestActivity(mCateGory2List.get(5));
                break;
            case R.id.shebaoyibaokabanli_ll:
                startRequestActivity(mCateGory2List.get(6));
                break;
            case R.id.yiliaobeian_ll:
                startRequestActivity(mCateGory2List.get(7));
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
                mBanners = engine.getBanner(SocialSecurityActivity.this, token, "2");
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(SocialSecurityActivity.this)) {
                return;
            }
            if (mBanners != null) {
                if (mBanners.getImage() != null) {
                    ImageLoaderUtil.loadImage(mImageView, ConstantValue.FILE_SERVER_URI + mBanners.getImage(), R.mipmap.banner_shebao);
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
                list = engine.getCategory2(SocialSecurityActivity.this, token, cid, city);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(SocialSecurityActivity.this)) {
                return;
            }
            if (list != null) {
                setCatgory1(list);

            }

            super.onPostExecute(result);
        }
    }


    void setCatgory1(List<Category2> list) {
        if (list.size() < 8) {
            return;
        }
        mCateGory2List = list;
        Category2 item0 = list.get(0);
        Category2 item1 = list.get(1);
        Category2 item2 = list.get(2);
        Category2 item3 = list.get(3);
        Category2 item4 = list.get(4);
        Category2 item5 = list.get(5);
        Category2 item6 = list.get(6);
        Category2 item7 = list.get(7);

        if (item0 != null) {
            mChaxunTv.setText(item0.getName());
            mChaxunLl.setVisibility("0".equals(item0.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item1 != null) {
            mShebaozhuanruTv.setText(item1.getName());
            mShebaozhuanruLl.setVisibility("0".equals(item1.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item2 != null) {
            mShebaobaoxiaoTv.setText(item2.getName());
            mShebaobaoxiaoLl.setVisibility("0".equals(item2.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item3 != null) {
            mShebaobiangengTv.setText(item3.getName());
            mShebaobiangengLl.setVisibility("0".equals(item3.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item4 != null) {
            mShebaozhuanchuTv.setText(item4.getName());
            mShebaozhuanchuLl.setVisibility("0".equals(item4.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item5 != null) {
            mShebaocanbaozmTv.setText(item5.getName());
            mShebaocanbaozmLl.setVisibility("0".equals(item5.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item6 != null) {
            mShebaoyibaokabanliTv.setText(item6.getName());
            mShebaoyibaokabanliLl.setVisibility("0".equals(item6.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item7 != null) {
            mYiliaobeianTv.setText(item7.getName());
            mYiliaobeianLl.setVisibility("0".equals(item7.getStatus()) ? View.VISIBLE : View.GONE);
        }
    }


    void startRequestActivity(Category2 category2) {
        if (category2 != null) {
            Intent intent = new Intent(this, XZZMBLActivity.class);
            intent.putExtra("id", category2.getId());
            intent.putExtra("name", category2.getName());
            startActivity(intent);
        }
    }

}

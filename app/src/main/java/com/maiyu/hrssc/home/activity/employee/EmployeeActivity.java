package com.maiyu.hrssc.home.activity.employee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.CheckPermissionsActivity;
import com.maiyu.hrssc.base.activity.WebActivity;
import com.maiyu.hrssc.base.bean.CategoryBaseinfo;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.GetWebsiteData;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.activity.XZZMBLActivity;
import com.maiyu.hrssc.home.bean.Category2;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maiyu.hrssc.R.id.dianhua_tv;

/**
 * 新员工
 */
public class EmployeeActivity extends CheckPermissionsActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.city_name_icon)
    ImageView mCityNameIcon;
    @BindView(R.id.city_name)
    TextView mCityName;
    @BindView(R.id.line_divider)
    View mLineDivider;
    @BindView(R.id.shuoming_tv)
    TextView mShuomingTv;
    @BindView(R.id.shuoming_ll)
    RelativeLayout mShuomingLl;
    @BindView(R.id.map_view)
    MapView mMapView;
    @BindView(R.id.ditu_iv)
    ImageView mDituIv;
    @BindView(R.id.dizhi_iv)
    ImageView mDizhiIv;
    @BindView(R.id.jiantou_iv)
    ImageView mJiantouIv;
    @BindView(R.id.dizhi_tv)
    TextView mDizhiTv;
    @BindView(R.id.dizhi_rl)
    RelativeLayout mDizhiRl;
    @BindView(R.id.lianxiren_iv)
    ImageView mLianxirenIv;
    @BindView(R.id.lianxiren_jiantou_iv)
    ImageView mLianxirenJiantouIv;
    @BindView(R.id.lianxiren_tv)
    TextView mLianxirenTv;
    @BindView(R.id.lianxiren_rl)
    RelativeLayout mLianxirenRl;
    @BindView(R.id.dianhua_iv)
    ImageView mDianhuaIv;
    @BindView(R.id.dianhua_jiantou_iv)
    ImageView mDianhuaJiantouIv;
    @BindView(dianhua_tv)
    TextView mDianhuaTv;
    @BindView(R.id.lianxidianhau_rl)
    RelativeLayout mLianxidianhauRl;
    @BindView(R.id.tijian_btn)
    TextView mTijianBtn;
    @BindView(R.id.xwyz_btn)
    TextView mXwyzBtn;
    @BindView(R.id.gkzp_btn)
    TextView mGkzpBtn;
    @BindView(R.id.yyrz_btn)
    TextView mYyrzBtn;
    private String mToken;
    private String mId;
    private String mTitle;
    private List<Category2> mCateGory2List;
    private LoadingDialog mLoadingDialog;
    private String mCity;
    private GetWebsiteData mGetWebsiteData;
    private AMap aMap;
    private MarkerOptions markerOption;
    private GeocodeSearch geocoderSearch;
    private LatLng mLatlng = new LatLng(22.537770, 113.949110);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        initAll();

    }

    void initAll() {
        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("name");
        mLoadingDialog = new LoadingDialog(this);
        mHeadView.setTitle(mTitle, true, false);
        mToken = DataCenter.getInstance().getuser().getToken();
        mCity = SharedPreferencesUtil.getCityName(this);
        if (mId != null && mCity != null && !"".equals(mCity)) {
            new Category2AsyncTask(mToken, mId, mCity).execute();
        }
       init();
    }

    @Override
    public void createActivityImpl() {

    }

    @Override
    public void initViews() {

        //  init();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.shuoming_ll, R.id.dizhi_rl, R.id.lianxiren_rl, R.id.lianxidianhau_rl, R.id.tijian_btn, R.id.xwyz_btn, R.id.gkzp_btn, R.id.yyrz_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shuoming_ll:
                if (mGetWebsiteData != null && mGetWebsiteData.getLink() != null) {
                    openWebActivity();
                }
                break;
            case R.id.dizhi_rl:
                if (mGetWebsiteData != null && mGetWebsiteData.getCategoryBaseinfo() != null) {
                    CategoryBaseinfo baseInfo = mGetWebsiteData.getCategoryBaseinfo();
                    Intent intent = new Intent(this, DizhiActivity.class);
                    intent.putExtra("address", baseInfo.getBaodao_addr());
                    intent.putExtra("latitude", mLatlng.latitude);
                    intent.putExtra("longitude", mLatlng.longitude);
                    startActivity(intent);
                }

                break;
            case R.id.lianxiren_rl:
                break;
            case R.id.lianxidianhau_rl:
                break;
            case R.id.tijian_btn:
                // startActivity(new Intent(this, TijianActivity.class));
                if (mCateGory2List != null && mCateGory2List.get(1) != null) {
                    startRequestActivity(1, mCateGory2List.get(1));
                }
                break;
            case R.id.xwyz_btn: // 学位验证
                if (mCateGory2List != null && mCateGory2List.get(2) != null) {
                    startRequestActivity(2, mCateGory2List.get(2));
                }
                break;
            case R.id.gkzp_btn:
                if (mCateGory2List != null && mCateGory2List.get(3) != null) {
                    startRequestActivity(3, mCateGory2List.get(3));
                }
                break;
            case R.id.yyrz_btn:
                if (mCateGory2List != null && mCateGory2List.get(4) != null) {
                    startRequestActivity(4, mCateGory2List.get(4));
                }
                break;
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
                list = engine.getCategory2(EmployeeActivity.this, token, cid, city);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(EmployeeActivity.this)) {
                return;
            }
            if (list != null) {

                setCatgory1(list);

            }

            super.onPostExecute(result);
        }
    }


    void setCatgory1(List<Category2> list) {
        if (list.size() < 5) {
            return;
        }
        mCateGory2List = list;
        Category2 item0 = list.get(0);
        Category2 item1 = list.get(1);
        Category2 item2 = list.get(2);
        Category2 item3 = list.get(3);
        Category2 item4 = list.get(4);
        if (item0 != null && mCity != null) {
            //  mDizhiTv.setText(item0.getName());
            //  mXzzmbl.setVisibility("0".equals(item0.getStatus()) ? View.VISIBLE : View.GONE);
            new GetWebsiteAsyncTask(mToken, item0.getId(), mCity).execute();
        }

        if (item1 != null) {
            mTijianBtn.setText(item1.getName());
            mTijianBtn.setVisibility("0".equals(item1.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item2 != null) {
            mXwyzBtn.setText(item2.getName());
            mXwyzBtn.setVisibility("0".equals(item2.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item3 != null) {
            mGkzpBtn.setText(item3.getName());
            mGkzpBtn.setVisibility("0".equals(item3.getStatus()) ? View.VISIBLE : View.GONE);
        }
        if (item4 != null) {
            mYyrzBtn.setText(item4.getName());
            mYyrzBtn.setVisibility("0".equals(item3.getStatus()) ? View.VISIBLE : View.GONE);
        }
    }


    void startRequestActivity(int location, Category2 category2) {
        if (category2 != null) {
            Intent intent = null;
            switch (location) {
                case 1:
                    intent = new Intent(this, TijianActivity.class);
                    intent.putExtra("GetWebsiteData", mGetWebsiteData);
                    break;
                case 2:
                    intent = new Intent(this, XZZMBLActivity.class);
                    break;
                case 3:
                    intent = new Intent(this, XZZMBLActivity.class);
                    break;
                case 4:
                    intent = new Intent(this, XZZMBLActivity.class);
                    break;
            }

            intent.putExtra("id", category2.getId());
            intent.putExtra("name", category2.getName());
            startActivity(intent);
        }
    }


    /**
     * 获取某个二级业务的基础信息（官方网站，体检、报到地址，联系人，联系方式）
     */
    class GetWebsiteAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String cid2;
        private String city;
        private GetWebsiteData getWebsiteData;

        public GetWebsiteAsyncTask(String token, String cid2, String city) {
            super();
            this.token = token;
            this.cid2 = cid2;
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
                getWebsiteData = engine.getWebsite(EmployeeActivity.this, token, cid2, city);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(EmployeeActivity.this)) {
                return;
            }
            if (getWebsiteData != null) {
                mGetWebsiteData = getWebsiteData;
                setBaseInfo(getWebsiteData);

            }

            super.onPostExecute(result);
        }
    }

    private void setBaseInfo(GetWebsiteData getWebsiteData) {
        if (getWebsiteData.getCategoryBaseinfo() == null) {
            return;
        }
        CategoryBaseinfo baseInfo = getWebsiteData.getCategoryBaseinfo();

        mCityName.setText(baseInfo.getCity());
        mDizhiTv.setText(baseInfo.getBaodao_addr());
        mLianxirenTv.setText(baseInfo.getBaodao_contact());
        mDianhuaTv.setText(baseInfo.getBaodao_phone());


        GeocodeQuery query = new GeocodeQuery(baseInfo.getBaodao_addr(), baseInfo.getCity());
        geocoderSearch.getFromLocationNameAsyn(query);
    }

    void openWebActivity() {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", mGetWebsiteData.getLink());
        intent.putExtra("titleName", "帮助说明");
        intent.putExtra("type", ConstantValue.TYPE_IMPORTANT);
        startActivity(intent);
    }


    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            UiSettings uiSettings = aMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setLogoBottomMargin(-50);//隐藏logo

            geocoderSearch = new GeocodeSearch(this);
            geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
                @Override
                public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

                }

                @Override
                public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                    if (i == 1000) {
                        if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                                && geocodeResult.getGeocodeAddressList().size() > 0) {

                            GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);
                            double dimensionality = address.getLatLonPoint().getLatitude();
                            double longitude = address.getLatLonPoint().getLongitude();
                            LatLng latlng = new LatLng(dimensionality, longitude);

                            /*aMap.animateCamera(CameraUpdateFactory
                                    .newLatLngZoom(latlng, 15));
*/
                            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 16));
                            addMarkersToMap(latlng);// 往地图上添加marker
                            mLatlng = latlng;
                        }
                    }
                }
            });
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap(LatLng latlng) {
        Bitmap bMap = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.icon_dingwei_maker);
        BitmapDescriptor des = BitmapDescriptorFactory.fromBitmap(bMap);

        markerOption = new MarkerOptions().icon(des)
                .position(latlng)
                .draggable(true);
        aMap.addMarker(markerOption);
    }


}

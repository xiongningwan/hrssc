package com.maiyu.hrssc.home.activity.employee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.maiyu.hrssc.base.bean.CheckResult;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.GetWebsiteData;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.activity.applying.adapter.ImageGridAdapter;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TijianActivity extends CheckPermissionsActivity {

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
    @BindView(R.id.ditu_iv)
    ImageView mDituIv;
    @BindView(R.id.jiantou_iv)
    ImageView mJiantouIv;
    @BindView(R.id.dizhi_tv)
    TextView mDizhiTv;
    @BindView(R.id.dizhi_rl)
    RelativeLayout mDizhiRl;
    @BindView(R.id.no_result_tv)
    TextView mNoResultTv;
    @BindView(R.id.grid_view)
    GridView mGridView;
    @BindView(R.id.map_view)
    MapView mMapView;
    @BindView(R.id.have_result_ll)
    LinearLayout mHaveResultLL;
    @BindView(R.id.content)
    TextView mContent;
    @BindView(R.id.time)
    TextView mTime;
    private ImageGridAdapter mGridAdapter;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    private GetWebsiteData mGetWebsiteData;
    private String mId;
    private String mTitle;
    private AMap aMap;
    private MarkerOptions markerOption;
    private GeocodeSearch geocoderSearch;
    private LatLng mLatlng = new LatLng(22.537770, 113.949110);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        initAll();

    }

    void initAll() {
        // 设置gridview
        mGridAdapter = new ImageGridAdapter(this);
        mGridView.setAdapter(mGridAdapter);
        init(); // 初始化地图

        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("name");
        mGetWebsiteData = getIntent().getParcelableExtra("GetWebSiteData");

        mHeadView.setTitle(mTitle, true, false);
        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();

        setBaseInfo(mGetWebsiteData);
        new CheckResultAsyncTask(mToken).execute();
    }

    @Override
    public void createActivityImpl() {
    }

    @Override
    public void initViews() {


    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.shuoming_ll, R.id.dizhi_rl})
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
        }
    }


    /**
     * 体检结果
     */
    class CheckResultAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private CheckResult chechResult;

        public CheckResultAsyncTask(String token) {
            super();
            this.token = token;
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
                chechResult = engine.getHealthCheck(TijianActivity.this, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(TijianActivity.this)) {
                return;
            }
            if (chechResult != null) {
                mHaveResultLL.setVisibility(View.VISIBLE);
                mNoResultTv.setVisibility(View.GONE);
                setCheckResult(chechResult);
            } else {
                mHaveResultLL.setVisibility(View.GONE);
                mNoResultTv.setVisibility(View.VISIBLE);
            }

            super.onPostExecute(result);
        }
    }

    void setCheckResult(CheckResult checkResult) {
        mContent.setText(checkResult.getResult());

        try{
            mTime.setText(sdf1.format(sdf.parse(checkResult.getCreate_time())));
        }catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = JSONObject.parseArray(checkResult.getImages());
        List images = jsonArray.toJavaList(String.class);
        mGridAdapter.updatePickImageView(images, mGridView);
    }




    private void setBaseInfo(GetWebsiteData getWebsiteData) {
        if (getWebsiteData.getCategoryBaseinfo() == null) {
            return;
        }
        CategoryBaseinfo baseInfo = getWebsiteData.getCategoryBaseinfo();

        mCityName.setText(baseInfo.getCity());
        mDizhiTv.setText(baseInfo.getBaodao_addr());

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
                                    .newLatLngZoom(latlng, 15));*/

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

package com.maiyu.hrssc.home.frament;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;
import com.amap.api.location.AMapLocationListener;
import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.CitysActivity;
import com.maiyu.hrssc.base.activity.MessagesActivity;
import com.maiyu.hrssc.base.adapter.HomeFragmentNewsAdapter;
import com.maiyu.hrssc.base.bean.Banners;
import com.maiyu.hrssc.base.bean.City;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.HomeData;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.AdvertisementImageBanner;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.activity.JZZBLActivity;
import com.maiyu.hrssc.home.activity.ZMBLActivity;
import com.maiyu.hrssc.home.activity.applying.ApplyingActivity;
import com.maiyu.hrssc.home.activity.employee.EmployeeActivity;
import com.maiyu.hrssc.home.activity.files.FilesBorrowActivity;
import com.maiyu.hrssc.home.activity.funds.FundsActivity;
import com.maiyu.hrssc.home.activity.information.InformationActivity;
import com.maiyu.hrssc.home.activity.residence.ResidenceActivity;
import com.maiyu.hrssc.home.activity.socialsecurity.SocialSecurityActivity;
import com.maiyu.hrssc.home.activity.todo.TodoActivity;
import com.maiyu.hrssc.home.bean.Category1;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.maiyu.hrssc.R.id.swipe_reshresh_layout;

public class HomeFragment extends Fragment {
    static final int REQUEST_ACCESS_FINE_LOCATION_PERMISSION = 101;
    static final int REQUEST_SELECT_CITY = 102;
    public static final String CITY_LIST = "city_list";
    private static final int MSG_REQUEST_CODE = 103;
    private static final int TODO_REQUEST_CODE = 104;
    @BindView(swipe_reshresh_layout)
    SwipeRefreshLayout mSwipeReshreshLayout;
    @BindView(R.id.address_btn_text)
    TextView mAddressBtnText;
    @BindView(R.id.address_btn)
    RelativeLayout mAddressBtn;
    @BindView(R.id.home_title_name)
    TextView mHomeTitleName;
    @BindView(R.id.msg_iv)
    ImageView mMsgIv;
    @BindView(R.id.msg_btn)
    RelativeLayout mMsgBtn;
    @BindView(R.id.activity_main_ads)
    AdvertisementImageBanner mActivityMainAds;
    @BindView(R.id.iv_hetong)
    ImageView mIvHetong;
    @BindView(R.id.hetong_next_btn)
    LinearLayout mHetongNextBtn;
    @BindView(R.id.hetong_rl)
    RelativeLayout mHetongRl;
    @BindView(R.id.iv_shenqing)
    ImageView mIvShenqing;
    @BindView(R.id.xianghou)
    ImageView mXianghou;
    @BindView(R.id.next_btn)
    LinearLayout mNextBtn;
    @BindView(R.id.shenqing_rl)
    RelativeLayout mShenqingRl;
    @BindView(R.id.btn_zmbl)
    TextView mBtnZmbl;
    @BindView(R.id.btn_sb)
    TextView mBtnSb;
    @BindView(R.id.btn_gjj)
    TextView mBtnGjj;
    @BindView(R.id.btn_dajy)
    TextView mBtnDajy;
    @BindView(R.id.btn_hkbl2)
    TextView mBtnHkbl2;
    @BindView(R.id.btn_jzzbl)
    TextView mBtnJzzbl;
    @BindView(R.id.btn_xyg)
    TextView mBtnXyg;
    @BindView(R.id.btn_more)
    TextView mBtnMore;
    @BindView(R.id.more_next_btn)
    LinearLayout mMoreNextBtn;

    @BindView(R.id.news_recycler_view)
    RecyclerView mNewRecyclerView;
    @BindView(R.id.myTransaction_tv)
    TextView myTransactionTv;
    @BindView(R.id.myApply_tv)
    TextView myApplyTv;
    @BindView(R.id.msg_point)
    View mMsgPoint;
    @BindView(R.id.scrollView)
    ScrollView scrollView;


    Unbinder unbinder;
    private AMapLocationClient locationClient = null;
    private RelativeLayout mCityRL;
    private LoadingDialog mLoadingDialog;
    private List<City> mCitys;
    private String mToken;
    private HomeFragmentNewsAdapter mNewsAdapter;
    private List<Category1> mCateGory1List;
    private User mUser;
    // TODO: Rename and change types of parameters


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //改变加载显示的颜色
        mSwipeReshreshLayout.setColorSchemeResources(R.color.project_color_general_hyperlink);
        mSwipeReshreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 请求首页数据
                new getHomeData(mAddressBtnText.getText().toString(), mToken).execute();
                //停止刷新
                mSwipeReshreshLayout.setRefreshing(false);
            }
        });
        mLoadingDialog = new LoadingDialog(getActivity());

        mNewsAdapter = new HomeFragmentNewsAdapter(getActivity());
        mNewRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mNewRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewRecyclerView.setAdapter(mNewsAdapter);


        mToken = DataCenter.getInstance().getuser().getToken();
        mUser = DataCenter.getInstance().getuser();
        if (mToken != null) {
            new GetCitysAsyncTask("1", "10000", mToken).execute();
            new Category1AsyncTask(mToken).execute();
        }
        setScroll();
    }

    void setScroll() {
        if (scrollView != null) {
            scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (mSwipeReshreshLayout != null) {
                        mSwipeReshreshLayout.setEnabled(scrollView.getScrollY() == 0);
                    }
                }
            });
        }
    }


    /***
     * Stop location service
     */
    @Override
    public void onStop() {
        super.onStop();
        stopLocation();
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }


    private void checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,
                    getString(R.string.permission_rationale_location),
                    REQUEST_ACCESS_FINE_LOCATION_PERMISSION);
        } else {
            startLocation();
        }
    }


    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
                            HomeFragment.this.requestPermissions(new String[]{permission},
                                    requestCode);

                        }
                    })
                    .setNegativeButton(R.string.permission_dialog_cancel, null)
                    .create().show();
        } else {
            HomeFragment.this.requestPermissions(new String[]{permission}, requestCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ACCESS_FINE_LOCATION_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocation();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                //String result = Utils.getLocationStr(loc);
                //tvResult.setText(result);
                String cityName = loc.getCity();
                // 设置城市
                comPareCity(cityName);
                stopLocation();
                Log.d("result", "loc:" + loc.toString());
            } else {
                stopLocation();
                String cityName = SharedPreferencesUtil.getCityName(getActivity());
                mAddressBtnText.setText(cityName);
                Log.d("result", "定位失败，loc is null");
                // tvResult.setText("定位失败，loc is null");
                // 设置城市
                comPareCity("");

            }
        }
    };


    void comPareCity(String cityName) {
        // 设置为默认城市
        for (City c : mCitys) {
            if ("1".equals(c.getDefaultOrNot())) {
                mAddressBtnText.setText(c.getCity());
            }
        }


        for (City c : mCitys) {
            if (c.getCity().equals(cityName)) {
                mAddressBtnText.setText(cityName);
            }
        }

        // 保存城市
        SharedPreferencesUtil.saveCityName(getActivity(), mAddressBtnText.getText().toString());

        // 请求首页数据
        new getHomeData(mAddressBtnText.getText().toString(), mToken).execute();
    }


    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        if (locationClient != null) {
            locationClient.stopLocation();
        }
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean isFresher() {

        if (mUser != null && "0".equals(mUser.getStatus())) {
            return true;
        }

        return false;
    }

    void openFunctionActivity(int location) {
        if (mCateGory1List != null && !isFresher()) {
            startRequestActivity(location, mCateGory1List.get(location));
        } else {
            HintUitl.toastShort(getActivity(), "新员工不能使用该功能");
        }
    }

    @OnClick({R.id.address_btn, R.id.msg_btn, R.id.hetong_rl, R.id.shenqing_rl, R.id.btn_zmbl, R.id.btn_sb, R.id.btn_gjj, R.id.btn_dajy, R.id.btn_hkbl2, R.id.btn_jzzbl, R.id.btn_xyg, R.id.btn_more, R.id.more_next_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.address_btn:

                Intent intent = new Intent(getActivity(), CitysActivity.class);
                intent.putParcelableArrayListExtra(CITY_LIST, (ArrayList) mCitys);
                startActivityForResult(intent, REQUEST_SELECT_CITY);


                break;
            case R.id.msg_btn:
                // 消息系统
                // startActivity(new Intent(getActivity(), InformationActivity.class));
                startActivityForResult(new Intent(getActivity(), MessagesActivity.class), MSG_REQUEST_CODE);
                /*if (mMsgPoint.getVisibility() == View.VISIBLE) {
                    mMsgPoint.setVisibility(View.GONE);
                }*/
                break;
            case R.id.hetong_rl:
                startActivityForResult(new Intent(getActivity(), TodoActivity.class), TODO_REQUEST_CODE);
                break;
            case R.id.shenqing_rl:
                startActivity(new Intent(getActivity(), ApplyingActivity.class));
                break;
            case R.id.btn_zmbl:
                openFunctionActivity(0);
                break;
            case R.id.btn_sb:
                // startActivity(new Intent(getActivity(), SocialSecurityActivity.class));
                openFunctionActivity(1);
                break;
            case R.id.btn_gjj:
                openFunctionActivity(2);
                // startActivity(new Intent(getActivity(), FundsActivity.class));
                break;
            case R.id.btn_dajy:
                // 档案借阅
                //startActivity(new Intent(getActivity(), FilesBorrowActivity.class));
                openFunctionActivity(3);
                break;
            case R.id.btn_hkbl2:
                // 户口办理
                // startActivity(new Intent(getActivity(), ResidenceActivity.class));
                openFunctionActivity(4);
                break;
            case R.id.btn_jzzbl:
                // 居住证办理
                openFunctionActivity(5);
                break;
            case R.id.btn_xyg:
                // 新员工
                if (mCateGory1List != null) {
                    startRequestActivity(6, mCateGory1List.get(6));
                }
                // startActivity(new Intent(getActivity(), EmployeeActivity.class));
                break;
            case R.id.btn_more:
                openFunctionActivity(7);
                break;
            case R.id.more_next_btn:
                startActivity(new Intent(getActivity(), InformationActivity.class));
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_SELECT_CITY:
                    if (data != null) {
                        String cityName = data.getStringExtra("cityName");
                        mAddressBtnText.setText(cityName);

                        // 请求首页数据
                        new getHomeData(mAddressBtnText.getText().toString(), mToken).execute();
                    }
                    break;
                case MSG_REQUEST_CODE:
                    Log.i("home_resultCode_code", "MSG_REQUEST_CODE");
                    // 刷新home
                    new getHomeData(mAddressBtnText.getText().toString(), mToken).execute();
                    break;
                case TODO_REQUEST_CODE:
                    Log.i("home_resultCode_code", "TODO_REQUEST_CODE");
                    // 刷新home
                    new getHomeData(mAddressBtnText.getText().toString(), mToken).execute();
                    break;
            }
        }

    }


    /**
     * 获取城市信息
     */
    class GetCitysAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String page;
        private String rows;
        private String token;
        private List<City> citys;

        public GetCitysAsyncTask(String page, String rows, String token) {
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
                citys = engine.getCitys(getActivity(), page, rows, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(getActivity())) {
                return;
            }
            if (citys != null) {
                // 保存帐号
                mCitys = citys;
                //初始化定位
                initLocation();
                // 检查定位权限
                checkLocationPermission();
            }

            super.onPostExecute(result);
        }
    }


    /**
     * 获取首页信息
     */
    class getHomeData extends BaseAsyncTask<Void, Void, Void> {
        private String city;
        private String token;
        private HomeData homeData;

        public getHomeData(String city, String token) {
            super();

            this.city = city;
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
                homeData = engine.getHomeData(getActivity(), city, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(getActivity())) {
                return;
            }
            if (homeData != null) {

                setHomeData(homeData);

            }

            super.onPostExecute(result);
        }
    }

    private void setHomeData(HomeData homeData) {
        myTransactionTv.setText(homeData.getMyTransaction());
        myApplyTv.setText(homeData.getMyApply());

        if (homeData.getMyMessage() != null
                && !homeData.getMyMessage().equals("0")
                && !homeData.getMyMessage().equals("")) {
            mMsgPoint.setVisibility(View.VISIBLE);
            SharedPreferencesUtil.saveIsPointViewVisibility(getActivity(), true);
            DataCenter.getInstance().notifyRedPointStatusChange();
        } else {
            mMsgPoint.setVisibility(View.GONE);
            SharedPreferencesUtil.saveIsPointViewVisibility(getActivity(), false);
            DataCenter.getInstance().notifyRedPointStatusChange();
        }

        ArrayList<Banners> banners = (ArrayList<Banners>) homeData.getBanners();
        mActivityMainAds.setViewData(addImageServerData(banners));
        mActivityMainAds.setAutoScroll(true);

        if (homeData.getNews() != null) {
            mNewsAdapter.setData(homeData.getNews());
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


    /**
     * 获取全部一级业务
     */
    class Category1AsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private List<Category1> list;

        public Category1AsyncTask(String token) {
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
                list = engine.getCategory1(getActivity(), token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(getActivity())) {
                return;
            }
            if (list != null) {

                setCatgory1(list);

            }

            super.onPostExecute(result);
        }
    }


    void setCatgory1(List<Category1> list) {
        mCateGory1List = list;

        mBtnZmbl.setText(list.get(0).getName());
        mBtnSb.setText(list.get(1).getName());
        mBtnGjj.setText(list.get(2).getName());
        mBtnDajy.setText(list.get(3).getName());
        mBtnHkbl2.setText(list.get(4).getName());
        mBtnJzzbl.setText(list.get(5).getName());
        mBtnXyg.setText(list.get(6).getName());
        mBtnMore.setText(list.get(7).getName());
    }

    void startRequestActivity(int location, Category1 category1) {
        if (category1 != null) {
            Intent intent = null;
            switch (location) {
                case 0:
                    intent = new Intent(getActivity(), ZMBLActivity.class);
                    break;
                case 1:
                    intent = new Intent(getActivity(), SocialSecurityActivity.class);
                    break;
                case 2:
                    intent = new Intent(getActivity(), FundsActivity.class);
                    break;
                case 3:
                    intent = new Intent(getActivity(), FilesBorrowActivity.class);
                    break;
                case 4:
                    intent = new Intent(getActivity(), ResidenceActivity.class);
                    break;
                case 5:
                    intent = new Intent(getActivity(), JZZBLActivity.class);
                    //intent = new Intent(getActivity(), XZZMBLActivity.class);
                    break;
                case 6:
                    intent = new Intent(getActivity(), EmployeeActivity.class);
                    break;
                case 7:
                    intent = new Intent(getActivity(), ZMBLActivity.class);
                    break;
            }

            intent.putExtra("id", category1.getId());
            intent.putExtra("name", category1.getName());
            startActivity(intent);
        }
    }

}

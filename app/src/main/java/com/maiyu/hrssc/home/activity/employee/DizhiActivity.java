package com.maiyu.hrssc.home.activity.employee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.CheckPermissionsActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.util.HintUitl;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DizhiActivity extends CheckPermissionsActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.map_view)
    MapView mMapView;
    @BindView(R.id.address_tv)
    TextView mAddressTv;
    @BindView(R.id.daohang_btn)
    Button mDaohangBtn;
    @BindView(R.id.daohang_rl)
    RelativeLayout mDaohangRl;
    private AMap aMap;
    private MarkerOptions markerOption;
    private LatLng latlng;
    private String mAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhi);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        init();

        mDaohangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDaohang();
            }
        });
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

    /**
     * 初始化AMap对象
     */
    private void init() {
        mHeadView.setTitle("地址", true, false);
        mAddress = getIntent().getStringExtra("address");
        Double latitude = getIntent().getDoubleExtra("latitude", 0);
        Double longitude = getIntent().getDoubleExtra("longitude", 0);
        latlng = new LatLng(latitude, longitude);
        mAddressTv.setText(mAddress);


        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 16));
            addMarkersToMap(latlng);// 往地图上添加marker

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


    private void startDaohang() {

        if (latlng.latitude != 0.0 && latlng.longitude != 0.0) {

            if (isInstallByread("com.autonavi.minimap")) {
              /*  //移动APP调起Android高德地图方式
                Intent intent = new Intent("android.intent.action.VIEW",
                        android.net.Uri.parse("androidamap://navi?sourceApplication=hrssc&lat=" + latlng.latitude + "&lon=" + latlng.longitude + "&dev=0&style=2"));
                intent.setPackage("com.autonavi.minimap");
                startActivity(intent); // 启动调用*/
                openGaoDeNavi();
            } else if(isInstallByread("com.baidu.BaiduMap")) {
                //startBaiduMap();
                openBaiduNavi();
            } else {
                HintUitl.toastShort(getBaseContext(), "没有安装高德/百度地图客户端");
            }
        } else {
            HintUitl.toastShort(getBaseContext(), "终点坐标不明确，请确认");
        }
    }


    //判断是否安装目标应用
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName)
                .exists();
    }



    //移动APP调起Android百度地图方式
    private void startBaiduMap() {
        if (isInstallByread("com.baidu.BaiduMap")) {

            try {
                Intent intent = new Intent("intent://map/navi?location=" + latlng.latitude + "," + latlng.longitude +
                        "&type=TIME&src=thirdapp.navi.hndist.sydt#Intent;scheme=hrsccapp;" +
                        "package=com.baidu.BaiduMap;end");
                startActivity(intent); // 启动调用
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 启动高德App进行导航
     * sourceApplication 必填 第三方调用应用名称。如 amap
     * poiname           非必填 POI 名称
     * dev               必填 是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
     * style             必填 导航方式(0 速度快; 1 费用少; 2 路程短; 3 不走高速；4 躲避拥堵；5 不走高速且避免收费；6 不走高速且躲避拥堵；7 躲避收费和拥堵；8 不走高速躲避收费和拥堵))
     */
    private void openGaoDeNavi() {
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
                .append("hrssc").append("&lat=").append(latlng.latitude)
                .append("&lon=").append(latlng.longitude)
                .append("&dev=").append(0)
                .append("&style=").append(0);
        ;
//        if (!TextUtils.isEmpty(poiname)) {
//            stringBuffer.append("&poiname=").append(poiname);
//        }
        Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(stringBuffer.toString()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.autonavi.minimap");
        startActivity(intent);
    }


    /**
     * 打开百度地图导航客户端
     * intent = Intent.getIntent("baidumap://map/navi?location=34.264642646862,108.95108518068&type=BLK&src=thirdapp.navi.you
     * location 坐标点 location与query二者必须有一个，当有location时，忽略query
     * query    搜索key   同上
     * type 路线规划类型  BLK:躲避拥堵(自驾);TIME:最短时间(自驾);DIS:最短路程(自驾);FEE:少走高速(自驾);默认DIS
     */
    private void openBaiduNavi() {
        StringBuffer stringBuffer = new StringBuffer("baidumap://map/navi?location=")
                .append(latlng.latitude).append(",").append(latlng.longitude).append("&type=TIME");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.baidu.BaiduMap");
        startActivity(intent);
    }
}

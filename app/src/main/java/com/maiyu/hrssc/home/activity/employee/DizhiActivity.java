package com.maiyu.hrssc.home.activity.employee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.net.URISyntaxException;

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
    private LatLng latlng = new LatLng(22.537770, 113.949110);

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

    /**
     * 初始化AMap对象
     */
    private void init() {
        mHeadView.setTitle("地址", true, false);

        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 16));
            addMarkersToMap();// 往地图上添加marker
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
    private void addMarkersToMap() {
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
            //移动APP调起Android高德地图方式
            Intent intent = new Intent("android.intent.action.VIEW",
                    android.net.Uri.parse("androidamap://navi?sourceApplication=hrssc&lat=" + latlng.latitude + "&lon=" + latlng.longitude + "&dev=0&style=2"));
            intent.setPackage("com.autonavi.minimap");
            if (isInstallByread("com.autonavi.minimap")) {
                startActivity(intent); // 启动调用
            } else {
                startBaiduMap();
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
        Intent intent = null;
        try {
            intent = Intent.getIntent
                    ("intent://map/navi?location=" + latlng.latitude + "," + latlng.longitude +
                            "&type=TIME&src=thirdapp.navi.hndist.sydt#Intent;scheme=hrsccapp;" +
                            "package=com.baidu.BaiduMap;end");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (isInstallByread("com.baidu.BaiduMap")) {
            startActivity(intent); // 启动调用
        } else {
            HintUitl.toastShort(getBaseContext(), "没有安装高德/百度地图客户端");
        }
    }


}

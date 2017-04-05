package com.maiyu.hrssc.integration.activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.Adv;
import com.maiyu.hrssc.base.view.AdvertisementImageBanner;
import com.maiyu.hrssc.util.StatusBarCompat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductItemActivity extends BaseActivity {

    @BindView(R.id.ads)
    AdvertisementImageBanner mAds;
    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.jifen)
    TextView mJifen;
    @BindView(R.id.shuliang)
    TextView mShuliang;
    @BindView(R.id.jiazhi)
    TextView mJiazhi;
    @BindView(R.id.duihuan_btn)
    TextView mDuihuanBtn;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_product_item);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        StatusBarCompat.translucentStatusBar(this, false); // 状态栏半透明
        ArrayList list = new ArrayList();
        String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491392546064&di=dd13abae446ba1f5bc5e74b7793e3063&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D1714026931%2C697146313%26fm%3D214%26gp%3D0.jpg";
        String url2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491395554431&di=ee709ec1711a30d7772296c750bf901c&imgtype=0&src=http%3A%2F%2Fhenan.sinaimg.cn%2F2015%2F0116%2FU10412P827DT20150116085814.jpg";
        String url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491395586525&di=11bece016a772c5f1909b22a973eeef7&imgtype=0&src=http%3A%2F%2Fupload1.techweb.com.cn%2F2015%2F1222%2F1450758045405.jpg";
        String url4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491395617145&di=ed3f2b31320aae4db87b6464ed6edd7a&imgtype=0&src=http%3A%2F%2Fwww.chinairn.com%2Feditor%2F..%2FUserFiles%2Fimage%2F20150925%2F20150925175522_4006.jpg";

        list.add(new Adv(1, url1, "1", "title1", "http://www.baidu.com/", "", "aaaa", ""));
        list.add(new Adv(1, url2, "1", "title1", "http://www.baidu.com/", "", "aaaa", ""));
        list.add(new Adv(1, url3, "1", "title1", "http://www.baidu.com/", "", "aaaa", ""));
        list.add(new Adv(1, url4, "1", "title1", "http://www.baidu.com/", "", "aaaa", ""));
        mAds.setViewData(list);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.back_btn, R.id.duihuan_btn})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.duihuan_btn:
                break;
        }

    }
}

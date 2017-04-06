package com.maiyu.hrssc.integration.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.view.AdvertisementImageBanner;
import com.maiyu.hrssc.integration.activity.DuihuanRecordActivity;
import com.maiyu.hrssc.integration.adapter.INtegrationGridAdapter;
import com.maiyu.hrssc.integration.bean.IngegrationProduct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class IntegrationFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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
    @BindView(R.id.dizhi_iv)
    ImageView mDizhiIv;
    @BindView(R.id.label_dangqianjf)
    TextView mLabelDangqianjf;
    @BindView(R.id.value_dangqianjf)
    TextView mValueDangqianjf;
    @BindView(R.id.jiantou_iv)
    ImageView mJiantouIv;
    @BindView(R.id.dizhi_tv)
    TextView mDizhiTv;
    @BindView(R.id.jifen_rl)
    RelativeLayout mJifenRl;
    @BindView(R.id.grid_view)
    GridView mGridView;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private INtegrationGridAdapter mGridAdapter;


    public IntegrationFragment() {
        // Required empty public constructor
    }

    public static IntegrationFragment newInstance() {
        IntegrationFragment fragment = new IntegrationFragment();
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
        View view = inflater.inflate(R.layout.fragment_jifen, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        // 初始化gridview
        // 设置gridview
        mGridAdapter = new INtegrationGridAdapter(getActivity());
        mGridView.setAdapter(mGridAdapter);

        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491392546064&di=dd13abae446ba1f5bc5e74b7793e3063&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D1714026931%2C697146313%26fm%3D214%26gp%3D0.jpg";
        List list = new ArrayList();
        list.add(new IngegrationProduct(1, "兑换商品/iPhone 6 128G 玫瑰金", "400", url));
        list.add(new IngegrationProduct(1, "兑换商品/iPhone 6 128G 玫瑰金", "400", url));
        list.add(new IngegrationProduct(1, "兑换商品/iPhone 6 128G 玫瑰金", "400", url));
        list.add(new IngegrationProduct(1, "兑换商品/iPhone 6 128G 玫瑰金", "400", url));
        list.add(new IngegrationProduct(1, "兑换商品/iPhone 6 128G 玫瑰金", "400", url));
        list.add(new IngegrationProduct(1, "兑换商品/iPhone 6 128G 玫瑰金", "400", url));
        list.add(new IngegrationProduct(1, "兑换商品/iPhone 6 128G 玫瑰金", "400", url));
        list.add(new IngegrationProduct(1, "兑换商品/iPhone 6 128G 玫瑰金", "400", url));

        mGridAdapter.setViewData(list, mGridView);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.address_btn, R.id.msg_btn, R.id.jifen_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.address_btn:
                break;
            case R.id.msg_btn:
                break;
            case R.id.jifen_rl:
                startActivity(new Intent(getActivity(), DuihuanRecordActivity.class));
                break;
        }
    }
}

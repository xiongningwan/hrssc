package com.maiyu.hrssc.my.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.AddressManageActivity;
import com.maiyu.hrssc.home.activity.todo.SignActivity;
import com.maiyu.hrssc.my.activity.FastServiceActivity;
import com.maiyu.hrssc.my.activity.FeedBackActivity;
import com.maiyu.hrssc.my.activity.HelpCenterActivity;
import com.maiyu.hrssc.my.activity.ModifyActivity;
import com.maiyu.hrssc.my.activity.PersonalInfoActivity;
import com.maiyu.hrssc.my.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment {
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
    @BindView(R.id.head_img_view)
    CircleImageView mHeadImgView;
    @BindView(R.id.point)
    View mPoint;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.gonghao_label_tv)
    TextView mGonghaoLabelTv;
    @BindView(R.id.gonghao_value_tv)
    TextView mGonghaoValueTv;
    @BindView(R.id.qiandao_btn)
    Button mQiandaoBtn;
    @BindView(R.id.activity_main_ads)
    RelativeLayout mActivityMainAds;
    @BindView(R.id.qianming_iv)
    ImageView mQianmingIv;
    @BindView(R.id.wodeqianming_rl)
    RelativeLayout mWodeqianmingRl;
    @BindView(R.id.xiugaimima_iv)
    ImageView mXiugaimimaIv;
    @BindView(R.id.xiugaimima_rl)
    RelativeLayout mXiugaimimaRl;
    @BindView(R.id.shoujiandizhi_iv)
    ImageView mShoujiandizhiIv;
    @BindView(R.id.shoujiandizhi_rl)
    RelativeLayout mShoujiandizhiRl;
    @BindView(R.id.kuaijiefuwu_iv)
    ImageView mKuaijiefuwuIv;
    @BindView(R.id.kuaijiefuwu_rl)
    RelativeLayout mKuaijiefuwuRl;
    @BindView(R.id.bangzhuzhongxin_iv)
    ImageView mBangzhuzhongxinIv;
    @BindView(R.id.bangzhuzhongxin_rl)
    RelativeLayout mBangzhuzhongxinRl;
    @BindView(R.id.yijianfankui_iv)
    ImageView mYijianfankuiIv;
    @BindView(R.id.yijianfankui_rl)
    RelativeLayout mYijianfankuiRl;
    @BindView(R.id.shezhi_iv)
    ImageView mShezhiIv;
    @BindView(R.id.shezhi_rl)
    RelativeLayout mShezhiRl;
    @BindView(R.id.jiayi_iv)
    ImageView mJiayi;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
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
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.msg_btn, R.id.head_img_view, R.id.name_tv, R.id.gonghao_label_tv, R.id.gonghao_value_tv, R.id.qiandao_btn, R.id.wodeqianming_rl, R.id.xiugaimima_rl, R.id.shoujiandizhi_rl, R.id.kuaijiefuwu_rl, R.id.bangzhuzhongxin_rl, R.id.yijianfankui_rl, R.id.shezhi_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.msg_btn:
                break;
            case R.id.head_img_view:
                startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
                break;
            case R.id.name_tv:
                startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
                break;
            case R.id.gonghao_label_tv:
                startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
                break;
            case R.id.gonghao_value_tv:
                startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
                break;
            case R.id.qiandao_btn:

                if (mQiandaoBtn.isSelected()) {
                    mQiandaoBtn.setSelected(false);
                    mJiayi.setVisibility(View.GONE);
                } else {
                    mQiandaoBtn.setSelected(true);
                    mJiayi.setVisibility(View.VISIBLE);
                    mQiandaoBtn.setClickable(false);
                }


                break;
            case R.id.wodeqianming_rl:
                startActivity(new Intent(getActivity(), SignActivity.class));
                break;
            case R.id.xiugaimima_rl:
                startActivity(new Intent(getActivity(), ModifyActivity.class));
                break;
            case R.id.shoujiandizhi_rl:
                startActivity(new Intent(getActivity(), AddressManageActivity.class));
                break;
            case R.id.kuaijiefuwu_rl:
                startActivity(new Intent(getActivity(), FastServiceActivity.class));
                break;
            case R.id.bangzhuzhongxin_rl:
                startActivity(new Intent(getActivity(), HelpCenterActivity.class));
                break;
            case R.id.yijianfankui_rl:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.shezhi_rl:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }
}

package com.maiyu.hrssc.my.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.AddressManageActivity;
import com.maiyu.hrssc.base.activity.MessagesActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.home.activity.todo.SignActivity;
import com.maiyu.hrssc.my.activity.FastServiceActivity;
import com.maiyu.hrssc.my.activity.FeedBackActivity;
import com.maiyu.hrssc.my.activity.HelpCenterActivity;
import com.maiyu.hrssc.my.activity.ModifyActivity;
import com.maiyu.hrssc.my.activity.PersonalInfoActivity;
import com.maiyu.hrssc.my.activity.SettingActivity;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.ImageLoaderUtil;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

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
    @BindView(R.id.msg_point)
    View mMsgPoint;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private User mUser;


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
        initView();
        initData();
        return view;
    }

    private void initView() {
        mUser = DataCenter.getInstance().getuser();
        if (mUser.getHead() != null) {
            ImageLoaderUtil.loadImage(mHeadImgView, ConstantValue.FILE_SERVER_URI + mUser.getHead(), R.mipmap.timg);
        }

        mNameTv.setText(mUser.getName());
        mGonghaoValueTv.setText("" + mUser.getUin());

        Boolean msgPointisVisiable = SharedPreferencesUtil.getIsPointViewVisibility(getActivity());
        if (msgPointisVisiable) {
            mMsgPoint.setVisibility(View.VISIBLE);
        } else {
            mMsgPoint.setVisibility(View.GONE);
        }

    }

    private void initData() {

        new SignOrNotAsyncTask(mUser.getToken()).execute();
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
                startActivity(new Intent(getActivity(), MessagesActivity.class));
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
                    // 已经签到，再次点击
                    mQiandaoBtn.setSelected(true);
                    mJiayi.setVisibility(View.GONE);
                } else {
                    // 未有签到，点击
                    mQiandaoBtn.setSelected(true);
                    mQiandaoBtn.setText("今日已签");
                    mJiayi.setVisibility(View.VISIBLE);
                    AlphaAnimation animation = new AlphaAnimation(1, 0);
                    animation.setDuration(3000);//设置动画时间
                    animation.setFillAfter(true);
                    // animation.setStartOffset(1000);
                    // animation.setStartTime(2000);
                    mJiayi.setAnimation(animation);
                }
                new SignAsyncTask(mUser.getToken()).execute();


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

    /**
     * 签到
     */
    class SignAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private int sign_integral;

        public SignAsyncTask(String token) {
            super();
            this.token = token;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                sign_integral = engine.sign(getActivity(), token);
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
            if (sign_integral != 0) {
                HintUitl.toastShort(getActivity(), "获得积分：" + sign_integral);
                User user = DataCenter.getInstance().getuser();
                int amount = Integer.parseInt(user.getAmount()) + sign_integral;
                user.setAmount(String.valueOf(amount));
                DataCenter.getInstance().setUser(user);
                DataCenter.getInstance().notifyIntegralChange();

            }

            super.onPostExecute(result);
        }
    }

    /**
     * 检查是否签到
     */
    class SignOrNotAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private int signed;

        public SignOrNotAsyncTask(String token) {
            super();
            this.token = token;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                signed = engine.signOrNot(getActivity(), token);
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
            if (signed == 1) { //0-未签到  1-已签到
                mQiandaoBtn.setSelected(true);
                mQiandaoBtn.setText("今日已签");
            } else {
                mQiandaoBtn.setSelected(false);
            }

            super.onPostExecute(result);
        }
    }


}

package com.maiyu.hrssc.service.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.MessagesActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.service.kefu.LoginActivity;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ServiceFragment extends Fragment {


    @BindView(R.id.msg_iv)
    ImageView mMsgIv;
    @BindView(R.id.msg_btn)
    RelativeLayout mMsgBtn;
    @BindView(R.id.kefu_btn)
    ImageView mKefuBtn;
    Unbinder unbinder;
    @BindView(R.id.msg_point)
    View mMsgPoint;
    private RedPointDataObserver redPointObserver;

    public ServiceFragment() {
        // Required empty public constructor
    }

    public static ServiceFragment newInstance() {
        ServiceFragment fragment = new ServiceFragment();
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
        View view = inflater.inflate(R.layout.fragment_kefu, container, false);
        unbinder = ButterKnife.bind(this, view);

        initViews();
        return view;
    }

    private void initViews() {

        redPointObserver = new RedPointDataObserver();
        DataCenter.getInstance().registerObserver(redPointObserver);

        setRedPoint();
    }
    /**
     * 小红点观察者
     */
    public class RedPointDataObserver implements DataCenter.DataObserver {

        @Override
        public void onDataChangedListener(int type, Object... data) {
            if (type == DataCenter.TYPE_RED_POINT_STATUS) {
                setRedPoint();
            }
        }
    }


    void  setRedPoint() {
        Boolean msgPointisVisiable = SharedPreferencesUtil.getIsPointViewVisibility(getActivity());
        if (msgPointisVisiable) {
            mMsgPoint.setVisibility(View.VISIBLE);
        } else {
            mMsgPoint.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        DataCenter.getInstance().unregisterObserver(redPointObserver);
    }

    @OnClick({R.id.msg_iv, R.id.kefu_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.msg_iv:
               // HintUitl.toastShort(getActivity(), "msg");
                startActivity(new Intent(getActivity(), MessagesActivity.class));
                break;
            case R.id.kefu_btn:
                startActivity(new Intent(getActivity(), LoginActivity.class));

                break;
        }
    }



}

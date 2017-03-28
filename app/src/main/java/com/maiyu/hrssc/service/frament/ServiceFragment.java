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
import com.maiyu.hrssc.service.kefu.LoginActivity;
import com.maiyu.hrssc.util.HintUitl;

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
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.msg_iv, R.id.kefu_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.msg_iv:
                HintUitl.toastShort(getActivity(), "msg");
                break;
            case R.id.kefu_btn:
                startActivity(new Intent(getActivity(), LoginActivity.class));

                break;
        }
    }



}

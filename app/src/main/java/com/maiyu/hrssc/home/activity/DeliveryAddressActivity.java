package com.maiyu.hrssc.home.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.adapter.DeliveryAddressAdapter;
import com.maiyu.hrssc.home.bean.Address;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邮寄
 */
public class DeliveryAddressActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.address_recycle_view)
    RecyclerView mAddressRecycleView;
    private DeliveryAddressAdapter mAdapter;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_choose_address);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("选择收件地址", true, false);
        mAdapter = new DeliveryAddressAdapter(this);
        mAddressRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAddressRecycleView.setItemAnimator(new DefaultItemAnimator());
        //mRecyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.input_bottom_line));
        mAddressRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        // 模拟数据
        List<Address> datas = new ArrayList<>();
        datas.add(new Address("中兴南山科研" , "广东深圳南山区科研大楼"));
        datas.add(new Address("中兴南山科研" , "广东深圳南山区科研大楼"));
        mAdapter.setData(datas);
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.head_view, R.id.address_recycle_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_view:
                break;
            case R.id.address_recycle_view:
                break;
        }
    }
}

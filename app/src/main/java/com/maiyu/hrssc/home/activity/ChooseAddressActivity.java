package com.maiyu.hrssc.home.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.adapter.AddressAdapter;
import com.maiyu.hrssc.home.bean.SelfAddress;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择地址
 */
public class ChooseAddressActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.address_recycle_view)
    RecyclerView mAddressRecycleView;
    private AddressAdapter mAdapter;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_choose_address);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("选择领取地址", true, false);
        mAdapter = new AddressAdapter(this, new AddressItemOnclickListener());
        mAddressRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAddressRecycleView.setItemAnimator(new DefaultItemAnimator());
        mAddressRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        ArrayList<SelfAddress> addrs = getIntent().getParcelableArrayListExtra("addres");
        if (addrs != null) {
            mAdapter.setData(addrs);
        }
    }

    @Override
    public void initOnClick(View v) {

    }


    class AddressItemOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            SelfAddress selfAddress = (SelfAddress) v.getTag(R.id.key_tag_item_data);
            //HintUitl.toastShort(ChooseAddressActivity.this, selfAddress.getAddress_info());
            Intent intent = new Intent();
            intent.putExtra("selfAddress", selfAddress);
            setResult(RESULT_OK, intent);
            finish();
        }
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

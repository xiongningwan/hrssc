package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.AddressManagerAdapter;
import com.maiyu.hrssc.base.bean.AddressManage;
import com.maiyu.hrssc.base.view.HeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址管理
 */
public class AddressManageActivity extends BaseActivity {
    private static final int LIST_ITEM_EDIT = 104;
    public static final String CONTENT_ITEM = "content_item";
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.address_recycle_view)
    RecyclerView mAddressRecycleView;
    @BindView(R.id.duihuan_btn)
    Button mDuihuanBtn;
    @BindView(R.id.llybuttom)
    LinearLayout mLlybuttom;
    private AddressManagerAdapter mAdapter;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_address_manage);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("收件地址管理", true, false);

        mAdapter = new AddressManagerAdapter(this, new EditOnclickLister());
        mAddressRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAddressRecycleView.setItemAnimator(new DefaultItemAnimator());
        mAddressRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        List<AddressManage> list = new ArrayList<>();
        list.add(new AddressManage("张三", "18780106585", "广东省深圳市南山区科技路55号一栋三单元1034号", true));
        list.add(new AddressManage("张四", "18780106585", "广东省深圳市南山区科技路55号一栋三单元1034号", false));
        list.add(new AddressManage("张五", "18780106585", "广东省深圳市南山区科技路55号一栋三单元1034号", false));
        list.add(new AddressManage("张六", "18780106585", "广东省深圳市南山区科技路55号一栋三单元1034号", false));
        list.add(new AddressManage("张七", "18780106585", "广东省深圳市南山区科技路55号一栋三单元1034号", false));

        mAdapter.setData(list);
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.duihuan_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.duihuan_btn:
                // 添加地址
                startActivity(new Intent(this, AddAddressActivity.class));
                break;
        }
    }

    private class EditOnclickLister implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            // 编辑地址
            AddressManage addressManage = (AddressManage) v.getTag(R.id.key_tag_item_data);
            Intent intent = new Intent(AddressManageActivity.this, AddAddressActivity.class);
            intent.putExtra(CONTENT_ITEM, addressManage);
            startActivityForResult(intent, LIST_ITEM_EDIT);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}

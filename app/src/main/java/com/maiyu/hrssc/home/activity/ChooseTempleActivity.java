package com.maiyu.hrssc.home.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.adapter.TempleAdapter;
import com.maiyu.hrssc.home.bean.SelfAddress;
import com.maiyu.hrssc.home.bean.Template;
import com.maiyu.hrssc.util.HintUitl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择模版
 */
public class ChooseTempleActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.recycle_view)
    RecyclerView mAddressRecycleView;
    private TempleAdapter mAdapter;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_choose_temple);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("模版选择", true, false);

        mAdapter = new TempleAdapter(this, new AddressItemOnclickListener());
        mAddressRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAddressRecycleView.setItemAnimator(new DefaultItemAnimator());
        mAddressRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        ArrayList<SelfAddress> addrs = getIntent().getParcelableArrayListExtra("templates");
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
            Template template = (Template) v.getTag(R.id.key_tag_item_data);
            HintUitl.toastShort(ChooseTempleActivity.this, template.getName());
           /* Intent intent = new Intent();
            intent.putExtra("template", template);
            setResult(RESULT_OK, intent);*/

            Intent intent = new Intent(ChooseTempleActivity.this, WriteTempleActivity.class);
            intent.putExtra("title_name", template.getName());
            intent.putExtra("template", template);
            startActivityForResult(intent, 111);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 111:
                    setResult(RESULT_OK, data);
                    //    HintUitl.toastShort(this, data!=null?data.getStringExtra("address"):"未选取到地址");
                    break;

            }
        }
    }
}

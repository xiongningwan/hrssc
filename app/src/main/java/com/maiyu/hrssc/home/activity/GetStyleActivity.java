package com.maiyu.hrssc.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.bean.SelfAddress;
import com.maiyu.hrssc.util.HintUitl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetStyleActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.zzdy_rl)
    RelativeLayout mZzdyRl;
    @BindView(R.id.zzlq_rl)
    RelativeLayout mZzlqRl;
    @BindView(R.id.yj_rl)
    RelativeLayout mYjRl;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_get_style);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("领取方式", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.head_view, R.id.zzdy_rl, R.id.zzlq_rl, R.id.yj_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_view:
                break;
            case R.id.zzdy_rl:
                Intent data = new Intent();
                data.putExtra("style", "自助打印");
                setResult(RESULT_OK, data);

                break;
            case R.id.zzlq_rl: {
                ArrayList<SelfAddress> addrs = getIntent().getParcelableArrayListExtra("addres");
                Intent intent = new Intent(this, ChooseAddressActivity.class);
                intent.putParcelableArrayListExtra("addres", addrs);
                startActivityForResult(intent, 111);

            }
            break;
            case R.id.yj_rl: {
                Intent data2 = new Intent();
                data2.putExtra("style", "邮寄");
                setResult(RESULT_OK, data2);
                startActivityForResult(new Intent(this, DeliveryAddressActivity.class), 112);
            }
            break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 111:
                    if (data != null) {
                        SelfAddress selfAddress = (SelfAddress) data.getParcelableExtra("selfAddress");
                        data.putExtra("style", "自助领取");
                        setResult(RESULT_OK, data);
                        HintUitl.toastShort(this, data != null ? selfAddress.getAddress() : "未选取到地址");
                    }
                    break;

            }
        }
    }
}

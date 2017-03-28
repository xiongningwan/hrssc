package com.maiyu.hrssc.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择模版
 */
public class ChooseTempleActivity extends BaseActivity {

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
        setContentView(R.layout.activity_choose_temple);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("模版选择", true, false);
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
                Intent intent = new Intent(this, WriteTempleActivity.class);
                intent.putExtra("title_name","招商银行模版");
                startActivity(intent);

                break;
            case R.id.zzlq_rl:
                Intent intent2 = new Intent(this, WriteTempleActivity.class);
                intent2.putExtra("title_name","中国银行模版");
                startActivity(intent2);

                break;
            case R.id.yj_rl:

                Intent intent3 = new Intent(this, WriteTempleActivity.class);
                intent3.putExtra("title_name","工商银行模版");
                startActivity(intent3);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case 111:

                //    HintUitl.toastShort(this, data!=null?data.getStringExtra("address"):"未选取到地址");
                    break;

            }
        }
    }
}

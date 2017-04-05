package com.maiyu.hrssc.home.activity.funds;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FundsActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.gjjcx_btn)
    TextView mGjjcxBtn;
    @BindView(R.id.zfgjjbg_btn)
    TextView mZfgjjbgBtn;
    @BindView(R.id.zfgjjlmk_btn)
    TextView mZfgjjlmkBtn;
    @BindView(R.id.zfgjjzm_btn)
    TextView mZfgjjzmBtn;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_funds);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("公积金", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.gjjcx_btn, R.id.zfgjjbg_btn, R.id.zfgjjlmk_btn, R.id.zfgjjzm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gjjcx_btn:

                startActivity(new Intent(this, FundsHistoryActivity.class));
                break;
            case R.id.zfgjjbg_btn:
                break;
            case R.id.zfgjjlmk_btn:
                break;
            case R.id.zfgjjzm_btn:
                break;
        }
    }
}

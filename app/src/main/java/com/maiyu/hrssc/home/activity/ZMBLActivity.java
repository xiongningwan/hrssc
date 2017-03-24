package com.maiyu.hrssc.home.activity;

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

public class ZMBLActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.xzzmbl)
    TextView mXzzmbl;
    @BindView(R.id.jhsyzmbl)
    TextView mJhsyzmbl;
    @BindView(R.id.xsbxzmbl)
    TextView mXsbxzmbl;
    @BindView(R.id.zzzmbl)
    TextView mZzzmbl;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_zmbl);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("证明办理", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.head_view, R.id.imageView, R.id.xzzmbl, R.id.jhsyzmbl, R.id.xsbxzmbl, R.id.zzzmbl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_view:
                break;
            case R.id.imageView:
                break;
            case R.id.xzzmbl:
                startActivity(new Intent(this, XZZMBLActivity.class));
                break;
            case R.id.jhsyzmbl:
                break;
            case R.id.xsbxzmbl:
                break;
            case R.id.zzzmbl:
                break;
        }
    }
}

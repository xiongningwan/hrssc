package com.maiyu.hrssc.home.activity;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.util.HintUitl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteTempleActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.name_et)
    EditText mNameEt;
    @BindView(R.id.zzdy_rl)
    RelativeLayout mZzdyRl;
    @BindView(R.id.address_et)
    EditText mAddressEt;
    @BindView(R.id.zzlq_rl)
    RelativeLayout mZzlqRl;
    @BindView(R.id.tel_et)
    EditText mTelEt;
    @BindView(R.id.yj_rl)
    RelativeLayout mYjRl;
    private TextView mRightButtonText;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_write_temple);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        String titleName = getIntent().getStringExtra("title_name");
        mHeadView.setTitle(titleName, true, true);
        mRightButtonText = mHeadView.getRightButtonText();
        mRightButtonText.setText("保存");
        mRightButtonText.setTextColor(ContextCompat.getColor(this,R.color.project_color_general_hyperlink));
        mRightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HintUitl.toastShort(WriteTempleActivity.this, "保存");
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


}

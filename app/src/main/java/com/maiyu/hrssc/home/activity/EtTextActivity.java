package com.maiyu.hrssc.home.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EtTextActivity extends BaseActivity {

    public static final String TEXT = "text_content";
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.text_et)
    EditText mTextEt;
    private TextView mRightButtonText;
    private String mContent;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_et_text);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        String titleName = getIntent().getStringExtra("title_name");
        mHeadView.setTitle("填写", true, true);
        mRightButtonText = mHeadView.getRightButtonText();
        mRightButtonText.setText("保存");
        mRightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        mRightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HintUitl.toastShort(EtTextActivity.this, "保存");
                mContent = mTextEt.getText().toString();
                setResult(RESULT_OK, new Intent().putExtra(TEXT, mContent));
                finish();
            }
        });
    }

    @Override
    public void initData() {
        int set = SharedPreferencesUtil.getSpecialParamSet(this);

        if (1 == set) {
            mTextEt.setHint("借用事由");
        } else if (2 == set) {
            mTextEt.setHint("申请说明");
        } else if (3 == set) {
            mTextEt.setHint("申请说明");
        } else if (4 == set) {
            mTextEt.setHint("申请说明");
        } else if (7 == set) {
            mTextEt.setHint("预约入职日期 格式：yyyy-MM-dd HH:mm:ss");
        } else if (8 == set) {
            mTextEt.setHint("申请说明");
        } else if (9 == set) {
            mTextEt.setHint("申请说明");
        } else {
            mTextEt.setHint("申请说明");
        }
    }

    @Override
    public void initOnClick(View v) {

    }


}

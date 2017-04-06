package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.maiyu.hrssc.R.id.title_left_button;

public class EditActivity extends BaseActivity {

    @BindView(title_left_button)
    RelativeLayout mTitleLeftBtn;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.title_right_button)
    RelativeLayout mTitleRightBtn;



    @BindView(R.id.name_et)
    EditText mNameEt;
    @BindView(R.id.zzdy_rl)
    RelativeLayout mZzdyRl;

    public static final String EDIT_CONTENT = "edit_content";

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mTitleText.setText("编辑");
        mTitleLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String content = mNameEt.getText().toString();
                if(content != null && !content.equals("")) {
                    Intent intent = new Intent();
                    intent.putExtra(EDIT_CONTENT, content);
                    setResult(RESULT_OK, intent);
                }
                finish();

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

package com.maiyu.hrssc.home.activity.files;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 档案借阅
 */
public class FilesBorrowActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_files_borrow);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("档案借阅", true, true);
        TextView rightText = mHeadView.getRightButtonText();
        rightText.setText("提交");
        rightText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

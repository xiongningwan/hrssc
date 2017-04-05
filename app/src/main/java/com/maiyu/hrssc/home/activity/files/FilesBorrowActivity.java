package com.maiyu.hrssc.home.activity.files;

import android.view.View;

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
        mHeadView.setTitle("档案借阅", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }
}

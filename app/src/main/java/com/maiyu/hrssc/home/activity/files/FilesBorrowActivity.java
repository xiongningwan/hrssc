package com.maiyu.hrssc.home.activity.files;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.bean.Category2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 档案借阅
 */
public class FilesBorrowActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    private String mToken;
    private String mId;
    private String mTitle;
    private List<Category2> mCateGory2List;
    private LoadingDialog mLoadingDialog;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_files_borrow);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("name");
        mLoadingDialog = new LoadingDialog(this);
        mHeadView.setTitle(mTitle, true, true);
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

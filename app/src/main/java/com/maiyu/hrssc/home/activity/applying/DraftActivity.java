package com.maiyu.hrssc.home.activity.applying;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.home.activity.applying.adapter.DraftAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.Draft;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DraftActivity extends BaseActivity {

    @BindView(R.id.left_button_icon)
    ImageView mLeftButtonIcon;
    @BindView(R.id.title_left_button)
    RelativeLayout mTitleLeftButton;
    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.delete_btn)
    TextView mDeleteBtn;
    @BindView(R.id.cancle_btn)
    TextView mCancleBtn;
    @BindView(R.id.confirm_delete_btn)
    TextView mConfirmDeleteBtn;
    @BindView(R.id.title_right_button)
    RelativeLayout mTitleRightButton;
    @BindView(R.id.draft_recyclerView)
    RecyclerView mDraftRecyclerView;
    private DraftAdapter mAdapter;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_draft);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mCancleBtn.setVisibility(View.GONE);
        mConfirmDeleteBtn.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        // 设置列表
        mAdapter = new DraftAdapter(this);
        mDraftRecyclerView.setLayoutManager(new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDraftRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDraftRecyclerView.setAdapter(mAdapter);


        List<Draft> list = new ArrayList<>();
        list.add(new Draft("工资证明办理"," 2017-02-21 : 14:00"));
        list.add(new Draft("保密协议办理"," 2017-02-21 : 14:00"));
        list.add(new Draft("劳动证明办理"," 2017-02-21 : 14:00"));
        list.add(new Draft("工资证明办理"," 2017-02-21 : 14:00"));

        mAdapter.setData(list);
     }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.title_left_button, R.id.delete_btn, R.id.cancle_btn, R.id.confirm_delete_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_button:
                finish();
                break;
            case R.id.delete_btn:
                mDeleteBtn.setVisibility(View.GONE);
                mCancleBtn.setVisibility(View.VISIBLE);
                mConfirmDeleteBtn.setVisibility(View.VISIBLE);
                mAdapter.showCircleDelBtn(true);
                break;
            case R.id.cancle_btn:
                mDeleteBtn.setVisibility(View.VISIBLE);
                mCancleBtn.setVisibility(View.GONE);
                mConfirmDeleteBtn.setVisibility(View.GONE);
                mAdapter.showCircleDelBtn(false);
                break;
            case R.id.confirm_delete_btn:
                // 删除选中list item
                mAdapter.detele();
                break;
        }
    }
}

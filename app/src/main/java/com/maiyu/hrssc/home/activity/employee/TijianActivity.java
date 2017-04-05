package com.maiyu.hrssc.home.activity.employee;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.applying.adapter.ImageGridAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.AttachImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TijianActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.city_name_icon)
    ImageView mCityNameIcon;
    @BindView(R.id.city_name)
    TextView mCityName;
    @BindView(R.id.line_divider)
    View mLineDivider;
    @BindView(R.id.shuoming_tv)
    TextView mShuomingTv;
    @BindView(R.id.shuoming_ll)
    RelativeLayout mShuomingLl;
    @BindView(R.id.ditu_iv)
    ImageView mDituIv;
    @BindView(R.id.jiantou_iv)
    ImageView mJiantouIv;
    @BindView(R.id.dizhi_tv)
    TextView mDizhiTv;
    @BindView(R.id.dizhi_rl)
    RelativeLayout mDizhiRl;
    @BindView(R.id.no_result_tv)
    TextView mNoResultTv;
    @BindView(R.id.grid_view)
    GridView mGridView;
    private ImageGridAdapter mGridAdapter;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_tijian);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("体检", true, false);
        // 设置gridview
        mGridAdapter = new ImageGridAdapter(this);
        mGridView.setAdapter(mGridAdapter);

    }

    @Override
    public void initData() {
        List<AttachImage> images = new ArrayList<>();
        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));
        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));
        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));
        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));
        mGridAdapter.updatePickImageView(images, mGridView);
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.shuoming_ll, R.id.dizhi_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shuoming_ll:
                break;
            case R.id.dizhi_rl:
                startActivity(new Intent(this, DizhiActivity.class));

                break;
        }
    }
}

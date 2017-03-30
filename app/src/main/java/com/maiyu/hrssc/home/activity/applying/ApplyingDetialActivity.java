package com.maiyu.hrssc.home.activity.applying;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.applying.adapter.ProgressAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.TypeText;
import com.maiyu.hrssc.util.HintUitl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 申请详情
 */
public class ApplyingDetialActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.id_number)
    TextView mIdNumber;
    @BindView(R.id.divier_line)
    View mDivierLine;
    @BindView(R.id.icon_xiangqing)
    ImageView mIconXiangqing;
    @BindView(R.id.title_name)
    TextView mTitleName;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.status)
    TextView mStatus;
    @BindView(R.id.hetong_lable)
    TextView mHetongLable;
    @BindView(R.id.arrow_iv)
    ImageView mArrowIv;
    @BindView(R.id.hetong_rl)
    RelativeLayout mHetongRl;
    @BindView(R.id.yundanjilv_lable)
    TextView mYundanjilvLable;
    @BindView(R.id.yundanjilv_arrow_iv)
    ImageView mYundanjilvArrowIv;
    @BindView(R.id.yundanjilv_rl)
    RelativeLayout mYundanjilvRl;
    @BindView(R.id.wendangdayin_lable)
    TextView mWendangdayinLable;
    @BindView(R.id.code)
    TextView mCode;
    @BindView(R.id.dayin_success_iv)
    ImageView mDayinSuccessIv;
    @BindView(R.id.wendangdayin_rl)
    RelativeLayout mWendangdayinRl;
    @BindView(R.id.divier_line_2)
    View mDivierLine2;
    @BindView(R.id.icon_dizhi)
    ImageView mIconDizhi;
    @BindView(R.id.shenheren_lable)
    TextView mShenherenLable;
    @BindView(R.id.shenheren_text)
    TextView mShenherenText;
    @BindView(R.id.point)
    View mPoint;
    @BindView(R.id.icon_yewuyuan)
    ImageView mIconYewuyuan;
    @BindView(R.id.yewuyuan_lable)
    TextView mYewuyuanLable;
    @BindView(R.id.yewuyuan_text)
    TextView mYewuyuanText;
    @BindView(R.id.qianshu_address)
    RelativeLayout mQianshuAddress;
    @BindView(R.id.vertivcal_content)
    RecyclerView mVertivcalContent;
    private ProgressAdapter mAdapter;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_applying_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        String title = getIntent().getStringExtra("title");

        mHeadView.setTitle(title, true, true);
        TextView rightButtonText = mHeadView.getRightButtonText();
        rightButtonText.setText("评价");
        rightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HintUitl.toastShort(ApplyingDetialActivity.this, "评价");
            }
        });



        // 设置列表
        mAdapter = new ProgressAdapter(this);
        mVertivcalContent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mVertivcalContent.setItemAnimator(new DefaultItemAnimator());
        mVertivcalContent.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        List<TypeText> list = new ArrayList<>();
        list.add(new TypeText("办理说明文字办理说明文字办理说明文字办理说明文字办理说明字办理说明文字办理说明字办理说明文字办理说明字办理说明文字办理说明文字办理说明文字","2017-02-14 14：22"));
        list.add(new TypeText("字办理说明文字办理说明字办理说明文字办理说明字字办理说明文字办理说明办理说明文字办理说明字办理说明文字办理说明办理说明","2017-02-14 14：22"));
        list.add(new TypeText("字办理说明文字办理说明字办理说明文字办理说字办理说明文字办理说明明","2017-02-14 14：22"));
        list.add(new TypeText("字办理说明文字办理说明","2017-02-14 14：22"));
        list.add(new TypeText("字办理说明文字办理说明字办理说明文字办理说明","2017-02-14 14：22"));


        mAdapter.setData(list);
    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.hetong_rl, R.id.yundanjilv_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hetong_rl:
                break;
            case R.id.yundanjilv_rl:
                break;
        }
    }
}

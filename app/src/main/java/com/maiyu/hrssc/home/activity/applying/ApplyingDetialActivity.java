package com.maiyu.hrssc.home.activity.applying;

import android.content.Intent;
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
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.applying.adapter.ProgressAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.AProgress;
import com.maiyu.hrssc.home.activity.applying.bean.ApplyDetail;
import com.maiyu.hrssc.home.activity.applying.bean.FindApplyDetailData;
import com.maiyu.hrssc.util.AppUtil;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maiyu.hrssc.R.id.dayin_success_iv;
import static com.maiyu.hrssc.R.id.status;

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
    @BindView(status)
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
    @BindView(dayin_success_iv)
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
    private String mToken;
    private String mAid;
    private FindApplyDetailData mFindApplyDetailData;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private TextView rightButtonText;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_applying_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        String title = getIntent().getStringExtra("title");
        mAid = getIntent().getStringExtra("id");

        mHeadView.setTitle(title, true, true);
        rightButtonText = mHeadView.getRightButtonText();
        rightButtonText.setText("评价");
        rightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightButtonText.setVisibility(View.GONE);


        // 设置列表
        mAdapter = new ProgressAdapter(this);
        mVertivcalContent.setLayoutManager(new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mVertivcalContent.setItemAnimator(new DefaultItemAnimator());
        mVertivcalContent.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        if (mAid != null) {
            new FindApplyDataDataAsyncTask(mToken, mAid).execute();
        }
    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.hetong_rl, R.id.yundanjilv_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hetong_rl: {
                // 内容详情
                Intent intent = new Intent(this, ContentDetialActivity.class);
                intent.putExtra("FindApplyDetailData", mFindApplyDetailData);
                startActivity(intent);
            }
            break;
            case R.id.yundanjilv_rl:
                // 运单记录
               /* Intent intent = new Intent(this, YDJLActivity.class);
                intent.putExtra("FindApplyDetailData", mFindApplyDetailData);
                startActivity(intent);*/
                HintUitl.toastShort(this, "【待开通】");
                break;
        }
    }

    /**
     * 获取业务详情
     */
    class FindApplyDataDataAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String aid;
        private FindApplyDetailData findApplyDetailData;

        public FindApplyDataDataAsyncTask(String token, String aid) {
            super();

            this.token = token;
            this.aid = aid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                findApplyDetailData = engine.findApplyDetail(ApplyingDetialActivity.this, token, aid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(ApplyingDetialActivity.this)) {
                return;
            }
            if (findApplyDetailData != null) {
                setData(findApplyDetailData);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(FindApplyDetailData findApplyDetailData) {
        mFindApplyDetailData = findApplyDetailData;
        ApplyDetail applyDetail = findApplyDetailData.getApply();
        List<AProgress> list = findApplyDetailData.getProgressList();
        if (applyDetail != null) {
            mIdNumber.setText("NO." + applyDetail.getWork_order());
            mTitleName.setText(applyDetail.getName());
            setTime(applyDetail);
            setStatusString(applyDetail);
            setPrintData(applyDetail);
            mShenherenText.setText(applyDetail.getAudit_name());
            mYewuyuanText.setText(applyDetail.getDeal_name());
        }

        if (list != null && list.size() > 0) {
            mAdapter.setData(list);
        }

    }


    void setTime(ApplyDetail applyDetail) {
        Date dateTime = null;
        try {
            dateTime = sdf.parse(applyDetail.getCreate_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mTime.setText(AppUtil.setTime(dateTime));
    }

    void setStatusString(final ApplyDetail applyDetail) {
        if ("0".equals(applyDetail.getStatus())) {
            mStatus.setText("待审核");
        } else if ("1".equals(applyDetail.getStatus())) {
            mStatus.setText("待办理");
        } else if ("2".equals(applyDetail.getStatus())) {
            mStatus.setText("待领取");
        } else if ("3".equals(applyDetail.getStatus())) {
            mStatus.setText("待评价");
            rightButtonText.setVisibility(View.VISIBLE);
            rightButtonText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  HintUitl.toastShort(ApplyingDetialActivity.this, "评价");

                    Intent intent = new Intent(ApplyingDetialActivity.this, EvaluationActivity.class);
                    intent.putExtra("aid", applyDetail.getId());
                    intent.putExtra("name", applyDetail.getName());
                    intent.putExtra("orderNo", applyDetail.getWork_order());
                    startActivity(intent);
                }
            });
        } else if ("4".equals(applyDetail.getStatus())) {
            mStatus.setText("已完成");
        } else if ("5".equals(applyDetail.getStatus())) {
            mStatus.setText("已驳回");
        }
    }

    void setPrintData(ApplyDetail applyDetail) {
        // 领取方式：0:自取 1:邮寄, 2打印
        if ("2".equals(applyDetail.getGet_way())) {
            mWendangdayinRl.setVisibility(View.VISIBLE);
            // 打印状态  0-打印失败 1-打印成功 2-未打印
            if ("0".equals(applyDetail.getPrint_status())) {
                mDayinSuccessIv.setVisibility(View.GONE);
            } else if ("1".equals(applyDetail.getPrint_status())) {
                mDayinSuccessIv.setVisibility(View.VISIBLE);
            } else if ("2".equals(applyDetail.getPrint_status())) {
                mDayinSuccessIv.setVisibility(View.GONE);
            }

            mCode.setText(applyDetail.getPrint_code());
        } else {
            mWendangdayinRl.setVisibility(View.GONE);
        }
    }


}

package com.maiyu.hrssc.home.activity.applying;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.activity.WebActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.applying.adapter.AttachFileAdapter;
import com.maiyu.hrssc.home.activity.applying.adapter.ImageGridAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.AProgress;
import com.maiyu.hrssc.home.activity.applying.bean.ApplyDetail;
import com.maiyu.hrssc.home.activity.applying.bean.FindApplyDetailData;
import com.maiyu.hrssc.util.HintUitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maiyu.hrssc.R.id.blcs_text;
import static com.maiyu.hrssc.R.id.blsm_rl;
import static com.maiyu.hrssc.R.id.choose_mould_text;
import static com.maiyu.hrssc.R.id.lqfs_text;

/**
 * 内容详情
 */
public class ContentDetialActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.blcs_lable)
    TextView mBlcsLable;
    @BindView(blcs_text)
    TextView mBlcsText;
    @BindView(R.id.blcs_rl)
    RelativeLayout mBlcsRl;
    @BindView(R.id.blsm_lable)
    TextView mBlsmLable;
    @BindView(R.id.blsm_arrow_iv)
    ImageView mBlsmArrowIv;
    @BindView(blsm_rl)
    RelativeLayout mBlsmRl;
    @BindView(R.id.lqfs_lable)
    TextView mLqfsLable;
    @BindView(R.id.lqfs_arrow_iv)
    ImageView mLqfsArrowIv;
    @BindView(lqfs_text)
    TextView mLqfsText;
    @BindView(R.id.lqfs_rl)
    RelativeLayout mLqfsRl;
    @BindView(R.id.fujian_lable)
    TextView mFujianLable;
    @BindView(R.id.file_recyclerView)
    RecyclerView mFileRecyclerView;
    @BindView(R.id.tupian_lable)
    TextView mTupianLable;
    @BindView(R.id.grid_view)
    GridView mGridView;
    @BindView(R.id.yundanjilv_lable)
    TextView mYundanjilvLable;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.yundanjilv_rl)
    RelativeLayout mYundanjilvRl;
    @BindView(R.id.editText)
    TextView mBeizhuText;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.simple_desc_text)
    TextView mSimpleDescText;
    @BindView(R.id.simple_desc_xh)
    ImageView mSimpleDescXh;
    @BindView(R.id.simple_desc_rl)
    RelativeLayout mSimpleDescRl;
    @BindView(choose_mould_text)
    TextView mChooseMouldText;
    @BindView(R.id.choose_mould_xh)
    ImageView mChooseMouldXh;
    @BindView(R.id.choose_mould_rl)
    RelativeLayout mChooseMouldRl;
    AttachFileAdapter mAttachFileAdapter;
    ImageGridAdapter mGridAdapter;
    private FindApplyDetailData mFindApplyDetailData;
    private String mLink = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_content_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("内容详情", true, false);
        mFindApplyDetailData = (FindApplyDetailData) getIntent().getParcelableExtra("FindApplyDetailData");

        // 设置列表
        mAttachFileAdapter = new AttachFileAdapter(this);
        mFileRecyclerView.setLayoutManager(new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mFileRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFileRecyclerView.setAdapter(mAttachFileAdapter);


        // 设置gridview
        mGridAdapter = new ImageGridAdapter(this);
        mGridView.setAdapter(mGridAdapter);
    }

    @Override
    public void initData() {
        if (mFindApplyDetailData != null) {
            ApplyDetail applyDetail = mFindApplyDetailData.getApply();
            List<AProgress> list = mFindApplyDetailData.getProgressList();
            if (applyDetail != null) {

                mBlcsText.setText(applyDetail.getCity());
                mLink = applyDetail.getLink();
                setGetWay(applyDetail.getGet_way());

                mAttachFileAdapter.setData(getFiles(list));
                mGridAdapter.updatePickImageView(getImages(list), mGridView);
                setTime(applyDetail.getCreate_time());
                mBeizhuText.setText(applyDetail.getComment());
                mSimpleDescText.setText(applyDetail.getBrief());
                mChooseMouldText.setText(applyDetail.getTpl_name());
            }
        }
    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.blcs_rl, blsm_rl, R.id.lqfs_rl, R.id.yundanjilv_rl, R.id.simple_desc_rl, R.id.choose_mould_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blcs_rl:
                break;
            case blsm_rl:
                openWebActivity();
                break;
            case R.id.lqfs_rl:
                break;
            case R.id.yundanjilv_rl:
                break;
            case R.id.simple_desc_rl:
                break;
            case R.id.choose_mould_rl:
                break;
        }
    }

    void openWebActivity() {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", mLink);
        intent.putExtra("titleName", "帮助说明");
        intent.putExtra("type", ConstantValue.TYPE_IMPORTANT);
        startActivity(intent);
    }

    void setGetWay(String getWay) {
        //领取方式：0:自取 1:邮寄, 2打印
        if ("0".equals(getWay)) {
            mLqfsText.setText("自助领取");
        } else if ("1".equals(getWay)) {
            mLqfsText.setText("邮寄");
        } else if ("2".equals(getWay)) {
            mLqfsText.setText("自助打印");
        }
    }

    List<String> getFiles(List<AProgress> list) {
        // 添加文件
        List<String> files = new ArrayList<>();
        for (AProgress aprogress : list) {
            String[] fileArr = aprogress.getAttachs().split(";");
            for (int i = 0; i < fileArr.length; i++) {
                files.add(fileArr[i]);
            }
        }
        return files;

    }

    List<String> getImages(List<AProgress> list) {
        // 添加图片
        List<String> pictures = new ArrayList<>();

        for (AProgress aprogress : list) {
            String[] imageArr = aprogress.getImages().split(";");
            for (int i = 0; i < imageArr.length; i++) {
                pictures.add(imageArr[i]);
            }
        }
        return pictures;
    }

    void setTime(String time) {
        try {
            mTime.setText(sdf1.format(sdf.parse(time)));
        } catch (ParseException e) {
            HintUitl.toastShort(this, e.getMessage());
            e.printStackTrace();
        }
    }

}

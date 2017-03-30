package com.maiyu.hrssc.home.activity.applying;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.applying.adapter.AttachFileAdapter;
import com.maiyu.hrssc.home.activity.applying.adapter.ImageGridAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.AttachFile;
import com.maiyu.hrssc.home.activity.applying.bean.AttachImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 内容详情
 */
public class ContentDetialActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.blcs_lable)
    TextView mBlcsLable;
    @BindView(R.id.blcs_text)
    TextView mBlcsText;
    @BindView(R.id.blcs_rl)
    RelativeLayout mBlcsRl;
    @BindView(R.id.blsm_lable)
    TextView mBlsmLable;
    @BindView(R.id.blsm_arrow_iv)
    ImageView mBlsmArrowIv;
    @BindView(R.id.blsm_rl)
    RelativeLayout mBlsmRl;
    @BindView(R.id.lqfs_lable)
    TextView mLqfsLable;
    @BindView(R.id.lqfs_arrow_iv)
    ImageView mLqfsArrowIv;
    @BindView(R.id.lqfs_text)
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
    @BindView(R.id.yundanjilv_rl)
    RelativeLayout mYundanjilvRl;
    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.simple_desc_text)
    TextView mSimpleDescText;
    @BindView(R.id.simple_desc_xh)
    ImageView mSimpleDescXh;
    @BindView(R.id.simple_desc_rl)
    RelativeLayout mSimpleDescRl;
    @BindView(R.id.choose_mould_text)
    TextView mChooseMouldText;
    @BindView(R.id.choose_mould_xh)
    ImageView mChooseMouldXh;
    @BindView(R.id.choose_mould_rl)
    RelativeLayout mChooseMouldRl;
    AttachFileAdapter mAttachFileAdapter;
    ImageGridAdapter mGridAdapter;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_content_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("内容详情", true, false);


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
        List<AttachImage> images = new ArrayList<>();
        List<AttachFile> files = new ArrayList<>();

        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));
        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));
        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));
        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));
        images.add(new AttachImage("http://s4.cdn.deahu.com/show/lfile/81E6678F2C08A53370C9CE5D86A8A6C0.jpg", "证明"));

        files.add(new AttachFile("https://sm.wdjcdn.com/release/files/jupiter/5.52.20.13520/wandoujia-web_seo_baidu_homepage.apk", "豌豆荚.apk"));
        files.add(new AttachFile("https://sm.wdjcdn.com/release/files/jupiter/5.52.20.13520/wandoujia-web_seo_baidu_homepage.apk", "豌豆荚.apk"));
        files.add(new AttachFile("https://sm.wdjcdn.com/release/files/jupiter/5.52.20.13520/wandoujia-web_seo_baidu_homepage.apk", "豌豆荚.apk"));
        files.add(new AttachFile("https://sm.wdjcdn.com/release/files/jupiter/5.52.20.13520/wandoujia-web_seo_baidu_homepage.apk", "豌豆荚.apk"));

        mAttachFileAdapter.setData(files);
        mGridAdapter.updatePickImageView(images, mGridView);
    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.blcs_rl, R.id.blsm_rl, R.id.lqfs_rl, R.id.yundanjilv_rl, R.id.simple_desc_rl, R.id.choose_mould_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blcs_rl:
                break;
            case R.id.blsm_rl:
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
}

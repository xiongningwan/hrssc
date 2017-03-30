package com.maiyu.hrssc.home.activity.applying;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.applying.adapter.YDJLAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.TypeText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 运单记录
 */
public class YDJLActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.no_value)
    TextView mNoValue;
    @BindView(R.id.direction_zh__city_from)
    TextView mZhCiryFrom;
    @BindView(R.id.direction_en_city_from)
    TextView mEnCiryFrom;
    @BindView(R.id.direction_zh__city_to)
    TextView mZhCiryTo;
    @BindView(R.id.direction_en_city_to)
    TextView mEnCiryTo;
    @BindView(R.id.vertivcal_content)
    RecyclerView mRecyclerView;
    private YDJLAdapter mAdapter;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_yun_dan_ji_lu);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("运单记录", true, false);

        // 设置列表
        mAdapter = new YDJLAdapter(this);
        mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void initData() {
        List<TypeText> tt = new ArrayList<>();

        tt.add(new TypeText("已签收", "2016-12-10 14：55"));
        tt.add(new TypeText("快件在深圳【是世界上少数】已装车,准备发往下一站", "2016-12-10 14：55"));
        tt.add(new TypeText("顺风速运 已经收取快件", "2016-12-10 14：55"));
        tt.add(new TypeText("顺风速运", "2016-12-10 14：55"));

        mAdapter.setData(tt);
    }

    @Override
    public void initOnClick(View v) {

    }


}

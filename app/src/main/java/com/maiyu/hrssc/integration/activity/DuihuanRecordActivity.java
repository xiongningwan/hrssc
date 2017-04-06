package com.maiyu.hrssc.integration.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.integration.adapter.DuihuanRecordAdapter;
import com.maiyu.hrssc.integration.bean.Record;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DuihuanRecordActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeToLoadLayout mRefreshLayout;
    private int mPage = 1;
    private final int init = 1;
    private final int isRefreshing = 2;
    private final int isLoadMoreing = 3;
    private int status = init;
    private DuihuanRecordAdapter mAdapter;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_duihuan_record);
        ButterKnife.bind(this);

    }

    @Override
    public void initViews() {
        mHeadView.setTitle("兑换记录", true, false);

        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(true);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mAdapter = new DuihuanRecordAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        List<Record> list = new ArrayList<>();
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491480554532&di=9c0b9ba9748490bc531cf222b8b09e30&imgtype=0&src=http%3A%2F%2Fjd.365jia.cn%2Fuploads%2Fnews%2Ffolder_1713554%2Fimages%2F7efe02190d84322e29c50296bc5048c1.jpg";
        list.add(new Record("中兴手机中兴手机",url, "320", "24", "2016-12-14 23:12"));
        list.add(new Record("中兴手机中兴手机",url, "320", "24", "2016-12-14 23:12"));
        list.add(new Record("中兴手机中兴手机","", "320", "24", "2016-12-14 23:12"));
        list.add(new Record("中兴手机中兴手机",url, "320", "24", "2016-12-14 23:12"));
        list.add(new Record("中兴手机中兴手机",url, "320", "24", "2016-12-14 23:12"));
        list.add(new Record("中兴手机中兴手机","", "320", "24", "2016-12-14 23:12"));
        list.add(new Record("中兴手机中兴手机","", "320", "24", "2016-12-14 23:12"));
        list.add(new Record("中兴手机中兴手机","", "320", "24", "2016-12-14 23:12"));
        mAdapter.setData(list);
    }

    @Override
    public void initOnClick(View v) {

    }

    @Override
    public void onLoadMore() {
        mPage++;
        status = isLoadMoreing;
        initData();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        status = isRefreshing;
        initData();
    }


    /**
     * 刷新加载更多完成
     */
    private void refreshOrLoadMoreComplete() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        if (mRefreshLayout.isLoadingMore()) {
            mRefreshLayout.setLoadingMore(false);
        }
    }
}

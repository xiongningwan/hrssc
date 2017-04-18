package com.maiyu.hrssc.integration.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IIntegrationEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.integration.adapter.DuihuanRecordAdapter;
import com.maiyu.hrssc.integration.bean.Record;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

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
    private int mCount = 10;
    private final int init = 1;
    private final int isRefreshing = 2;
    private final int isLoadMoreing = 3;
    private int status = init;
    private DuihuanRecordAdapter mAdapter;
    private String mToken;


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

        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {
        new RecordListAsyncTask(mToken, String.valueOf(mPage), String.valueOf(mCount)).execute();
    }

    @Override
    public void initOnClick(View v) {

    }

    @Override
    public void onLoadMore() {
        mPage++;
        status = isLoadMoreing;
        initData();
        refreshOrLoadMoreComplete();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        status = isRefreshing;
        initData();
        refreshOrLoadMoreComplete();
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

    /**
     * 兑换产品记录列表
     */
    class RecordListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String page;
        private String rows;
        private List<Record> list;

        public RecordListAsyncTask(String token, String page, String rows) {
            super();

            this.token = token;
            this.page = page;
            this.rows = rows;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IIntegrationEngine engine = EngineFactory.get(IIntegrationEngine.class);
            try {
                list = engine.getMyProductOrder(DuihuanRecordActivity.this, token, page, rows);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(DuihuanRecordActivity.this)) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<Record> list) {

        if (status == init) {
            mAdapter.setData(list);
        } else if (status == isRefreshing) {
            mAdapter.setData(list);
        } else if (status == isLoadMoreing) {
            mAdapter.loadMoreData(list);
        }
    }
}

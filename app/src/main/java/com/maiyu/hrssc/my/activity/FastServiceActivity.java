package com.maiyu.hrssc.my.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.activity.WebActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.ISpecialEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.my.activity.adapter.FastServiceAdapter;
import com.maiyu.hrssc.my.activity.bean.FastService;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 快捷服务
 */
public class FastServiceActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
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
    private FastServiceAdapter mAdapter;
    private LoadingDialog mLoadingDialog;
    private String mToken;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_fast_service);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("快捷服务", true, false);
        mLoadingDialog = new LoadingDialog(this);

        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(true);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mAdapter = new FastServiceAdapter(this, new MessageOnclickLister());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {
        new FastServiceListAsyncTask(mToken, String.valueOf(mPage), String.valueOf(mCount)).execute();
    }

    @Override
    public void initOnClick(View v) {

    }

    /**
     * 进入详情
     */
    class MessageOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            FastService fastService = (FastService) v.getTag(R.id.key_tag_item_data);
            if (fastService.getUrl() == null || "".equals(fastService.getUrl())) {
                HintUitl.toastShort(FastServiceActivity.this, "该服务链接异常，请选择其他服务");
                return;
            }

            Intent intent = new Intent(FastServiceActivity.this, WebActivity.class);
            intent.putExtra("url", fastService.getUrl());
            intent.putExtra("titleName", fastService.getName());
            intent.putExtra("type", ConstantValue.TYPE_ORDINARY);
            startActivity(intent);
        }
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
     * 获取快捷服务列表
     */
    class FastServiceListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String page;
        private String rows;
        private String token;
        private List<FastService> list;

        public FastServiceListAsyncTask(String token, String page, String rows) {
            super();

            this.page = page;
            this.rows = rows;
            this.token = token;
        }

        @Override
        protected void onPreExecute() {
            mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ISpecialEngine engine = EngineFactory.get(ISpecialEngine.class);
            try {
                list = engine.fastService(FastServiceActivity.this, token, page, rows);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(FastServiceActivity.this)) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<FastService> list) {
        if (status == init) {
            mAdapter.setData(list);
        } else if (status == isRefreshing) {
            mAdapter.setData(list);
        } else if (status == isLoadMoreing) {
            mAdapter.loadMoreData(list);
        }
    }


}

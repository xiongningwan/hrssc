package com.maiyu.hrssc.my.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.ISpecialEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.my.activity.adapter.QuestionClassAdapter;
import com.maiyu.hrssc.my.activity.bean.QuestionClass;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 帮助中心
 */
public class HelpCenterActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.search_et)
    EditText mSearchEt;
    @BindView(R.id.search_btn)
    TextView mSearchBtn;
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
    private QuestionClassAdapter mAdapter;
    private LoadingDialog mLoadingDialog;
    private String mToken;

    public static final String CID = "cid";
    public static final String SEARCH = "serach";

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mLoadingDialog = new LoadingDialog(this);

        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(true);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mAdapter = new QuestionClassAdapter(this, new MessageOnclickLister());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {
        new QuestionClassListAsyncTask(mToken, String.valueOf(mPage), String.valueOf(mCount)).execute();
    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick({R.id.back_iv, R.id.search_et, R.id.search_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                break;
            case R.id.search_et:
                break;
            case R.id.search_btn:
                doSearch();
                break;
        }
    }

    void doSearch() {
        String searchStr = mSearchEt.getText().toString();
        if (searchStr == null || "".equals(searchStr)) {
            return;
        }
        Intent intent = new Intent(HelpCenterActivity.this, HelpCenterSubActivity.class);
        intent.putExtra(SEARCH, searchStr);
        startActivity(intent);
    }


    /**
     * 进入详情
     */
    class MessageOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            QuestionClass questionClass = (QuestionClass) v.getTag(R.id.key_tag_item_data);

            Intent intent = new Intent(HelpCenterActivity.this, HelpCenterSubActivity.class);
            intent.putExtra(CID, questionClass.getId());
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
    class QuestionClassListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String page;
        private String rows;
        private String token;
        private List<QuestionClass> list;

        public QuestionClassListAsyncTask(String token, String page, String rows) {
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
                list = engine.questClassList(HelpCenterActivity.this, token, page, rows);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(HelpCenterActivity.this)) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<QuestionClass> list) {
        if (status == init) {
            mAdapter.setData(list);
        } else if (status == isRefreshing) {
            mAdapter.setData(list);
        } else if (status == isLoadMoreing) {
            mAdapter.loadMoreData(list);
        }
    }

}

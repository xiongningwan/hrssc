package com.maiyu.hrssc.my.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.ISpecialEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.my.activity.adapter.QuestionClassSubAdapter;
import com.maiyu.hrssc.my.activity.bean.Question;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 帮助中心下级菜单
 */
public class HelpCenterSubActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
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
    private QuestionClassSubAdapter mAdapter;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    public static final String QID = "qid";

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_help_center_sub);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("问题分类", true, false);
        mLoadingDialog = new LoadingDialog(this);

        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(true);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mAdapter = new QuestionClassSubAdapter(this, new MessageOnclickLister());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {
        String cid = getIntent().getStringExtra(HelpCenterActivity.CID);
        String search = getIntent().getStringExtra(HelpCenterActivity.SEARCH);

        if (cid == null && search != null) {
            new QuestionClassSubListAsyncTask(mToken, "", search, String.valueOf(mPage), String.valueOf(mCount)).execute();
        } else if (cid != null && search == null) {
            new QuestionClassSubListAsyncTask(mToken, cid, "", String.valueOf(mPage), String.valueOf(mCount)).execute();
        }
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
            Question question = (Question) v.getTag(R.id.key_tag_item_data);

            Intent intent = new Intent(HelpCenterSubActivity.this, QuestionDetailActivity.class);
            intent.putExtra(QID, question.getId());
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
     * 获取问题子分类列表
     */
    class QuestionClassSubListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String cid;
        private String search;
        private String page;
        private String rows;
        private List<Question> list;

        public QuestionClassSubListAsyncTask(String token, String cid, String search, String page, String rows) {
            super();

            this.token = token;
            this.cid = cid;
            this.search = search;
            this.page = page;
            this.rows = rows;
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
                list = engine.getSubQuestionList(HelpCenterSubActivity.this, token, cid, search, page, rows);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(HelpCenterSubActivity.this)) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<Question> list) {
        if (status == init) {
            mAdapter.setData(list);
        } else if (status == isRefreshing) {
            mAdapter.setData(list);
        } else if (status == isLoadMoreing) {
            mAdapter.loadMoreData(list);
        }
    }

}

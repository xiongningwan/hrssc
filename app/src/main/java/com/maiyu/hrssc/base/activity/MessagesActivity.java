package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.messageAdapter;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.Messages;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息系统
 */
public class MessagesActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
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
    private messageAdapter mMessageAdapter;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    public static final String MESSAGE_ITEM_ID = "message_item_id";

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_messages);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("消息", true, false);
        mLoadingDialog = new LoadingDialog(this);

        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(true);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mMessageAdapter = new messageAdapter(this, new MessageOnclickLister(), new DeleteMessageItemOnclickLister());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMessageAdapter);

        mToken = DataCenter.getInstance().getuser().getToken();
    }


    @Override
    public void initData() {

        new MessagesListAsyncTask(mToken, String.valueOf(mPage), String.valueOf(mCount)).execute();

    }

    @Override
    public void initOnClick(View v) {

    }


    /**
     * 进入消息详情
     */
    class MessageOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            setResult(RESULT_OK); // 返回首页刷新

            Messages messages = (Messages) v.getTag(R.id.key_tag_item_data);
            Intent intent = new Intent(MessagesActivity.this, MessageDetailActivity.class);
            intent.putExtra(MESSAGE_ITEM_ID, messages.getId());
            startActivity(intent);
        }
    }


    /**
     * 删除消息
     */
    class DeleteMessageItemOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            setResult(RESULT_OK); // 返回首页刷新

            Messages messages = (Messages) v.getTag(R.id.key_tag_item_data);
            int position = mMessageAdapter.getPosition(messages);
            mMessageAdapter.removeItem(position);
            // 执行请求
            new DeleteMessagesItemAsyncTask(mToken, messages.getId()).execute();
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
     * 获取消息列表
     */
    class MessagesListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String page;
        private String rows;
        private String token;
        private List<Messages> list;

        public MessagesListAsyncTask(String token, String page, String rows) {
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
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                list = engine.getMessageList(MessagesActivity.this, token, page, rows);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(MessagesActivity.this)) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<Messages> list) {
        if (status == init) {
            mMessageAdapter.setData(list);
        } else if (status == isRefreshing) {
            mMessageAdapter.setData(list);
        } else if (status == isLoadMoreing) {
            mMessageAdapter.loadMoreData(list);
        }
    }


    /**
     * 删除消息
     */
    class DeleteMessagesItemAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String mid;
        private String str;

        public DeleteMessagesItemAsyncTask(String token, String mid) {
            super();

            this.token = token;
            this.mid = mid;
        }

        @Override
        protected void onPreExecute() {
            mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                str = engine.deleteMessageItem(MessagesActivity.this, token, mid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(MessagesActivity.this)) {
                return;
            }
            if (str != null && !str.equals("")) {
                HintUitl.toastShort(MessagesActivity.this, str);
            }

            super.onPostExecute(result);
        }
    }

}

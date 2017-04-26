package com.maiyu.hrssc.home.activity.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.home.activity.todo.adapter.TodoPageAdapter;
import com.maiyu.hrssc.home.activity.todo.bean.Todo;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待签署 盖章 完成
 */
public class TodoFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
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
    private TodoPageAdapter mAdapter;

    public TodoFragment() {
        // Required empty public constructor
    }

    public static TodoFragment newInstance(int param1) {
        TodoFragment fragment = new TodoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    private void initView() {
        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(true);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mAdapter = new TodoPageAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        String token = DataCenter.getInstance().getuser().getToken();

        switch (mParam1) {
            case 0:
                new ContractListAsyncTask(token, String.valueOf(mPage), String.valueOf(mCount), String.valueOf(mParam1)).execute();
                break;
            case 1:
                new ContractListAsyncTask(token, String.valueOf(mPage), String.valueOf(mCount), String.valueOf(mParam1)).execute();
                break;
            case 3:
                new ContractListAsyncTask(token, String.valueOf(mPage), String.valueOf(mCount), String.valueOf(mParam1)).execute();
                break;
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
     * 获取合同列表
     */
    class ContractListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String page;
        private String rows;
        private String status;
        private List<Todo> list;

        public ContractListAsyncTask(String token, String page, String rows, String status) {
            super();

            this.token = token;
            this.page = page;
            this.rows = rows;
            this.status = status;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                list = engine.findMyContract(getActivity(), token, page, rows, status);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(getActivity())) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<Todo> list) {
        if (status == init) {
            mAdapter.setData(list);
        } else if (status == isRefreshing) {
            mAdapter.setData(list);
        } else if (status == isLoadMoreing) {
            mAdapter.loadMoreData(list);
        }
    }
}

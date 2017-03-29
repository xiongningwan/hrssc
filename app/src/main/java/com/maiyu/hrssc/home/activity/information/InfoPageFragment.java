package com.maiyu.hrssc.home.activity.information;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.home.activity.information.adapter.InfoPageAdapter;
import com.maiyu.hrssc.home.activity.information.bean.Info;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待签署 盖章 完成
 */
public class InfoPageFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeToLoadLayout mRefreshLayout;
    private int mPage = 1;
    private final int init = 1;
    private final int isRefreshing = 2;
    private final int isLoadMoreing = 3;
    private int status = init;
    private InfoPageAdapter mAdapter;

    public InfoPageFragment() {
        // Required empty public constructor
    }

    public static InfoPageFragment newInstance(int param1) {
        InfoPageFragment fragment = new InfoPageFragment();
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
        View view = inflater.inflate(R.layout.fragment_info_page, container, false);
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
        mAdapter = new InfoPageAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        List list = new ArrayList();

        if (1 == mParam1) {
            list.add(new Info("关于证明办理最新规则", "2017-2-22"));
            list.add(new Info("档案借阅规则", "2017-2-22"));
            list.add(new Info("关于证明办理最新规则", "2017-2-22"));
            mAdapter.setData(list);
        } else if (2 == mParam1) {
            list.add(new Info("新闻1", "2017-2-22"));
            list.add(new Info("新闻2", "2017-2-22"));
            list.add(new Info("新闻3", "2017-2-22"));
            mAdapter.setData(list);

        } else if (3 == mParam1) {
            list.add(new Info("公告1", "2017-2-22"));
            list.add(new Info("公告2", "2017-2-22"));
            list.add(new Info("公告3", "2017-2-22"));
            mAdapter.setData(list);
        }else if (3 == mParam1) {
            list.add(new Info("通知1", "2017-2-22"));
            list.add(new Info("通知2", "2017-2-22"));
            list.add(new Info("通知3", "2017-2-22"));
            mAdapter.setData(list);
        }
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

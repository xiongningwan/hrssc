package com.maiyu.hrssc.home.activity.applying;

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
import com.maiyu.hrssc.home.activity.applying.adapter.DLQPageAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.Apply;
import com.maiyu.hrssc.home.activity.applying.bean.GetApplysData;
import com.maiyu.hrssc.home.activity.todo.dialog.ConfirmDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申请 待领取
 */
public class DLQFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
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
    private DLQPageAdapter mAdapter;
    private GetApplysData mGetApplysData;
    private String mToken;

    public DLQFragment() {
        // Required empty public constructor
    }

    public static DLQFragment newInstance(int param1) {
        DLQFragment fragment = new DLQFragment();
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
        mAdapter = new DLQPageAdapter(getActivity(), new OnItemClickListener());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        String status = "2"; //状态0待审核，1待办理，2待领取，3待评价，4已完成,5-已驳回 ，6-草稿箱
        new GetApplysDataAsyncTask(mToken, status, String.valueOf(mPage), String.valueOf(mCount)).execute();
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
     * 获取申请列表
     */
    class GetApplysDataAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String status;
        private String page;
        private String rows;
        private GetApplysData getApplysData;

        public GetApplysDataAsyncTask(String token, String status, String page, String rows) {
            super();

            this.token = token;
            this.status = status;
            this.page = page;
            this.rows = rows;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                getApplysData = engine.getApplys(getActivity(), token, status, page, rows);
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
            if (getApplysData != null) {
                setData(getApplysData);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(GetApplysData getApplysData) {
        mGetApplysData = getApplysData;
        List<Apply> list = getApplysData.getApplys();

        if (list != null && list.size() != 0) {
            if (status == init) {
                mAdapter.setData(list);
            } else if (status == isRefreshing) {
                mAdapter.setData(list);
            } else if (status == isLoadMoreing) {
                mAdapter.loadMoreData(list);
            }
        }
    }

    /**
     * 确认领取
     */
    class GainAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String aid;
        private String str;

        public GainAsyncTask(String token, String aid) {
            super();

            this.token = token;
            this.aid = aid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                str = engine.gain(getActivity(), token, aid);
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
            if (str != null) {
                HintUitl.toastShort(getActivity(), str);
            }

            super.onPostExecute(result);
        }
    }


    class OnItemClickListener implements View.OnClickListener {
        ConfirmDialog dialog;

        @Override
        public void onClick(View v) {
            if (dialog == null) {
                dialog = new ConfirmDialog(getActivity(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Apply apply = (Apply) v.getTag(R.id.key_tag_item_data);
                        if (apply != null) {
                            new GainAsyncTask(mToken, apply.getId()).execute();
                        }
                        dialog.closeDialog();
                    }
                });
            }
            dialog.setTitleText("确认已领取，确认后不可撤销");
            dialog.show();
        }
    }
}

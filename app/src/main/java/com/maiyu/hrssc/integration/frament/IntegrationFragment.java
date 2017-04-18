package com.maiyu.hrssc.integration.frament;

import android.content.Intent;
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
import com.maiyu.hrssc.base.engine.IIntegrationEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.integration.activity.ProductItemActivity;
import com.maiyu.hrssc.integration.adapter.IntegrationAdapter;
import com.maiyu.hrssc.integration.bean.IngegrationProduct;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IntegrationFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {
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
    Unbinder unbinder;

    private IntegrationAdapter mAdapter;
    private String mToken;
    private IntegrationDataObserver integrationObserver;


    public IntegrationFragment() {
        // Required empty public constructor
    }

    public static IntegrationFragment newInstance() {
        IntegrationFragment fragment = new IntegrationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jifen, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    private void initView() {
        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(false);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mAdapter = new IntegrationAdapter(getActivity(), new OneProductOnclickLister());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mToken = DataCenter.getInstance().getuser().getToken();
        integrationObserver = new IntegrationDataObserver();
        DataCenter.getInstance().registerObserver(integrationObserver);
    }

    void initData() {
        new ProductsListAsyncTask(mToken, String.valueOf(mPage), String.valueOf(mCount)).execute();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        DataCenter.getInstance().unregisterObserver(integrationObserver);
    }

    /**
     * 积分数据观察者
     */
    public class IntegrationDataObserver implements DataCenter.DataObserver {

        @Override
        public void onDataChangedListener(int type, Object... data) {
            if (type == DataCenter.TYPE_INTEGTATION_INFO) {
                mAdapter.refreshIntegration();
            }
        }
    }


    /**
     * 进入详情
     */
    class OneProductOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            IngegrationProduct product = (IngegrationProduct) v.getTag(R.id.key_tag_item_data);

            Intent intent = new Intent(getActivity(), ProductItemActivity.class);
            intent.putExtra("productId", product.getId());
            startActivity(intent);
        }
    }

    @Override
    public void onLoadMore() {
        //mPage++;
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
     * 获取产品列表
     */
    class ProductsListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String page;
        private String rows;
        private List<IngegrationProduct> list;

        public ProductsListAsyncTask(String token, String page, String rows) {
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
                list = engine.getProducts(getActivity(), token, page, rows);
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

    private void setData(List<IngegrationProduct> list) {

        if (status == init) {
            mAdapter.setData(list);
        } else if (status == isRefreshing) {
            mAdapter.setData(list);
        } else if (status == isLoadMoreing) {
            mAdapter.loadMoreData(list);
        }
    }
}

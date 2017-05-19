package com.maiyu.hrssc.home.activity.applying;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.home.activity.applying.adapter.DraftAdapter;
import com.maiyu.hrssc.home.activity.applying.bean.Apply;
import com.maiyu.hrssc.home.activity.applying.bean.GetApplysData;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DraftActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.left_button_icon)
    ImageView mLeftButtonIcon;
    @BindView(R.id.title_left_button)
    RelativeLayout mTitleLeftButton;
    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.delete_btn)
    TextView mDeleteBtn;
    @BindView(R.id.cancle_btn)
    TextView mCancleBtn;
    @BindView(R.id.confirm_delete_btn)
    TextView mConfirmDeleteBtn;
    @BindView(R.id.title_right_button)
    RelativeLayout mTitleRightButton;
    private DraftAdapter mAdapter;

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
    private String mToken;
    private GetApplysData mGetApplysData;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_draft);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mCancleBtn.setVisibility(View.GONE);
        mConfirmDeleteBtn.setVisibility(View.GONE);

        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(true);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mAdapter = new DraftAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        // 设置列表
        mToken = DataCenter.getInstance().getuser().getToken();
        String status = "6"; //状态0待审核，1待办理，2待领取，3待评价，4已完成,5-已驳回 ，6-草稿箱
        new GetApplysDataAsyncTask(mToken, status, String.valueOf(mPage), String.valueOf(mCount)).execute();

     }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.title_left_button, R.id.delete_btn, R.id.cancle_btn, R.id.confirm_delete_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_button:
                finish();
                break;
            case R.id.delete_btn:
                mDeleteBtn.setVisibility(View.GONE);
                mCancleBtn.setVisibility(View.VISIBLE);
                mConfirmDeleteBtn.setVisibility(View.VISIBLE);
                mAdapter.showCircleDelBtn(true);
                break;
            case R.id.cancle_btn:
                mDeleteBtn.setVisibility(View.VISIBLE);
                mCancleBtn.setVisibility(View.GONE);
                mConfirmDeleteBtn.setVisibility(View.GONE);
                mAdapter.showCircleDelBtn(false);
                break;
            case R.id.confirm_delete_btn:
                // 删除选中list item
               // mAdapter.detele();
                List<Apply> applyList = mAdapter.getRemoveDrafts();
                for(Apply apply : applyList) {
                    new DeleteBusinessAsyncTask(mToken, apply.getId()).execute();
                }
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
                getApplysData = engine.getApplys(DraftActivity.this, token, status, page, rows);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(DraftActivity.this)) {
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
     * 删除业务
     */
    class DeleteBusinessAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String aid;
        private String str;

        public DeleteBusinessAsyncTask(String token, String aid) {
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
                str = engine.deleteBusiness(DraftActivity.this, token, aid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(DraftActivity.this)) {
                return;
            }
            if (str != null) {
                HintUitl.toastShort(DraftActivity.this, str);

                List<Apply> applyList = mAdapter.getRemoveDrafts();
                for(Apply apply : applyList) {
                    if(aid == apply.getId()) {
                        mAdapter.detele(apply);
                    }
                }


            }

            super.onPostExecute(result);
        }
    }


}
